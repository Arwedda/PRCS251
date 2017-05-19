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
public class Size {
    /**
     * The name of the size object.
     * It is the unique identifier within the  database table.
     */
    @JsonProperty("SizeName")
    private String name;
    /**
     * The description of the size object.
     */
    @JsonProperty("SizeDescription")
    private String description;
    /**
     * The price of the size object.
     * Added to the cost of {@link CustomPizza}.
     */
    @JsonProperty("SizePrice")
    private String price;
    
    /**
     * Default constructor.
     */
    public Size(){
        name = "Unknown";
        description = "Unknown";
        price = "0.00";
    }
    
    /**
     * Overloaded constructor. Sets all attributes of {@link Size#} on creation
     * @param name          Default value of {@link Size#name}
     * @param description   Default value of {@link Size#description}
     * @param price         Default value of {@link Size#price}
     */
    public Size(String name, String description, String price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * @return  Current value of {@link Size#name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets value of {@link Size#name}
     * @param name  New value of {@link Size#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return  Current value of {@link Size#description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets value of {@link Size#description}
     * @param description   New value of {@link Size#description}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return  Current value of {@link Size#price}
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets value of {@link Size#price}
     * @param price New value of {@link Size#price}
     */
    public void setPrice(String price) {
        this.price = price;
    }
}