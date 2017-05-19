/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaservlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pizzaheaven.controllers.OrderController;
import pizzaheaven.controllers.OrderedItemController;
import pizzaheaven.models.Customer;
import pizzaheaven.models.Order;
import pizzaheaven.models.OrderItem;

/**
 *
 * @author Joseph Kellaway & Craig Banyard
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        String orderCost = (String) session.getAttribute("orderPrice");
        ArrayList<OrderItem> arlOrderItems = (ArrayList<OrderItem>) session.getAttribute("orderItems");
        
        OrderedItemController orderItemController = new OrderedItemController();
        OrderController orderController = new OrderController();
        Order[] orderList = orderController.get();
        int countBefore = orderList.length;
   
        Order newOrder = new Order(LocalDateTime.now().toString(), orderCost, "1", customer.getCustomerID(), 
                LocalDateTime.now().plusMinutes(45).toString(), LocalDateTime.now().plusMinutes(45).toString(),
                "Placed");
        
        try {
            String queryString = request.getQueryString();
            String[] payment = queryString.split("=");
            if (payment[1].equals("PayPal")){
                newOrder.setPaymentMethod("PayPal");
            }
        } catch (Exception e){
            newOrder.setPaymentMethod("Cash");
        }

        int postResponse = orderController.add(newOrder);

        while (countBefore == (int) (orderController.get(true).length)) { 
            try{
                Thread.sleep(500);
            } catch (Exception e){
            }
        }

        newOrder = orderController.get(newOrder.getCustomerID(), newOrder.getOrderDateTime());

        session.setAttribute("order", newOrder);
        
        int orderItemCount = orderItemController.get(true).length;
        
        for (OrderItem item : arlOrderItems){
            item.setId(newOrder.getOrderID());
            orderItemController.add(item);
            while (orderItemCount == (int) (orderItemController.get(true).length)){
                try{
                    Thread.sleep(500);
                } catch (Exception e){
                }
            }
            orderItemCount += 1;
        }
        response.sendRedirect("OrderStatusServlet");
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}