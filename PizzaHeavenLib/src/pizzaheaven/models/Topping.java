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
public class Topping {
    /**
     * The name of the topping object.
     * It is the unique identifier within the  database table.
     */
    @JsonProperty("ToppingName")
    private String name;
    /**
     * The description of the topping object.
     */
    @JsonProperty("ToppingDescription")
    private String description;
    /**
     * The price of the topping object.
     * Added to the cost of {@link CustomPizza}.
     */
    @JsonProperty("ToppingPrice")
    private String price;
    /**
     * The heat rating of the topping object.
     */
    @JsonProperty("HeatRating")
    private String heatRating;
    /**
     * The image of the topping object
     */
    @JsonProperty("ToppingImage")
    private String image;

    /**
     * Default constructor.
     */
    public Topping() {
        this.name = "unknown";
        this.description = "unknown";
        this.price = "0.00";
        this.heatRating = "0.0";
        this.image = "unknown";
    }

    /**
     * Overloaded constructor. Sets all attributes of {@link Topping#} on creation
     * @param name          Default value of {@link Topping#name}
     * @param description   Default value of {@link Topping#description}
     * @param price         Default value of {@link Topping#price}
     * @param heatRating    Default value of {@link Topping#heatRating}
     * @param image         Default value of {@link Topping#image}
     */
    public Topping(String name, String description, String price, String heatRating, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.heatRating = heatRating;
        this.image = image;
    }

    /**
     * @return  Current value of {@link Topping#name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets value of {@link Topping#name}
     * @param name  New value of {@link Topping#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return  Current value of {@link Topping#description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets value of {@link Topping#description}
     * @param description   New value of {@link Topping#description}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return  Current value of {@link Topping#price}
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets value of {@link Topping#price}
     * @param price New value of {@link Topping#price}
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return  Current value of {@link topping#heatRating}
     */
    public String getHeatRating() {
        return heatRating;
    }

    /**
     * Sets value of {@link Topping#heatRating}
     * @param heatRating New value of {@link Topping#heatRating}
     */
    public void setHeatRating(String heatRating) {
        this.heatRating = heatRating;
    }

    /**
     * @return  Current value of {@link topping#image}
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets value of {@link Topping#image}
     * @param image New value of {@link Topping#image}
     */
    public void setImage(String image) {
        this.image = image;
    }    
}