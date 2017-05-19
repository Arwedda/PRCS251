/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author zrobinson1
 */
public class OrderItem {   
    @JsonProperty("ID")
    private String id;
    @JsonProperty("Name")
    private String itemName; 
    @JsonProperty("Quantity")
    private String quantity;
    @JsonProperty("Price")
    private String price;
    
    /**
     *
     */
    public OrderItem(){
        this.id = "unknown";
        this.itemName = "unknown";
        this.quantity = "unknown";
        this.price = "unknown";
    }
    
    /**
     *
     * @param id
     * @param itemName
     * @param quantity
     * @param price
     */
    public OrderItem(String id, String itemName, String quantity, String price){
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * @return  Current value of {@link Drink#name}
     */
    public String getId() {
        return id;
    }

    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Drink#name}
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return  Current value of {@link Drink#name}
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Drink#name}
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return  Current value of {@link Drink#name}
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Drink#name}
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * @return  Current value of {@link Drink#name}
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Drink#name}
     */
    public void setPrice(String price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return this.id + ", "  + this.itemName + ", " + this.price + ", " + this.quantity;
    }
    
    /**
     *
     * @return  
     */
    public String nameToString() {
        String nameString = itemName;
        String nameSplit[] = nameString.split("\\^");
        
        if (nameSplit.length > 1){
            String toppingString = "";
            for (int i = 5; i < nameSplit.length; i++){
                toppingString = nameSplit[i] + ", ";
            }
            try {
                toppingString = toppingString.substring(0, (toppingString.length() - 2));
            } catch (Exception e){
                toppingString = "None";
            }
            toppingString += ".";
            nameString = nameSplit[0] + " Custom Pizza. " + nameSplit[1] + " base, " + nameSplit[2] + " crust, " + nameSplit[3] + " sauce, " + nameSplit[4] + " cheese. Toppings: " + toppingString;
        }
        
        return nameString;
    }
}