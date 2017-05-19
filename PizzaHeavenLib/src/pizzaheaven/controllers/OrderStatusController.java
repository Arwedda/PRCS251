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
import pizzaheaven.models.OrderStatus;

/**
 *
 * @author dansc
 */
public class OrderStatusController extends APIController implements IAPIController{
    private OrderStatus[] cachedStatuses;
    
    @Override
    public OrderStatus[] getCached() {
        return cachedStatuses;
    }
    
    @Override 
    public OrderStatus[] get() {
        return get(false);
    }
    
    @Override
    public OrderStatus[] get(Boolean forceUpdate) {
        System.out.println("[INFO] Fetching resource from: " + Globals.API_ENDPOINT + "/OrderStatus" + (forceUpdate ? ". Forcing cache to update..." : ""));
        if (!forceUpdate) {
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedStatuses != null) {
                return cachedStatuses;
            }
        }

        OrderStatus[] orderStatusList = new OrderStatus[0];
        try {
            orderStatusList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/OrderStatus"), OrderStatus[].class);
            cachedStatuses = orderStatusList;
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cachedStatuses;
    }
    
    @Override
    public int add(Object obj) {
        if (obj instanceof OrderStatus) {
            OrderStatus orderStatus = (OrderStatus)obj;
            return post(orderStatus, Globals.API_ENDPOINT + "/OrderStatus/");
        }
        return -1;
    }
    
    @Override
    public int update(Object obj) {
        try {
        OrderStatus orderStatus = (OrderStatus)obj;
        return put(orderStatus, Globals.API_ENDPOINT + "/OrderStatus/" + URLEncoder.encode(orderStatus.getStatus(), "UTF-8").replace("+", "%20"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    
    @Override 
    public int remove(Object obj) {
        OrderStatus orderStatus = (OrderStatus)obj;
        try {
            return delete(Globals.API_ENDPOINT + "/OrderStatus/" + URLEncoder.encode(orderStatus.getStatus(), "UTF-8").replace("+", "%20"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
}
