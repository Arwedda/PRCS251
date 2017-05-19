$(document).ready(function () {
    $('.size.Small').prop('checked', true);
    $('.base.Normal').prop('checked', true);
    $('.crust.Normal').prop('checked', true);
    $('.sauce.Tomato').prop('checked', true);
    $('.cheese.Normal').prop('checked', true);
    $('.addBasket').click(function() {
        var loadBasket = sessionStorage.getItem("basket");
        var loadCustoms = sessionStorage.getItem("customs");
        var basketInfo = JSON.parse(loadBasket);
        var customs = JSON.parse(loadCustoms);
        var newItem = true;
        
        var name = "";
        var size = $("#size input[type='radio']:checked").val();
        var split = size.split("^");
        size = split[0];
        name += size + "^";
        var sizePrice = Number(split[1]).toFixed(2);
        
        var base = $("#base input[type='radio']:checked").val();
        split = base.split("^");
        base = split[0];
        name += base + "^";
        var basePrice = Number(split[1]).toFixed(2);
        
        var crust = $("#crust input[type='radio']:checked").val();
        split = crust.split("^");
        crust = split[0];
        name += crust + "^";
        var crustPrice = Number(split[1]).toFixed(2);
        
        var sauce = $("#sauce input[type='radio']:checked").val();
        split = sauce.split("^");
        sauce = split[0];
        name += sauce + "^";
        var saucePrice = Number(split[1]).toFixed(2);
        
        var cheese = $("#cheese input[type='radio']:checked").val();
        split = cheese.split("^");
        cheese = split[0];
        name += cheese + "^";
        var cheesePrice = Number(split[1]).toFixed(2);
        
        var toppings = [];
        var toppingString = "";
        var toppingsPrice = 0.00;
        $("#topping input[type='checkbox']:checked").each(function(){
           split = $(this).val().split("^");
           toppings.push(split[0]);
           name += (split[0] + "^");
           toppingString += split[0] + ", ";
           toppingsPrice += parseFloat(split[1]);
        });
        if (toppings.length > 0){
            toppingsPrice = toppingsPrice.toFixed(2);
            toppingString = toppingString.slice(0, toppingString.length - 2);
            toppingString += ".";
        } else {
            toppings.push("None");
            toppingString = "None.";
        }
        
        var totalPrice = Number(sizePrice) + Number(basePrice) + Number(crustPrice) + Number(saucePrice) + Number(cheesePrice) + Number(toppingsPrice);
        
        if (customs === null){
            customs = [];
        }
        
        if (basketInfo === null){
            basketInfo = [2];
            basketInfo[0] = 0;
            basketInfo[1] = 0.0;
        }
        
        for (var i = 0; i < customs.length; i++){
            if (customs[i][0] === name){
                Number(customs[i][6] += 1);
                newItem = false;
                basketInfo[0] += 1;
                basketInfo[1] += totalPrice;
            }
        }
        
        if (newItem){
            var newCustom = [];
            newCustom.push(name);
            newCustom.push(size);
            newCustom.push(base);
            newCustom.push(crust);
            newCustom.push(sauce);
            newCustom.push(cheese);
            newCustom.push(1);
            newCustom.push(totalPrice);
            newCustom.push(toppings);
            newCustom.push(size + " Custom Pizza. " + base + " base, " + crust + " crust, " + sauce + " sauce, " + cheese + " cheese. Toppings: " + toppingString);
            customs.push(newCustom);
            
            basketInfo[0] += 1;
            basketInfo[1] += totalPrice;
        }
        
        if (basketInfo[0] > 9){
            $('.basketQuantity').css("padding-left","13.5px");
        }
        $('.basketQuantity').text(basketInfo[0]);
        $('.basketTotal').text('£'+basketInfo[1].toFixed(2));
    

        window.sessionStorage.setItem("basket", JSON.stringify(basketInfo));
        window.sessionStorage.setItem("customs", JSON.stringify(customs));
    });

    var loadBasket = sessionStorage.getItem("basket");
    var basketInfo = JSON.parse(loadBasket);
    
    if (basketInfo === null){
            basketInfo = [2];
            basketInfo[0] = 0;
            basketInfo[1] = 0.0;
    }   
    
    if (basketInfo[0] > 9){
            $('.basketQuantity').css("padding-left","13.5px");
        }
    $('.basketQuantity').text(basketInfo[0]);
    $('.basketTotal').text('£'+basketInfo[1].toFixed(2));
});