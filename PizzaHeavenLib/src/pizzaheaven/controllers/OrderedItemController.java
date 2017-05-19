/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.controllers;

import pizzaheaven.globals.Globals;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import pizzaheaven.models.OrderItem;

/**
 * 
 * @author dansc
 */
public class OrderedItemController extends APIController implements IAPIController{
    private OrderItem[] cachedOrderItems;
    
    @Override 
    public OrderItem[] get(Boolean forceUpdate) {
        System.out.println("[INFO] Fetching resource from: " + Globals.API_ENDPOINT + "/OrderItems" + (forceUpdate ? ". Forcing cache to update..." : ""));
        if (!forceUpdate) {
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedOrderItems != null) {
                return cachedOrderItems;
            }
        }
        
        OrderItem[] orderItemsList  = new OrderItem[0];
        try {
            orderItemsList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/OrderItems"), OrderItem[].class);
            cachedOrderItems = orderItemsList;
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return orderItemsList;
    }
    
    @Deprecated
    public OrderItem[] get(){
        OrderItem[] orderedItems = new OrderItem[0];
        try {
            orderedItems = mapper.readValue(new URL(Globals.API_ENDPOINT + "/OrderItems"), OrderItem[].class);
            cachedOrderItems = orderedItems;
            
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return orderedItems;
    }
    
    public OrderItem[] getForID(int id) {
        ArrayList<OrderItem> items = new ArrayList<OrderItem>();
        for (OrderItem item : get(false)) {
            if (item.getId().equals(String.valueOf(id))) {
                items.add(item);
            }
        }
        return items.toArray(new OrderItem[items.toArray().length]);
    }
    
    @Override 
    public int update(Object obj) {
        if (obj instanceof OrderItem) {
            return put((OrderItem)obj, Globals.API_ENDPOINT + "/OrderItems/" + ((OrderItem)obj).getId());
        }
        return -1;
    }
    
    @Deprecated
    public OrderItem[] getOrderItems(int orderID){
        OrderItem[] orderedItemsList = new OrderItem[10];
        int i = 0;
        try {
            
            for (OrderItem orderItem : get()){
                if (Integer.parseInt(orderItem.getId()) == orderID){
                    
                    orderedItemsList[i] = orderItem;
                    i++;
                }
            }
        
        
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return orderedItemsList;
    }
    
    @Override 
    public OrderItem[] getCached() {
        return cachedOrderItems;
    }
    
    @Deprecated
    public OrderItem[] getCachedItems() {
        return cachedOrderItems;
    }
    
    @Deprecated
    public void updateItem(OrderItem item) {
        put(item, Globals.API_ENDPOINT + "/OrderItems/" + item.getId());
    }
    
    @Deprecated
    /**
     * 
     */
    public void addItem(OrderItem item) {
        post(item, Globals.API_ENDPOINT + "/OrderItems/");
    }
    
    @Override
    public int add(Object obj) {
       if (obj instanceof OrderItem) {
           return post((OrderItem)obj, Globals.API_ENDPOINT + "/OrderItems/");
       }
       return -1;
    }
    
    @Override
    public int remove(Object obj) {
        if (obj instanceof OrderItem) {
            try {
                return delete(Globals.API_ENDPOINT + "/OrderItems/" + ((OrderItem)obj).getId());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
    
    @Deprecated
     public void removeItem(OrderItem item) {
        try {
            delete(Globals.API_ENDPOINT + "/OrderItems/" + item.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}