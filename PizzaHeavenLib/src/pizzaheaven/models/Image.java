/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 *
 * @author Joseph Kellaway + Craig Banyard
 */
public class Image {
    /**
     * The name of the Image object.
     * It is the unique identifier within the database table.
     */
    @JsonProperty("Name")
    String name;
    /**
     * The base64 value of the Image object.
     */
    @JsonProperty("Base64")
    String base64;
  
    /**
     * Default constructor.
     */
    public Image(){
        name = "unknown";
        base64 = "unknown";
    }

    /**
     * Overloaded constructor. Sets all attributes of {@link Image#} on creation       
     * @param name      Default value of {@link Image#name}
     * @param base64    Default value of {@link Image#base64}
     */
    public Image(String name, String base64) {
        this.name = name;
        this.base64 = base64;
    }

    /**
     * @return  Current value of {@link Image#name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets value of {@link Image#name}
     * @param name  New value of {@link Image#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return  Current value of {@link Image#base64}
     */
    public String getBase64() {
        return base64;
    }

    /**
     * Sets value of {@link Image#base64}
     * @param base64  New value of {@link Image#base64}
     */
    public void setBase64(String base64) {
        this.base64 = base64;
    }
    
    /**
     * Checks to see if the image name already exists within the database
     * @param obj   Any object (checks to see if it is an image within the function)
     * @return      true if the names are the same, false if the object is null, not an image or not found
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) 
            return false;
        if (!(obj instanceof Image))
            return false;

        Image other = (Image) obj;
        if (this.name.equals(other.name)) 
            return true;

        return false;
    }

    /**
     *  {@link Image#equals} requires it according to Netbeans??
     */
    @Deprecated
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.name);
        return hash;
    }
}