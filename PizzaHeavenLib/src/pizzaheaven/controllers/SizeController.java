/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.controllers;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import pizzaheaven.globals.Globals;
import pizzaheaven.models.Size;

/**
 *
 * @author Joseph Kellaway + Craig Banyard
 */
public class SizeController extends APIController implements IAPIController{
    Size[] cachedSize;
    
    @Override 
    public Size[] get() {
        return get(false);
    }
    
    @Override 
    public Size[] get(Boolean forceUpdate) {
        System.out.println("[INFO] Fetching resource from: " + Globals.API_ENDPOINT + "/PizzaSizes" + (forceUpdate ? ". Forcing cache to update..." : ""));
        if (!forceUpdate){
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedSize != null) {
                return cachedSize;
            }
        }
        
        Size[] sizeList = new Size[0];
        try {
            sizeList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/PizzaSizes"), Size[].class);
            cachedSize = sizeList;
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return sizeList;
    }
    
    @Override 
    public int add(Object obj) {
        if (obj instanceof Size) {
            Size size = (Size)obj;
            return post(size, Globals.API_ENDPOINT + "/PizzaSizes/");
        }
        return -1;
    }
    
    @Override 
    public int remove(Object obj) {
        if (obj instanceof Size) {
            Size size = (Size)obj;
            try {
                return delete(Globals.API_ENDPOINT + "/PizzaSizes/" + URLEncoder.encode(size.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    @Override 
    public int update(Object obj) {
        if (obj instanceof Size) {
            Size size = (Size)obj;
            try {
                return put(size, Globals.API_ENDPOINT + "/PizzaSizes/" + URLEncoder.encode(size.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    public int update(Object obj, String oldName) {
        if (obj instanceof Size) {
            Size size = (Size)obj;
            try {
                delete(Globals.API_ENDPOINT + "/PizzaSizes/" + URLEncoder.encode(oldName, "UTF-8").replace("+", "%20"));
                return put(size, Globals.API_ENDPOINT + "/PizzaSizes/" + URLEncoder.encode(size.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    @Override
    public Size[] getCached() {
        return cachedSize;
    }
}