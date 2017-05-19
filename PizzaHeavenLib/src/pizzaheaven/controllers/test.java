/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.controllers;

import java.util.ArrayList;
import pizzaheaven.models.Order;

/**
 *
 * @author jkellaway
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OrderController orderController = new OrderController();
        ArrayList<Order> rdy = orderController.getReady();
        try {
            for (Order order : rdy){
                System.out.println(order.getOrderID());
            }
        } catch (Exception e){
            e.getMessage();
        }
    }
}