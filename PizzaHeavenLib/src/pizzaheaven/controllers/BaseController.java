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
import pizzaheaven.models.Base;

/**
 *
 * @author Joseph Kellaway + Craig Banyard
 */
public class BaseController extends APIController implements IAPIController{
    Base[] cachedBases;
    
    @Override 
    public Base[] get() {
        return get(false);
    }
    
    @Override 
    public Base[] get(Boolean forceUpdate) {
        System.out.println("[INFO] Fetching resource from: " + Globals.API_ENDPOINT + "/PizzaBases" + (forceUpdate ? ". Forcing cache to update..." : ""));
        if (!forceUpdate){
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedBases != null) {
                return cachedBases;
            }
        }
        
        Base[] baseList = new Base[0];
        try {
            baseList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/PizzaBases"), Base[].class);
            cachedBases = Sort(baseList);
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return baseList;
    }
    
    private Base[] Sort(Base[] baseList){
        for (int i = 0; i < baseList.length; i++){
            if (baseList[i].getName().equals("Normal")){
                Base tempBase = baseList[0];
                baseList[0] = baseList[i];
                baseList[i] = tempBase;
                break;
            }
        }
        return baseList;
    }
    
    @Override 
    public int add(Object obj) {
        if (obj instanceof Base) {
            Base base = (Base)obj;
            return post(base, Globals.API_ENDPOINT + "/PizzaBases/");
        }
        return -1;
    }
    
    @Override 
    public int remove(Object obj) {
        if (obj instanceof Base) {
            Base base = (Base)obj;
            try {
                return delete(Globals.API_ENDPOINT + "/PizzaBases/" + URLEncoder.encode(base.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    @Override 
    public int update(Object obj) {
        if (obj instanceof Base) {
            Base base = (Base)obj;
            try {
                return put(base, Globals.API_ENDPOINT + "/PizzaBases/" + URLEncoder.encode(base.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    public void update(Object obj, String oldName) {
        if (obj instanceof Base) {
            Base base = (Base)obj;
            try {
                delete(Globals.API_ENDPOINT + "/PizzaBases/" + URLEncoder.encode(oldName, "UTF-8").replace("+", "%20"));
                post(base, Globals.API_ENDPOINT + "/PizzaBases/" + URLEncoder.encode(base.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    @Override
    public Base[] getCached() {
        return cachedBases;
    }
}