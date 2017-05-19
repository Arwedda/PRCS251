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
public class Crust {
    /**
     * The name of the Crust object.
     * It is the unique identifier within the  database table.
     */
    @JsonProperty("CrustName")
    private String name;
    /**
     * The description of the Crust object.
     */
    @JsonProperty("CrustDescription")
    private String description;
    /**
     * The price of the Crust object.
     * Added to the cost of {@link CustomPizza}.
     */
    @JsonProperty("CrustPrice")
    private String price;
    
    /**
     * Default constructor.
     */
    public Crust(){
        name = "Unknown";
        description = "Unknown";
        price = "0.00";
    }
    
    /**
     * Overloaded constructor. Sets all attributes of {@link Crust#} on creation
     * @param name          Default value of {@link Crust#name}
     * @param description   Default value of {@link Crust#description}
     * @param price         Default value of {@link Crust#price}
     */
    public Crust(String name, String description, String price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * @return  Current value of {@link Crust#name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets value of {@link Crust#name}
     * @param name  New value of {@link Crust#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return  Current value of {@link Crust#description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets value of {@link Crust#description}
     * @param description   New value of {@link Crust#description}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return  Current value of {@link Crust#price}
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets value of {@link Crust#price}
     * @param price New value of {@link Crust#price}
     */
    public void setPrice(String price) {
        this.price = price;
    }
}