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
public class Order {
    /**
     * The ID of the Order object.
     * It is the unique identifier within the database table.
     */
    @JsonProperty("ID")
    private String orderID;
    /**
     * The date and time that the Order has been PLACED.
     */
    @JsonProperty("DateTime")
    private String orderDateTime;
    /**
     * The total price charged to the {@link Customer} for the {@link OrderItem}s
     * on this Order.
     */
    @JsonProperty("Cost")
    private String orderCost;
    /**
     * The {@link Staff} member responsible for delivering this Order.
     */
    @JsonProperty("StaffID")
    private String staffID;
    /**
     * The {@link Customer} that has placed this Order.
     */
    @JsonProperty("CustomerID")
    private String customerID;
    /**
     * The date and time that the Order has been DELIVERED.
     */
    @JsonProperty("DeliveryDateTime")
    private String deliveryDateTime;
    /**
     * The date and time that the Order IS REQUIRED.
     */
    @JsonProperty("RequestedDateTime")
    private String requestedDateTime;
    /**
     * The {@link Status} of this Order
     */
    @JsonProperty("Status")
    private String status;
    /**
     * 
     */
    @JsonProperty("PaymentMethod")
    private String paymentMethod;

    /**
     *
     * @return  Current value of {@link Drink#name}
     */
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Order ID " + this.orderID + ", Customer ID "  + this.customerID + ", Requested Delivery " + this.deliveryDateTime + ", Status " + this.status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     *
     */
    public Order(){
        this.orderCost = "Unknown";
        this.orderDateTime = null;
        this.orderID = "0";
        this.staffID = null;
        this.customerID = null;
        this.deliveryDateTime = null;
        this.requestedDateTime = null;
        this.status = null;
    }
    
    /**
     *
     * @param orderDateTime
     * @param orderCost
     * @param staffID
     * @param customerID
     * @param deliveryDateTime
     * @param requestedDateTime
     * @param status
     */
    public Order(String orderDateTime, String orderCost, String staffID, String customerID, String deliveryDateTime, String requestedDateTime, String status){
        this.orderCost = orderCost;
        this.orderDateTime = orderDateTime;
        this.orderID = "0";
        this.staffID = staffID;
        this.customerID = customerID;
        this.deliveryDateTime = deliveryDateTime;
        this.requestedDateTime = requestedDateTime;
        this.status = status;
    }

    /**
     *
     * @param orderID
     * @param orderDateTime
     * @param orderCost
     * @param staffID
     * @param customerID
     * @param deliveryDateTime
     * @param requestedDateTime
     * @param status
     */
    public Order(String orderID, String orderDateTime, String orderCost, String staffID, String customerID, String deliveryDateTime, String requestedDateTime, String status) {
        this.orderID = orderID;
        this.orderDateTime = orderDateTime;
        this.orderCost = orderCost;
        this.staffID = staffID;
        this.customerID = customerID;
        this.deliveryDateTime = deliveryDateTime;
        this.requestedDateTime = requestedDateTime;
        this.status = status;
    }

    /**
     * @return  Current value of {@link Drink#name}
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Drink#name}
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * @return  Current value of {@link Drink#name}
     */
    public String getOrderDateTime() {
        return orderDateTime;
    }

    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Drink#name}
     */
    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    /**
     * @return  Current value of {@link Drink#name}
     */
    public String getOrderCost() {
        return orderCost;
    }

    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Drink#name}
     */
    public void setOrderCost(String orderCost) {
        this.orderCost = orderCost;
    }

    /**
     * @return  Current value of {@link Drink#name}
     */
    public String getStaffID() {
        return staffID;
    }

    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Drink#name}
     */
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    /**
     * @return  Current value of {@link Drink#name}
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Drink#name}
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     * @return  Current value of {@link Drink#name}
     */
    public String getDeliveryDateTime() {
        return deliveryDateTime;
    }

    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Drink#name}
     */
    public void setDeliveryDateTime(String deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
    }

    /**
     * @return  Current value of {@link Drink#name}
     */
    public String getRequestedDateTime() {
        return requestedDateTime;
    }

    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Drink#name}
     */
    public void setRequestedDateTime(String requestedDateTime) {
        this.requestedDateTime = requestedDateTime;
    }

    /**
     * @return  Current value of {@link Drink#name}
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets value of {@link Drink#name}
     * @param name  New value of {@link Drink#name}
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}