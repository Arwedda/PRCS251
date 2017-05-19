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
import pizzaheaven.models.Crust;

/**
 *
 * @author Joseph Kellaway + Craig Banyard
 */
public class CrustController extends APIController implements IAPIController{
    Crust[] cachedCrust;
    
    @Override 
    public Crust[] get() {
        return get(false);
    }
    
    @Override 
    public Crust[] get(Boolean forceUpdate) {
        System.out.println("[INFO] Fetching resource from: " + Globals.API_ENDPOINT + "/PizzaCrusts" + (forceUpdate ? ". Forcing cache to update..." : ""));
        if (!forceUpdate){
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedCrust != null) {
                return cachedCrust;
            }
        }
        
        Crust[] crustList = new Crust[0];
        try {
            crustList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/PizzaCrusts"), Crust[].class);
            cachedCrust = Sort(crustList);
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return crustList;
    }
    
    private Crust[] Sort(Crust[] crustList){
        for (int i = 0; i < crustList.length; i++){
            if (crustList[i].getName().equals("Normal")){
                Crust tempCrust = crustList[0];
                crustList[0] = crustList[i];
                crustList[i] = tempCrust;
                break;
            }
        }
        return crustList;
    }
    
    @Override 
    public int add(Object obj) {
        if (obj instanceof Crust) {
            Crust crust = (Crust)obj;
            return post(crust, Globals.API_ENDPOINT + "/PizzaCrusts/");
        }
        return -1;
    }
    
    @Override 
    public int remove(Object obj) {
        if (obj instanceof Crust) {
            Crust crust = (Crust)obj;
            try {
                return delete(Globals.API_ENDPOINT + "/PizzaCrusts/" + URLEncoder.encode(crust.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    @Override 
    public int update(Object obj) {
        if (obj instanceof Crust) {
            Crust crust = (Crust)obj;
            try {
                return put(crust, Globals.API_ENDPOINT + "/PizzaCrusts/" + URLEncoder.encode(crust.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    public int update(Object obj, String oldName) {
        if (obj instanceof Crust) {
            Crust crust = (Crust)obj;
            try {
                delete(Globals.API_ENDPOINT + "/PizzaCrusts/" + URLEncoder.encode(oldName, "UTF-8").replace("+", "%20"));
                return post(crust, Globals.API_ENDPOINT + "/PizzaCrusts/" + URLEncoder.encode(crust.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    @Override
    public Crust[] getCached() {
        return cachedCrust;
    }
}