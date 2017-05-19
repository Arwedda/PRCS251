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
public class Side {
    /**
     * The name of the Side object.
     * It is the unique identifier within the database table.
     */
    @JsonProperty("Name")
    private String name;
    /**
     * The description of the Side object.
     */
    @JsonProperty("Description")
    private String description;
    /**
     * The price of the Side object.
     */
    @JsonProperty("Price")
    private String price;
    /**
     * The heat rating of the Side object.
     */
    @JsonProperty("HeatRating")
    private String heatRating;
    /**
     * The {@link Image} of the Side object.
     */
    @JsonProperty("Image")
    private String image;

    /**
     * Default constructor.
     */
    public Side(){
        this.name = "Unknown";
        this.description = "Unknown";
        this.price = "0.0";
        this.heatRating = "0.0";
        this.image = "Unknown";
    }
    
    /**
     * Overloaded constructor. Sets all attributes of {@link Side#} on creation       
     * @param name          Default value of {@link Side#name}
     * @param description   Default value of {@link Side#description}
     * @param price         Default value of {@link Side#price}
     * @param heatRating    Default value of {@link Side#heatRating}
     * @param image         Default value of {@link Side#image}
     */
    public Side(String name, String description, String price, String heatRating, String image){
        this.name = name;
        this.description = description;
        this.price = price;
        this.heatRating = heatRating;
        this.image = image;
    }

    /**
     * @return  Current value of {@link Side#name}
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets value of {@link Side#name}
     * @param name  New value of {@link Side#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return  Current value of {@link Side#description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets value of {@link Side#description}
     * @param description  New value of {@link Side#description}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return  Current value of {@link Side#price}
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets value of {@link Side#price}
     * @param price  New value of {@link Side#price}
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return  Current value of {@link Side#heatRating}
     */
    public String getHeatRating() {
        return heatRating;
    }

    /**
     * Sets value of {@link Side#heatRating}
     * @param heatRating  New value of {@link Side#heatRating}
     */
    public void setHeatRating(String heatRating) {
        this.heatRating = heatRating;
    }

    /**
     * @return  Current value of {@link Side#image}
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets value of {@link Side#image}
     * @param image  New value of {@link Side#image}
     */
    public void setImage(String image) {
        this.image = image;
    }
    
    @Override
    public String toString() {
        return this.name + " "  + this.description + " " + this.price;
    }
}