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
public class Pizza {
    /**
     * The name of the Pizza object.
     * It is the unique identifier within the database table.
     */
    @JsonProperty("Name")
    private String name;
    /**
     * The description of the Pizza object.
     */
    @JsonProperty("Description")
    private String description;
    /**
     * The price of the small version of a Pizza object.
     */
    @JsonProperty("SmallPrice")
    private String smallPrice;
    /**
     * The price of the medium version of a Pizza object.
     */
    @JsonProperty("MediumPrice")
    private String mediumPrice;
    /**
     * The price of the large version of a Pizza object.
     */
    @JsonProperty ("LargePrice")
    private String largePrice;
    /**
     * The heat rating of the Pizza object.
     */
    @JsonProperty ("HeatRating")
    private String heatRating;
    /**
     * The {@link Image} of the Pizza object.
     */
    @JsonProperty("Image")
    private String image;
    
    /**
     * Default constructor.
     */
    public Pizza(){
        name = "Unknown";
        description = "Unknown";
        image = "Unknown";
        heatRating = "0.0";
        smallPrice = "0.0"; 
        mediumPrice = "0.0";
        largePrice = "0.0";
    }
    
    /**
     * Overloaded constructor. Sets all attributes of {@link Pizza#} on creation       
     * @param name          Default value of {@link Pizza#name}
     * @param description   Default value of {@link Pizza#description}
     * @param smallPrice    Default value of {@link Pizza#smallPrice}
     * @param mediumPrice   Default value of {@link Pizza#mediumPrice}
     * @param largePrice    Default value of {@link Pizza#largePrice}
     * @param heatRating    Default value of {@link Pizza#heatRating}
     * @param image         Default value of {@link Pizza#image}
     */
    public Pizza(String name, String description, String smallPrice, String mediumPrice,
            String largePrice, String heatRating, String image){
        this.name = name;
        this.description = description;
        this.smallPrice = smallPrice;
        this.mediumPrice = mediumPrice;
        this.largePrice = largePrice;
        this.heatRating = heatRating;
        this.image = image;
    }

    /**
     * @return  Current value of {@link Pizza#name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets value of {@link Pizza#name}
     * @param name  New value of {@link Pizza#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return  Current value of {@link Pizza#description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets value of {@link Pizza#description}
     * @param description  New value of {@link Pizza#description}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return  Current value of {@link Pizza#heatRating}
     */
    public String getHeatRating() {
        return heatRating;
    }

    /**
     * Sets value of {@link Pizza#heatRating}
     * @param heatRating  New value of {@link Pizza#heatRating}
     */
    public void setHeatRating(String heatRating) {
        this.heatRating = heatRating;
    }

    /**
     * @return  Current value of {@link Pizza#smallPrice}
     */
    public String getSmallPrice() {
        return smallPrice;
    }

    /**
     * Sets value of {@link Pizza#smallPrice}
     * @param smallPrice  New value of {@link Pizza#smallPrice}
     */
    public void setSmallPrice(String smallPrice) {
        this.smallPrice = smallPrice;
    }

    /**
     * @return  Current value of {@link Pizza#mediumPrice}
     */
    public String getMediumPrice() {
        return mediumPrice;
    }

    /**
     * Sets value of {@link Pizza#mediumPrice}
     * @param mediumPrice  New value of {@link Pizza#mediumPrice}
     */
    public void setMediumPrice(String mediumPrice) {
        this.mediumPrice = mediumPrice;
    }

    /**
     * @return  Current value of {@link Pizza#largePrice}
     */
    public String getLargePrice() {
        return largePrice;
    }

    /**
     * Sets value of {@link Pizza#largePrice}
     * @param largePrice  New value of {@link Pizza#largePrice}
     */
    public void setLargePrice(String largePrice) {
        this.largePrice = largePrice;
    }

    /**
     * @return  Current value of {@link Pizza#image}
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets value of {@link Pizza#image}
     * @param image  New value of {@link Pizza#image}
     */
    public void setImage(String image) {
        this.image = image;
    }
    
    @Override
    public String toString() {
        return this.name + " "  + this.description + " " + this.smallPrice;
    }
}