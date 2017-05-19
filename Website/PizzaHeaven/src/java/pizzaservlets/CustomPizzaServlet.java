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
import pizzaheaven.controllers.BaseController;
import pizzaheaven.controllers.CheeseController;
import pizzaheaven.controllers.CrustController;
import pizzaheaven.controllers.SauceController;
import pizzaheaven.controllers.SizeController;
import pizzaheaven.controllers.ToppingsController;
import pizzaheaven.models.Base;
import pizzaheaven.models.Cheese;
import pizzaheaven.models.Crust;
import pizzaheaven.models.Sauce;
import pizzaheaven.models.Size;
import pizzaheaven.models.Topping;

/**
 *
 * @author Joseph Kellaway & Craig Banyard
 */
@WebServlet(name = "CustomPizzaServlet", urlPatterns = {"/CustomPizzaServlet"})
public class CustomPizzaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        SizeController sizeController = new SizeController();
        BaseController baseController = new BaseController();
        CrustController crustController = new CrustController();
        SauceController sauceController = new SauceController();
        CheeseController cheeseController = new CheeseController();
        ToppingsController toppingsController = new ToppingsController();
        
        Size[] sizeList = sizeController.get();
        Base[] baseList = baseController.get();
        Crust[] crustList = crustController.get();
        Sauce[] sauceList = sauceController.get();
        Cheese[] cheeseList = cheeseController.get();
        Topping[] toppingList = toppingsController.get();
        
        session.setAttribute("sizeList", sizeList);
        session.setAttribute("baseList", baseList);
        session.setAttribute("crustList", crustList);
        session.setAttribute("sauceList", sauceList);
        session.setAttribute("cheeseList", cheeseList);
        session.setAttribute("toppingList", toppingList);
        response.sendRedirect("buildapizza.jsp");
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