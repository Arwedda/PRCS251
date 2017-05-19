/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaservlets;

import pizzaheaven.security.Encryptor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pizzaheaven.controllers.CustomerController;
import pizzaheaven.models.Customer;
import pizzaheaven.security.Hash;
import static pizzaheaven.security.KeyStretch.keyStretch;
import org.apache.commons.codec.binary.Base64;
import pizzaheaven.security.Authenticator;
import pizzaheaven.security.KeyAndSaltGenerator;

/**
 *
 * @author Joseph Kellaway & Craig Banyard
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerController customerController = new CustomerController();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Customer[] customerList = customerController.get();
        int countBefore = customerList.length;

        Boolean exists = Authenticator.checkEmail(email);
            
        if (exists){
            response.sendRedirect("login.jsp?exists=true");
        } else {
            String firstName = request.getParameter("firstname");
            String surname = request.getParameter("surname");
            String dob = request.getParameter("dob");
            String phoneNumber = request.getParameter("phonenumber");
            String deliveryLineOne = request.getParameter("deliverylineone");
            String deliveryLineTwo = request.getParameter("deliverylinetwo");
            String deliveryCity = request.getParameter("deliverycity");
            String deliveryCounty = request.getParameter("deliverycounty");
            String deliveryPostCode = request.getParameter("deliverypostcode");
            
            String salt = KeyAndSaltGenerator.SHA1(16);
            String str = keyStretch(password, salt);
            String hash = Hash.SHA256(str);
            String privateKey = KeyAndSaltGenerator.SHA1(8);
            
            Customer customer = new Customer(firstName, surname, dob, phoneNumber,
                    email.toLowerCase(), hash, deliveryLineOne, deliveryLineTwo, deliveryCity,
                    deliveryCounty, deliveryPostCode, salt, privateKey);
                
            customer = (Customer)Encryptor.encrypt(customer);

            customerController.add(customer);

            while (countBefore == (int) (customerController.get(true).length)) { 
                try{
                    Thread.sleep(500);
                } catch (Exception e){
                }
            }
            
            customer = customerController.getCustomer(email);

            session.setAttribute("customer", customer);
            response.sendRedirect("welcome.jsp");
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