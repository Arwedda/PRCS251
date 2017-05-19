/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author dansc
 */
public class OrderStatus {
    @JsonProperty("Status")
    private String status;
    
    /**
     * @return  Current value of {@link Drink#name}
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Drink#name}
     */
    public void setStatus(String value) {
        this.status = value;
    }
    
    public String toString() {
        return this.status;
    }
}
