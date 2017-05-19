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
public class CustomPizza {
    /**
     * The name of the CustomPizza object.
     * It is the unique identifier within the database table.
     */
    @JsonProperty("CustomPizzaName")
    private String customPizzaName;
    /**
     * The Size of the CustomPizza object.
     */
    @JsonProperty("SizeName")
    private String sizeName;  
    /**
     * The Base of the CustomPizza object.
     */
    @JsonProperty("BaseName")
    private String baseName;
    /**
     * The Crust of the CustomPizza object.
     */
    @JsonProperty("CrustName")
    private String crustName;
    /**
     * The Sauce of the CustomPizza object.
     */
    @JsonProperty("SauceName")
    private String sauceName;
    /**
     * The Cheese of the CustomPizza object.
     */
    @JsonProperty("CheeseName")
    private String cheeseName;

    @Deprecated
    /**
     * Remove Me
     */
    @JsonProperty("CustomPizzaImage")
    private String customPizzaImage;
  
    /**
     * Default constructor.
     */
    public CustomPizza(){
      this.customPizzaName = "Unknown";
      this.sizeName = "Unknown";
      this.baseName = "Unknown";
      this.crustName = "Unknown";
      this.sauceName = "Unknown";
      this.cheeseName = "Unknown";
  }
  
    /**
     * Overloaded constructor. Sets all attributes of {@link Base#} on creation
     * @param customPizzaName   Default value of {@link CustomPizza#customPizzaName}
     * @param sizeName          Default value of {@link CustomPizza#sizeName}
     * @param crustName         Default value of {@link CustomPizza#crustName}
     * @param baseName          Default value of {@link CustomPizza#baseName}
     * @param sauceName         Default value of {@link CustomPizza#sauceName}
     * @param cheeseName        Default value of {@link CustomPizza#cheeseName}
     */
    public CustomPizza(String customPizzaName, String sizeName, String crustName, String baseName, String sauceName, String cheeseName){
      this.customPizzaName = customPizzaName;
      this.sizeName = sizeName;
      this.baseName = baseName;
      this.crustName = crustName;
      this.sauceName = sauceName;
      this.cheeseName = cheeseName;
  }

    /**
     * @return  Current value of {@link CustomPizza#customPizzaName}
     */
    public String getCustomPizzaName() {
        return customPizzaName;
    }

    /**
     * Sets value of {@link CustomPizza#customPizzaName}
     * @param customPizzaName New value of {@link CustomPizza#customPizzaName}
     */
    public void setCustomPizzaName(String customPizzaName) {
        this.customPizzaName = customPizzaName;
    }

    /**
     * @return  Current value of {@link CustomPizza#sizeName}
     */
    public String getSizeName() {
        return sizeName;
    }

    /**
     * Sets value of {@link CustomPizza#customPizzaName}
     * @param sizeName New value of {@link CustomPizza#customPizzaName}
     */
    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    /**
     * @return  Current value of {@link CustomPizza#baseName}
     */
    public String getBaseName() {
        return baseName;
    }

    /**
     * Sets value of {@link CustomPizza#baseName}
     * @param baseName New value of {@link CustomPizza#baseName}
     */
    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    /**
     * @return  Current value of {@link CustomPizza#crustName}
     */
    public String getCrustName() {
        return crustName;
    }

    /**
     * Sets value of {@link CustomPizza#crustName}
     * @param crustName New value of {@link CustomPizza#crustName}
     */
    public void setCrustName(String crustName) {
        this.crustName = crustName;
    }

    /**
     * @return  Current value of {@link CustomPizza#sauceName}
     */
    public String getSauceName() {
        return sauceName;
    }

    /**
     * Sets value of {@link CustomPizza#sauceName}
     * @param sauceName New value of {@link CustomPizza#sauceName}
     */
    public void setSauceName(String sauceName) {
        this.sauceName = sauceName;
    }

    /**
     * @return  Current value of {@link CustomPizza#cheeseName}
     */
    public String getCheeseName() {
        return cheeseName;
    }

    /**
     * Sets value of {@link CustomPizza#cheeseName}
     * @param cheeseName New value of {@link CustomPizza#cheeseName}
     */
    public void setCheeseName(String cheeseName) {
        this.cheeseName = cheeseName;
    }
}