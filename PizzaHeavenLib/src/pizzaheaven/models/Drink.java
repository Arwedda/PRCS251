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
public class Drink {
    /**
     * The name of the Drink object.
     * It is the unique identifier within the database table.
     */
    @JsonProperty("Name")
    private String name;
    /**
     * The description of the Drink object.
     */
    @JsonProperty("Description")
    private String description;
    /**
     * The {@link Image} of the Drink object.
     */
    @JsonProperty("Image")
    private String image;
    /**
     * The price of the small version of a Drink object.
     */
    @JsonProperty("SmallPrice")
    private String smallPrice;
    /**
     * The price of the medium version of a Drink object.
     */
    @JsonProperty("MediumPrice")
    private String mediumPrice;
    /**
     * The price of the large version of a Drink object.
     */
    @JsonProperty("LargePrice")
    private String largePrice;
    /**
     * The unit size of the small version of a Drink object.
     */
    @JsonProperty("SmallSize")
    private String smallSize;
    /**
     * The unit size of the medium version of a Drink object.
     */
    @JsonProperty("MediumSize")
    private String mediumSize;
    /**
     * The unit size of the large version of a Drink object.
     */
    @JsonProperty("LargeSize")
    private String largeSize;

    /**
     * Default constructor.
     */
    public Drink(){
        this.name = "unknown";
        this.description = "unknown";
        this.image = "unknown";
        this.smallPrice = "0.00";
        this.mediumPrice = "0.00";
        this.largePrice = "0.00";
        this.smallSize = "unknown";
        this.mediumSize = "unknown";
        this.largeSize = "unknown";
    }
    
    /**
     * Overloaded constructor. Sets all attributes of {@link Drink#} on creation       
     * @param name          Default value of {@link Drink#name}
     * @param description   Default value of {@link Drink#description}
     * @param image         Default value of {@link Drink#image}
     * @param smallPrice    Default value of {@link Drink#smallPrice}
     * @param mediumPrice   Default value of {@link Drink#mediumPrice}
     * @param largePrice    Default value of {@link Drink#largePrice}
     * @param smallSize     Default value of {@link Drink#smallSize}
     * @param mediumSize    Default value of {@link Drink#mediumSize}
     * @param largeSize     Default value of {@link Drink#largeSize}
     */
    public Drink(String name, String description, String image, String smallPrice, String mediumPrice, String largePrice, String smallSize, String mediumSize, String largeSize) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.smallPrice = smallPrice;
        this.mediumPrice = mediumPrice;
        this.largePrice = largePrice;
        this.smallSize = smallSize;
        this.mediumSize = mediumSize;
        this.largeSize = largeSize;
    }

    /**
     * @return  Current value of {@link Drink#name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Drink#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return  Current value of {@link Drink#description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets value of {@link Drink#description}
     * @param description  New value of {@link Drink#description}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return  Current value of {@link Drink#image}
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets value of {@link Drink#image}
     * @param image  New value of {@link Drink#image}
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return  Current value of {@link Drink#smallPrice}
     */
    public String getSmallPrice() {
        return smallPrice;
    }

    /**
     * Sets value of {@link Drink#smallPrice}
     * @param smallPrice  New value of {@link Drink#smallPrice}
     */
    public void setSmallPrice(String smallPrice) {
        this.smallPrice = smallPrice;
    }

    /**
     * @return  Current value of {@link Drink#mediumPrice}
     */
    public String getMediumPrice() {
        return mediumPrice;
    }

    /**
     * Sets value of {@link Drink#mediumPrice}
     * @param mediumPrice  New value of {@link Drink#mediumPrice}
     */
    public void setMediumPrice(String mediumPrice) {
        this.mediumPrice = mediumPrice;
    }

    /**
     * @return  Current value of {@link Drink#largePrice}
     */
    public String getLargePrice() {
        return largePrice;
    }

    /**
     * Sets value of {@link Drink#largePrice}
     * @param largePrice  New value of {@link Drink#largePrice}
     */
    public void setLargePrice(String largePrice) {
        this.largePrice = largePrice;
    }

    /**
     * @return  Current value of {@link Drink#smallSize}
     */
    public String getSmallSize() {
        return smallSize;
    }

    /**
     * Sets value of {@link Drink#smallSize}
     * @param smallSize  New value of {@link Drink#smallSize}
     */
    public void setSmallSize(String smallSize) {
        this.smallSize = smallSize;
    }

    /**
     * @return  Current value of {@link Drink#mediumSize}
     */
    public String getMediumSize() {
        return mediumSize;
    }

    /**
     * Sets value of {@link Drink#mediumSize}
     * @param mediumSize  New value of {@link Drink#mediumSize}
     */
    public void setMediumSize(String mediumSize) {
        this.mediumSize = mediumSize;
    }

    /**
     * @return  Current value of {@link Drink#largeSize}
     */
    public String getLargeSize() {
        return largeSize;
    }

    /**
     * Sets value of {@link Drink#largeSize}
     * @param largeSize  New value of {@link Drink#largeSize}
     */
    public void setLargeSize(String largeSize) {
        this.largeSize = largeSize;
    }

    @Override
    public String toString() {
        return this.name + " "  + this.description;
    }
}