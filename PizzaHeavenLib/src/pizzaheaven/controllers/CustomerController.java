/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.controllers;
import java.net.URL;
import pizzaheaven.globals.Globals;
import java.util.Date;
import pizzaheaven.models.Customer;
import pizzaheaven.security.Encryptor;

/**
 *
 * @author Joseph Kellaway + Craig Banyard
 */
public class CustomerController extends APIController implements IAPIController {
    Customer[] cachedCustomer;
    
    public CustomerController() {
        lastUpdated.setYear(1970);
    }
    
    @Override 
    public Customer[] get() {
        return get(false);
    }

    @Override
    public Customer[] get(Boolean forceUpdate) {
        System.out.println("[INFO] Fetching resource from: " + Globals.API_ENDPOINT + "/Customers" + (forceUpdate ? ". Forcing cache to update..." : ""));
        if (!forceUpdate) {
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedCustomer != null) {
                return cachedCustomer;
            }
        }
        
        Customer[] customerList = new Customer[0];
        try {
            customerList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/customers/"), Customer[].class);
            cachedCustomer = customerList;
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return customerList;
    }
    
    public Customer getCustomer(Integer id) {
        try {
            for (Customer customer : get()) {
                if (customer.getFirstName().contains("="))
                    customer = (Customer)Encryptor.decrypt(customer);
                if (customer.getCustomerID().equals(id.toString())) {
                    return customer;
                }   
            }
        } catch (Exception e) {
            System.out.println("[ERR] " + e.getMessage());
        }
        return null;
    }
    
    public Customer getCustomer(String email){
        Customer findCustomer = new Customer();
        try {
            if (cachedCustomer == null){
                cachedCustomer = get();
            }
            for (Customer customer : cachedCustomer){
                customer = (Customer)Encryptor.decrypt(customer);
                if (customer.getEmail() != null && customer.getEmail().equals(email)){
                    findCustomer = customer;
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return findCustomer;
    }
    
    @Override
    public int add(Object object) {
        if (object instanceof Customer) {
            Customer customer = (Customer)object;
            return post(customer, Globals.API_ENDPOINT + "/customers/");
        }
        return -1;
    }

    @Override
    public int update(Object object) {
        if (object instanceof Customer) {
            Customer customer = (Customer)object;
            return put(customer, Globals.API_ENDPOINT + "/customers/" + customer.getCustomerID());
        }
        return -1;
    }
    
    @Override
    public Customer[] getCached() {
        return cachedCustomer;
    }

    @Override
    public int remove(Object object) {
        /*
        Don't want to remove customer accounts. Should create an "active" boolean variable
        */
        return -1;
    }
}