$(document).ready(function () {
    $('.addBasket').click(function() {
        var loadBasket = sessionStorage.getItem("basket");
        var loadDrinks = sessionStorage.getItem("drinks");
        var basketInfo = JSON.parse(loadBasket);
        var drinks = JSON.parse(loadDrinks);
        var newItem = true;

        var reference = $(this).attr('class');
        var i = reference.substring(10,13);
        var quantity = Number($(".quantity." + i + " option:selected").text());
        var selected = $(".price." + i + " option:selected").val();
        var selectsplit = selected.split("^");
        var name = selectsplit[0];
        var price = Number(selectsplit[1]).toFixed(2);
        
        
        name = $(".name."+i).text() + " (" + name + ")";

        var reference = $(this).attr('class');
        var manipulated = reference.substring(10,13);
        
        $('.basketAdd.'+ manipulated +'').show();
        $('.infoimg.'+ manipulated +'').hide();

        if (drinks === null){
            drinks = [];
        }
        
        if (basketInfo === null){
            basketInfo = [2];
            basketInfo[0] = 0;
            basketInfo[1] = 0.0;
        }
        
        for (var i = 0; i < drinks.length; i++){
            if (drinks[i][0] === name){
                Number(drinks[i][1] += quantity);
                newItem = false;
                basketInfo[0] += quantity;
                basketInfo[1] += quantity*price;
                $('h4.basketAddMessage.'+manipulated).text('There are now ' +drinks[i][1]+' in your basket');
            }
        }

        if (newItem){
            var newDrink = [];
            newDrink.push(name);
            newDrink.push(quantity);
            newDrink.push(price);
            drinks.push(newDrink);
            basketInfo[0] += quantity;
            basketInfo[1] += quantity*price;
            $('h4.basketAddMessage.'+manipulated).text('There is now ' +drinks[drinks.length-1][1]+' in your basket');
        }

        if (basketInfo[0] > 9){
            $('.basketQuantity').css("padding-left","13.5px");
        }
        $('.basketQuantity').text(basketInfo[0]);
        $('.basketTotal').text('£'+basketInfo[1].toFixed(2));
    

        window.sessionStorage.setItem("basket", JSON.stringify(basketInfo));
        window.sessionStorage.setItem("drinks", JSON.stringify(drinks));
        setTimeout(function() {
        $('.basketAdd.'+ manipulated +'').hide();
        $('.infoimg.'+ manipulated +'').show();
             }, 5000);
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