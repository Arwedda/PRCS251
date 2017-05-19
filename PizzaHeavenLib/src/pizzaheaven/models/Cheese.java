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
public class Cheese {
    /**
     * The name of the Cheese object.
     * It is the unique identifier within the database table.
     */
    @JsonProperty("CheeseName")
    private String name;
    /**
     * The description of the Cheese object.
     */
    @JsonProperty("CheeseDescription")
    private String description;
    /**
     * The price of the Cheese object.
     * Added to the cost of {@link CustomPizza}.
     */
    @JsonProperty("CheesePrice")
    private String price;
    
    /**
     * Default constructor.
     */
    public Cheese(){
        name = "Unknown";
        description = "Unknown";
        price = "0.00";
    }
    
    /**
     *  Overloaded constructor. Sets all attributes of {@link Cheese#} on creation
     * @param name          Default value of {@link Cheese#name}
     * @param description   Default value of {@link Cheese#description}
     * @param price         Default value of {@link Cheese#price}
     */
    public Cheese(String name, String description, String price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * @return  Current value of {@link Cheese#name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets value of {@link Cheese#name}
     * @param name  New value of {@link Cheese#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return  Current value of {@link Cheese#description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets value of {@link Cheese#description}
     * @param description   New value of {@link Cheese#description}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return  Current value of {@link Cheese#price}
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets value of {@link Cheese#price}
     * @param price New value of {@link Cheese#price}
     */
    public void setPrice(String price) {
        this.price = price;
    }
}