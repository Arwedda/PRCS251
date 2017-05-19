/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaservlets;

import java.io.IOException;
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

@WebServlet(name = "OrderStatusServlet", urlPatterns = {"/OrderStatusServlet"})
public class OrderStatusServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            OrderController orderController = new OrderController();
            Order[] orders = orderController.get(true);
            OrderedItemController orderedItemController = new OrderedItemController();
            OrderItem[] orderedItems = orderedItemController.get(true);
            Customer customer = (Customer) session.getAttribute("customer");
            String id = customer.getCustomerID();
            ArrayList<Order> arlOrders = new ArrayList<>();
            ArrayList<OrderItem> arlOrderItems = new ArrayList<>();

            for (Order thisOrder : orders) {
                if (thisOrder.getCustomerID().equals(id)){
                    switch (thisOrder.getStatus()){
                        case "Placed":
                        case "Prep":
                        case "Ready For Delivery":
                        case "Out For Delivery":
                            arlOrders.add(thisOrder);
                            break;
                        default:
                            break;
                    }
                }
            }

            for (Order thisOrder : arlOrders){
                String orderID = thisOrder.getOrderID();
                for (OrderItem orderItem : orderedItems){
                    if (orderItem.getId().equals(orderID)){
                        arlOrderItems.add(orderItem);
                    }
                }
            }

            session.setAttribute("activeOrders", arlOrders);
            session.setAttribute("activeOrderItems", arlOrderItems);
            response.sendRedirect("status.jsp");
        } catch (Exception e){
            response.sendRedirect("login.jsp");
        }
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