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
public class Sauce {
    /**
     * The name of the Sauce object.
     * It is the unique identifier within the  database table.
     */
    @JsonProperty("SauceName")
    private String name;
    /**
     * The description of the Sauce object.
     */
    @JsonProperty("SauceDescription")
    private String description;
    /**
     * The price of the Sauce object.
     * Added to the cost of {@link CustomPizza}.
     */
    @JsonProperty("SaucePrice")
    private String price;
    /**
     * The heat rating of the Sauce object.
     */
    @JsonProperty("HeatRating")
    private String heatRating;

    /**
     * Default constructor.
     */
    public Sauce() {
        this.name = "unknown";
        this.description = "unknown";
        this.price = "0.00";
        this.heatRating = "0.0";
    }

    /**
     * Overloaded constructor. Sets all attributes of {@link Sauce#} on creation
     * @param name          Default value of {@link Sauce#name}
     * @param description   Default value of {@link Sauce#description}
     * @param price         Default value of {@link Sauce#price}
     * @param heatRating    Default value of {@link Sauce#heatRating}
     */
    public Sauce(String name, String description, String price, String heatRating) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.heatRating = heatRating;
    }

    /**
     * @return  Current value of {@link Sauce#name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets value of {@link Sauce#name}
     * @param name  New value of {@link Sauce#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return  Current value of {@link Sauce#description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets value of {@link Sauce#description}
     * @param description   New value of {@link Sauce#description}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return  Current value of {@link Sauce#price}
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets value of {@link Sauce#price}
     * @param price New value of {@link Sauce#price}
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return  Current value of {@link Sauce#heatRating}
     */
    public String getHeatRating() {
        return heatRating;
    }

    /**
     * Sets value of {@link Sauce#heatRating}
     * @param heatRating New value of {@link Sauce#heatRating}
     */
    public void setHeatRating(String heatRating) {
        this.heatRating = heatRating;
    }
}