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
public class PercentageOffer {
    @JsonProperty("OfferID")
    String offerID;
    @JsonProperty("MinimumValue")
    String minimumValue;
    @JsonProperty("Discount")
    String discount;

    /**
     *
     */
    public PercentageOffer() {
        this.offerID = "Unknown";
        this.minimumValue = "Unknown";
        this.discount = "Unknown";
    }

    /**
     *
     * @param offerID
     * @param minimumValue
     * @param discount
     */
    public PercentageOffer(String offerID, String minimumValue, String discount) {
        this.offerID = offerID;
        this.minimumValue = minimumValue;
        this.discount = discount;
    }
    
    /**
     * @return  Current value of {@link Drink#name}
     */
    public String getOfferID() {
        return offerID;
    }

    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Drink#name}
     */
    public void setOfferID(String offerID) {
        this.offerID = offerID;
    }

    /**
     * @return  Current value of {@link Drink#name}
     */
    public String getMinimumValue() {
        return minimumValue;
    }

    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Drink#name}
     */
    public void setMinimumValue(String minimumValue) {
        this.minimumValue = minimumValue;
    }

    /**
     * @return  Current value of {@link Drink#name}
     */
    public String getDiscount() {
        return discount;
    }

    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Drink#name}
     */
    public void setDiscount(String discount) {
        this.discount = discount;
    }
}