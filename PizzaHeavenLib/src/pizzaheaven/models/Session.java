/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.models;
import pizzaheaven.controllers.APIController;
import java.util.ArrayList;
import pizzaheaven.controllers.CompanyPositionController;
import pizzaheaven.controllers.CustomerController;
import pizzaheaven.controllers.DrinksController;
import pizzaheaven.controllers.ImagesController;
import pizzaheaven.controllers.OrderController;
import pizzaheaven.controllers.OrderStatusController;
import pizzaheaven.controllers.OrderedItemController;
import pizzaheaven.controllers.PizzaController;
import pizzaheaven.controllers.SideController;
import pizzaheaven.controllers.StaffController;
import pizzaheaven.controllers.ToppingsController;

/**
 *
 * @author dan
 */
public class Session { 
    Staff staff;
    ArrayList<APIController> controllers;
    static Session session;
    
    /**
     *
     * @param staff
     */
    public Session(Staff staff) {
        this.staff = staff;
        controllers = new ArrayList<>();
    }
    
    /**
     * @return  Current value of {@link Session#name}
     */
    public static Session get() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }
    
    /**
     *
     */
    public Session() {
        controllers = new ArrayList<>();
    }
    
    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Session#name}
     */
    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    
    /**
     * @return  Current value of {@link Session#name}
     */
    public Staff getStaff() {
        return staff;
    }
    
    /**
     * @return  Current value of {@link Session#name}
     */
    public ArrayList<APIController> getControllers() {
        return controllers;
    }
    
    /**
    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Session#name}
     * @param type
     * @return  Current value of {@link Session#name}
     */
    public APIController getController(String type) {
        for (APIController controller : controllers) {
            if (("DrinksController".equals(type) && controller instanceof DrinksController) ||
                ("StaffContoller".equals(type) && controller instanceof StaffController) || 
                ("PizzaController".equals(type) && controller instanceof PizzaController) || 
                ("SideController".equals(type) && controller instanceof SideController) || 
                ("ImagesController".equals(type) && controller instanceof ImagesController) ||
                ("OrderController".equals(type) && controller instanceof OrderController) ||
                ("CustomerController".equals(type) && controller instanceof CustomerController) || 
                ("CompanyPositionController".equals(type) && controller instanceof CompanyPositionController) ||
                ("OrderStatusController".equals(type) && controller instanceof OrderStatusController) ||
                ("ToppingsController".equals(type) && controller instanceof ToppingsController) ||
                ("OrderedItemController".equals(type) && controller instanceof OrderedItemController)) {
                return controller;
            } 
        }
        
        if ("DrinksController".equals(type)) {
            APIController controller = new DrinksController();
            controllers.add(controller);
            return controller;
        }else if ("OrderStatusController".equals(type)) {
            APIController controller = new OrderStatusController();
            controllers.add(controller);
            return controller;
        } else if ("OrderedItemController".equals(type)) {
            APIController controller = new OrderedItemController();
            controllers.add(controller);
            return controller;
        } else if ("CustomerController".equals(type)) {
            APIController controller = new CustomerController();
            controllers.add(controller);
            return controller;
        }else if ("StaffController".equals(type)) { 
            APIController controller = new StaffController();
            controllers.add(controller);
            return controller;
        } else if ("PizzaController".equals(type)) {
            APIController controller = new PizzaController();
            controllers.add(controller);
            return controller;
        } else if ("SideController".equals(type)) {
            APIController controller = new SideController();
            controllers.add(controller);
            return controller;
        } else if ("ImagesController".equals(type)) {
            APIController controller = new ImagesController();
            controllers.add(controller);
            return controller;
        } else if ("OrderController".equals(type)) {
            APIController controller = new OrderController();
            controllers.add(controller);
            return controller;
        } else if ("CompanyPositionController".equals(type)) {
            APIController controller = new CompanyPositionController();
            controllers.add(controller);
            return controller;
        } else if ("ToppingsController".equals(type)) {
            APIController controller = new ToppingsController();
            controllers.add(controller);
            return controller;
        }
        else {
            return null;
        }
    }
    
    /**
     *
     */
    public void dispose() {
        
    }
}