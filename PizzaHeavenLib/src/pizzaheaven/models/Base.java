/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Joseph Kellaway + Craig Banyard
 */
public class Base {
    /**
     * The name of the Base object.
     * It is the unique identifier within the database table.
     */
    @JsonProperty("BaseName")
    private String name;
    /**
     * The description of the Base object.
     */
    @JsonProperty("BaseDescription")
    private String description;
    /**
     * The price of the Base object.
     * Added to the cost of {@link CustomPizza}.
     */
    @JsonProperty("BasePrice")
    private String price;
    
    /**
     * Default constructor.
     */
    public Base(){
        name = "Unknown";
        description = "Unknown";
        price = "0.00";
    }
    
    /**
     * Overloaded constructor. Sets all attributes of {@link Base#} on creation
     * @param name          Default value of {@link Base#name}
     * @param description   Default value of {@link Base#description}
     * @param price         Default value of {@link Base#price}
     */
    public Base(String name, String description, String price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * @return  Current value of {@link Base#name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets value of {@link Base#name}
     * @param name  New value of {@link Base#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return  Current value of {@link Base#description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets value of {@link Base#description}
     * @param description   New value of {@link Base#description}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return  Current value of {@link Base#price}
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets value of {@link Base#price}
     * @param price New value of {@link Base#price}
     */
    public void setPrice(String price) {
        this.price = price;
    }
}