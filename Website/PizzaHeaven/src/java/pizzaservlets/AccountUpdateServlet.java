/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pizzaheaven.controllers.CustomerController;
import pizzaheaven.models.Customer;
import pizzaheaven.security.Authenticator;
import pizzaheaven.security.Encryptor;

/**
 *
 * @author Joseph Kellaway & Craig Banyard
 */
@WebServlet(name = "AccountUpdateServlet", urlPatterns = {"/AccountUpdateServlet"})
public class AccountUpdateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerController customerController = new CustomerController();
        
        String email = request.getParameter("email2");
        String password = request.getParameter("password");
        Customer customer = (Customer) session.getAttribute("customer");
        Customer customerCheck = Authenticator.authenticate(email, password);

        if (customer.getCustomerID().equals(customerCheck.getCustomerID())){
            String firstName = request.getParameter("firstname");
            String surname = request.getParameter("surname");
            String dob = request.getParameter("dob");
            String phoneNumber = request.getParameter("phonenumber");
            String deliveryLineOne = request.getParameter("deliverylineone");
            String deliveryLineTwo = request.getParameter("deliverylinetwo");
            String deliveryCity = request.getParameter("deliverycity");
            String deliveryCounty = request.getParameter("deliverycounty");
            String deliveryPostCode = request.getParameter("deliverypostcode");
            
            customer.updateAccount(firstName, surname, dob, phoneNumber, 
                    deliveryLineOne, deliveryLineTwo, deliveryCity, deliveryCounty, 
                    deliveryPostCode);
            
            customer = (Customer)Encryptor.encrypt(customer);

            customerController.update(customer);
            customer = (Customer)Encryptor.decrypt(customer);
            session.setAttribute("customer", customer);
        }
        response.sendRedirect("account.jsp");
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