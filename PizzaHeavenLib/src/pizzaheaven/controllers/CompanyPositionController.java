/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.controllers;

import java.net.URL;
import pizzaheaven.globals.Globals;
import pizzaheaven.models.CompanyPosition;
import java.net.URLEncoder;
import java.util.Date;

/**
 *
 * @author dansc
 */
public class CompanyPositionController extends APIController implements IAPIController {
    private CompanyPosition[] cachedPositions; 
    
    @Override
    public CompanyPosition[] getCached() {
        return cachedPositions;
    }
    
    @Override 
    public CompanyPosition[] get(Boolean forceUpdate) {
        System.out.println("[INFO] Fetching resource from: " + Globals.API_ENDPOINT + "/CompanyPositions" + (forceUpdate ? ". Forcing cache to update..." : ""));
        if (!forceUpdate) {
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedPositions != null) {
                return cachedPositions;
            }
        }
        
        CompanyPosition[] positionList = new CompanyPosition[0];
        try {
            positionList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/CompanyPositions"), CompanyPosition[].class);
            cachedPositions = positionList;
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return positionList;
    }
    
    @Override 
    public CompanyPosition[] get() {
        return get(false);
    }
    
    public CompanyPosition get(String name) {
        for (CompanyPosition position : get()) {
            if (position.getName().equals(name)) {
                return position;
            }
        }
        return null;
    }
    
    @Override
    public int update(Object obj) {
        if (obj instanceof CompanyPosition) {
            CompanyPosition position = (CompanyPosition)obj;
            try {
                return put(position, Globals.API_ENDPOINT + "/CompanyPositions/" + URLEncoder.encode(position.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    public int update(Object obj, String oldName) {
        if (obj instanceof CompanyPosition) {
            CompanyPosition position = (CompanyPosition)obj;
            try {
                delete(Globals.API_ENDPOINT + "/CompanyPositions/" + URLEncoder.encode(oldName, "UTF-8").replace("+", "%20"));
                return post(position, Globals.API_ENDPOINT + "/CompanyPositions/");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    public int add(Object obj) {
        if (obj instanceof CompanyPosition) {
            CompanyPosition position = (CompanyPosition)obj;
            return post(position, Globals.API_ENDPOINT + "/CompanyPositions/");
        }
        return -1;
    }
    
    @Override
    public int remove(Object obj) {
        if (obj instanceof CompanyPosition) {
            CompanyPosition position = (CompanyPosition)obj;
            try {
                return delete(Globals.API_ENDPOINT + "/CompanyPositions/" + URLEncoder.encode(position.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
}
