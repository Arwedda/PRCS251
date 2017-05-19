/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author dan
 */
public class CompanyPosition {
    /**
     * The name of the CompanyPosition object.
     */
    @JsonProperty("Name")
    private String name;
    
    /**
     * @return  Current value of {@link CompanyPosition#name}
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets value of {@link CompanyPosition#name}
     * @param name  New value of {@link CompanyPosition#name}
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return  Current value of {@link CompanyPosition#name} in String format
     */
    @Override
    public String toString() {
       return this.name; 
    }
}
