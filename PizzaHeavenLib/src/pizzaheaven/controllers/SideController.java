/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import pizzaheaven.globals.Globals;
import pizzaheaven.models.Side;

/**
 *
 * @author zrobinson1
 */
public class SideController extends APIController implements IAPIController {
    Side[] cachedSide; 
    
    @Override 
    public Side[] get() {
        return get(false);
    }
    
    @Override
    public Side[] get(Boolean forceUpdate) {
        System.out.println("[INFO] Fetching resource from: " + Globals.API_ENDPOINT + "/Sides" + (forceUpdate ? ". Forcing cache to update..." : ""));
        if (!forceUpdate) {
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedSide != null) {
                return cachedSide;
            }
        }
        Side[] sideList = new Side[0];
        try {
            sideList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/Sides"), Side[].class);
            cachedSide = sortAZ(sideList);
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sideList;
    }
    
    public Side get(String name) {
        for (Side side : get()) {
            if (side.getName().equals(name)) {
                return side;
            }
        }
        return null;
    }
    
    private Side[] sortAZ(Side[] sideList){
        Arrays.sort(sideList, new Comparator<Side>() {
            @Override
            public int compare(Side p1, Side p2) {
                return p1.getName().compareToIgnoreCase(p2.getName());
            }
        });
        return sideList;
    }
    
    @Override 
    public int update(Object obj) {
        try {
            if (obj instanceof Side) {
                Side side = (Side)obj;
                return put(side, Globals.API_ENDPOINT + "/Sides/" + URLEncoder.encode(side.getName(), "UTF-8").replace("+", "%20"));
            } 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    
    public int update(Object obj, String oldName) {
        try {
            if (obj instanceof Side) {
                delete(Globals.API_ENDPOINT + "/Sides/" + URLEncoder.encode(oldName, "UTF-8").replace("+", "%20"));
                Side side = (Side)obj;
                return post(side, Globals.API_ENDPOINT + "/Sides/" + URLEncoder.encode(side.getName(), "UTF-8").replace("+", "%20"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    
    @Override
    public int add(Object obj) {
        try {
            if (obj instanceof Side) {
                Side side = (Side)obj;
                return post(side, Globals.API_ENDPOINT + "/Sides/");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    
    @Override
    public int remove(Object obj) {
        try {
            if (obj instanceof Side) {
                Side side = (Side)obj;            
                return delete(Globals.API_ENDPOINT + "/Sides/" + URLEncoder.encode(side.getName(), "UTF-8").replace("+", "%20"));
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    
    @Override
    public Side[] getCached() {
        return cachedSide;
    }
    
}
