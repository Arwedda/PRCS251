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
import pizzaheaven.models.Order;

/**
 * 
 * @author dansc
 */
public class OrderController extends APIController implements IAPIController {
    private Order[] cachedOrders;
    
    public Order get(String customerID, String orderDateTime){
        Order placedOrder = new Order();
        try {
            if (cachedOrders == null){
                cachedOrders = get();
            }
            for (Order order : cachedOrders){
                if (order.getOrderDateTime().equals(orderDateTime)){
                    if (order.getCustomerID().equals(customerID)){
                        placedOrder = order;
                    }
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return placedOrder;
    }
    
    public Order get(String orderID) {
        for (Order order : get()) {
            if (order.getOrderID().equals(orderID)) {
                return order;
            }
        }
        return null;
    }
    
    @Override
    public Order[] getCached() {
        return cachedOrders;
    }
    
    public Order get(int id) {
        for (Order order : get()) {
            if (order.getOrderID().equals(String.valueOf(id))) {
                return order;
            }
        }
        return null;
    }
    
    @Override 
    public Order[] get() {
        return get(false);
    }
    
    private Order[] sortNumeric(Order[] orderList) {
        for (int i = (orderList.length - 1); i >= 0; i--) {
            for (int j = 1; j <= i; j++) {
                if (Integer.parseInt(orderList[j - 1].getOrderID()) > Integer.parseInt(orderList[j].getOrderID())) {
                    Order temp = orderList[j - 1];
                    orderList[j - 1] = orderList[j];
                    orderList[j] = temp;
                }
            }
        }
        return orderList;
    }
    
    public ArrayList<Order> getReady(){
        ArrayList<Order> readyOrders = new ArrayList<>();
        try {
            for (Order order : new OrderController().get()) {
                if (order.getStatus().equals("Ready For Delivery")) {
                    readyOrders.add(order);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return readyOrders;
    }
    
    @Override
    public Order[] get(Boolean forceUpdate) {
        System.out.println("[INFO] Fetching resource from: " + Globals.API_ENDPOINT + "/CustomerOrders" + (forceUpdate ? ". Forcing cache to update..." : ""));
        if (!forceUpdate) {
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedOrders != null) {
                return cachedOrders;
            }
        }

        Order[] orderList = new Order[0];
        try {
            orderList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/CustomerOrders"), Order[].class);
            cachedOrders = sortNumeric(orderList);
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cachedOrders;
    }
    
    @Override
    public int add(Object obj) {
        if (obj instanceof Order) {
            Order order = (Order)obj;
            return post(order, Globals.API_ENDPOINT + "/CustomerOrders/");
        }
        return -1;
    }
    
    @Override
    public int update(Object obj) {
        Order order = (Order)obj;
        return put(order, Globals.API_ENDPOINT + "/CustomerOrders/" + order.getOrderID());
    }
    
    @Override 
    public int remove(Object obj) {
        Order order = (Order)obj;
        try {
            return delete(Globals.API_ENDPOINT + "/CustomerOrders/" + order.getOrderID());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
}