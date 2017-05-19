/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaservlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pizzaheaven.controllers.BaseController;
import pizzaheaven.controllers.CheeseController;
import pizzaheaven.controllers.CrustController;
import pizzaheaven.controllers.CustomPizzaController;
import pizzaheaven.controllers.PercentageOfferController;
import pizzaheaven.controllers.SauceController;
import pizzaheaven.controllers.SizeController;
import pizzaheaven.controllers.ToppingsController;
import pizzaheaven.models.Base;
import pizzaheaven.models.Cheese;
import pizzaheaven.models.Crust;
import pizzaheaven.models.CustomPizza;
import pizzaheaven.models.Drink;
import pizzaheaven.models.OrderItem;
import pizzaheaven.models.PercentageOffer;
import pizzaheaven.models.Pizza;
import pizzaheaven.models.Sauce;
import pizzaheaven.models.Side;
import pizzaheaven.models.Size;
import pizzaheaven.models.Topping;

/**
 *
 * @author Joseph Kellaway & Craig Banyard
 */
@WebServlet(name = "BuildBasketServlet", urlPatterns = {"/BuildBasketServlet"})
public class BuildBasketServlet extends HttpServlet {
    HttpSession session;
    ArrayList<OrderItem> arlOrderItems = new ArrayList<>();
    OrderItem orderItem;
    Double totalPrice;
    Boolean found;
    Boolean deal = false;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession(true);
        String customs = request.getParameter("customs");
        String drinks = request.getParameter("drinks");
        String pizzas = request.getParameter("pizzas");
        String sides = request.getParameter("sides");
        String regex;
        Pattern pattern;
        
        arlOrderItems.clear();
        totalPrice = 0.0;
        
        if (customs != null){
            regex = "([A-Za-z0-9- ^]+\\\",\\\"[A-Za-z0-9- ]+\\\",\\\"[A-Za-z0-9- ]+\\\",\\\"[A-Za-z0-9- ]+\\\",\\\"[A-Za-z0-9- ]+\\\",\\\"[A-Za-z0-9- ]+\\\",[0-9]+,[0-9]*.[0-9]{0,2},\\[(\\\"[A-Za-z0-9- ]+\\\",){0,4}\\\"[A-Za-z0-9- ]+\\\"\\],\\\"[A-Za-z0-9- ]+.[A-Za-z0-9- ]+,[A-Za-z0-9- ]+,[A-Za-z0-9- ]+,[A-Za-z0-9- ]+.[A-Za-z0-9- ]+: ([A-Za-z0-9- ]+,){0,4}[A-Za-z0-9- ]+.)";
            pattern = Pattern.compile(regex);
            addCustomsToBasket(customs, pattern);
        }
        if (drinks != null){
            regex = "([A-Za-z0-9- ]+\\([A-Za-z0-9- ]+\\)\",[0-9]+,\"[0-9]+.[0-9]{0,2})";
            pattern = Pattern.compile(regex);
            addDrinksToBasket(drinks, pattern);
        }
        if (pizzas != null){
            regex = "([A-Za-z0-9- ]+((\\(Small\\))|(\\(Medium\\))|(\\(Large\\)))?\\\",[0-9]+,\\\"*[0-9]+.[0-9]{0,2})";
            pattern = Pattern.compile(regex);
            addPizzasToBasket(pizzas, pattern);
        }
        if (sides != null){
            regex = "([A-Za-z0-9- ]+((\\(Small\\))|(\\(Medium\\))|(\\(Large\\)))?\\\",[0-9]+,\\\"*[0-9]+.[0-9]{0,2})";
            pattern = Pattern.compile(regex);
            addSidesToBasket(sides, pattern);
        }
        
        if (!arlOrderItems.isEmpty()){
            if (deal){
                
            } else {
                applyPercentageDiscount();
            }
            session.setAttribute("orderPrice", String.valueOf(totalPrice));
            session.setAttribute("orderItems", arlOrderItems);
            response.sendRedirect("delivery.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    private String cleanEnd(String name){
        if (name == null || name.length() == 0){
            return name;
        }
        return name.substring(0, name.length() - 1);
    }
    
    private String cleanFront(String price){
        if (price == null || price.length() == 0){
            return price;
        }
        return price.substring(1, price.length());
    }
    
    private void addCustomsToBasket(String customs, Pattern customPattern){
        Matcher match;
        SizeController sizeController = new SizeController();
        Size[] sizeList = sizeController.get();
        BaseController baseController = new BaseController();
        Base[] baseList = baseController.get();
        CrustController crustController = new CrustController();
        Crust[] crustList = crustController.get();
        SauceController sauceController = new SauceController();
        Sauce[] sauceList = sauceController.get();
        CheeseController cheeseController = new CheeseController();
        Cheese[] cheeseList = cheeseController.get();
        ToppingsController toppingsController = new ToppingsController();
        Topping[] toppingList = toppingsController.get();
        CustomPizzaController customPizzaController = new CustomPizzaController();
        CustomPizza[] customList = customPizzaController.get();
        int countBefore = customList.length;
        ArrayList<String> arlCustoms = new ArrayList<>();
        match = customPattern.matcher(customs);
        while (match.find()){
            arlCustoms.add(match.group(1));
        }

        for (String customOrder : arlCustoms){
            String[] custom = customOrder.split(",");
            Double checkPrice = 0.00;
            orderItem = new OrderItem();

            custom[0] = cleanEnd(cleanEnd(custom[0]));
            for (int i = 1; i < 6; i ++){
                custom[i] = cleanFront(cleanEnd(custom[i]));
            }

            orderItem.setItemName(custom[0]);
            orderItem.setQuantity(custom[6]);

            found = false;

            for (Size size : sizeList){
                if (size.getName().equals(custom[1])){
                    checkPrice += Double.parseDouble(size.getPrice());
                    found = true;
                    break;
                }
            }
            if (found){
                found = false;
                for (Base base : baseList){
                    if (base.getName().equals(custom[2])){
                        checkPrice += Double.parseDouble(base.getPrice());
                        found = true;
                        break;
                    }
                }
                if (found){
                    found = false;
                    for (Crust crust : crustList){
                        if (crust.getName().equals(custom[3])){
                            checkPrice += Double.parseDouble(crust.getPrice());
                            found = true;
                            break;
                        }
                    }
                    if (found){
                        found = false;
                        for (Sauce sauce : sauceList){
                            if (sauce.getName().equals(custom[4])){
                                checkPrice += Double.parseDouble(sauce.getPrice());
                                found = true;
                                break;
                            }
                        }
                        if (found){
                            found = false;
                            for (Cheese cheese : cheeseList){
                                if (cheese.getName().equals(custom[5])){
                                    checkPrice += Double.parseDouble(cheese.getPrice());
                                    found = true;
                                    break;
                                }
                            }
                            if (found){
                                found = false;
                                String[] toppings = new String[0];
                                String toppingString = "";

                                for (int i = 8; i < custom.length; i++){
                                    toppingString += custom[i];
                                    toppings = toppingString.split("]");
                                }
                                if (toppings[0].equals("[\"None\"")){
                                    found = true;
                                } else {
                                    toppings = toppings[0].split("\"");
                                    toppings[0] = cleanFront(toppings[0]);                                        
                                    for (String top : toppings){
                                        if (!top.equals("")){
                                            found = false;
                                            for (Topping topping : toppingList){
                                                if (topping.getName().equals(top)){
                                                    checkPrice += Double.parseDouble(topping.getPrice());
                                                    found = true;
                                                    break;
                                                }
                                            }
                                            if (!found){
                                                //Incorrect topping name
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else {
                                //Incorrect cheese name
                            }
                        } else {
                            //Incorrect sauce name
                        }
                    } else {
                        //Incorrect crust name
                    }
                } else {
                    //Incorrect base name
                }
            } else {
                //Incorrect size name
            }
            if (found){
                orderItem.setPrice(String.valueOf(checkPrice));
                boolean newPizza = true;
                for (CustomPizza customPizza : customList){
                    if (orderItem.getItemName().equals(customPizza.getCustomPizzaName())){
                        newPizza = false;
                        break;
                    }
                }
                if (newPizza){
                    CustomPizza customPizza = new CustomPizza(custom[0], custom[1], custom[3], custom[2], custom[4], custom[5]);
                    customPizzaController.add(customPizza);

                    while (countBefore == (int) (customPizzaController.get(true).length)) { 
                        //do nothing because we want to wait for the order to go through
                    }
                }
            } else {
                orderItem.setItemName("Custom Pizza (Hacker)");
                orderItem.setPrice("99.99");
            }
            arlOrderItems.add(orderItem);
            totalPrice += (Double.parseDouble(orderItem.getPrice()) * Integer.parseInt(orderItem.getQuantity()));
        }
    }
    
    private void addDrinksToBasket(String drinks, Pattern drinkPattern){
        Matcher match;
        Drink[] drinkList = (Drink[]) session.getAttribute("drinkList");
        ArrayList<String> arlDrinks = new ArrayList<>();
        match = drinkPattern.matcher(drinks);
        while (match.find()){
            arlDrinks.add(match.group(1));
        }
        for (String drinkOrder : arlDrinks){
            String[] drink = drinkOrder.split(",");
            orderItem = new OrderItem();
            orderItem.setItemName(cleanEnd(drink[0]));
            orderItem.setQuantity(drink[1]);
            orderItem.setPrice(cleanFront(drink[2]));
            found = false;

            for (Drink checkDrink : drinkList){
                if (checkDrink.getSmallPrice().equals(orderItem.getPrice())){
                    found = true;
                    break;
                } else if (checkDrink.getMediumPrice().equals(orderItem.getPrice())){
                    found = true;
                    break;
                } else if (checkDrink.getLargePrice().equals(orderItem.getPrice())){
                    found = true;
                    break;
                }
            }
            if (!found){
                orderItem.setItemName("Drink (Hacker)");
                orderItem.setPrice("99.99");
            }
            arlOrderItems.add(orderItem);
            totalPrice += (Double.parseDouble(orderItem.getPrice()) * Integer.parseInt(orderItem.getQuantity()));
        }
    }
    
    private void addPizzasToBasket(String pizzas, Pattern pattern){
        Matcher match;
        Pizza[] pizzaList = (Pizza[]) session.getAttribute("pizzaList");
        ArrayList<String> arlPizzas = new ArrayList<>();
        match = pattern.matcher(pizzas);
        while (match.find()){
            arlPizzas.add(match.group(1));
        }
        for (String pizzaOrder : arlPizzas){
            String[] pizza = pizzaOrder.split(",");
            orderItem = new OrderItem();
            orderItem.setItemName(cleanEnd(pizza[0]));
            orderItem.setQuantity(pizza[1]);
            orderItem.setPrice(pizza[2]);
            found = false;

            for (Pizza checkPizza : pizzaList){
                if ((checkPizza.getName() + " (Small)").equals(orderItem.getItemName())){
                    orderItem.setPrice(((Pizza) checkPizza).getSmallPrice());
                    found = true;
                } else if ((checkPizza.getName() + " (Medium)").equals(orderItem.getItemName())){
                    orderItem.setPrice(((Pizza) checkPizza).getMediumPrice());
                    found = true;
                } else if ((checkPizza.getName() + " (Large)").equals(orderItem.getItemName())){
                    orderItem.setPrice(((Pizza) checkPizza).getLargePrice());
                    found = true;
                }
                if (found){
                    break;
                }
            }
            if (!found){
                orderItem.setItemName("Pizza (Hacker)");
                orderItem.setPrice("99.99");
            }
            arlOrderItems.add(orderItem);
            totalPrice += (Double.parseDouble(orderItem.getPrice()) * Integer.parseInt(orderItem.getQuantity()));
        }
    }
    
    private void addSidesToBasket(String sides, Pattern pattern){
        Matcher match;
        Side[] sideList = (Side[]) session.getAttribute("sideList");
        ArrayList<String> arlSides = new ArrayList<>();
        match = pattern.matcher(sides);
        while (match.find()){
            arlSides.add(match.group(1));
        }
        for (String sideOrder : arlSides){
            String[] side = sideOrder.split(",");
            orderItem = new OrderItem();
            orderItem.setItemName(cleanEnd(side[0]));

            for (Side checkSide : sideList){
                if (checkSide.getName().equals(orderItem.getItemName())){
                    orderItem.setQuantity(side[1]);
                    orderItem.setPrice(checkSide.getPrice());
                    break; //found the side
                }
            }
            arlOrderItems.add(orderItem);
            totalPrice += (Double.parseDouble(orderItem.getPrice()) * Integer.parseInt(orderItem.getQuantity()));
        }
    }
    
    private void applyPercentageDiscount(){
        PercentageOfferController percentageOfferController = new PercentageOfferController();
        PercentageOffer[] percentageOffers = percentageOfferController.get(true);
        
        for (PercentageOffer offer : percentageOffers){
            if (totalPrice >= Double.parseDouble(offer.getMinimumValue())){
                totalPrice = totalPrice - ((totalPrice * Double.parseDouble(offer.getDiscount())) / 100);
                totalPrice = Double.parseDouble(new DecimalFormat("#.##").format(totalPrice));
                break;
            }
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