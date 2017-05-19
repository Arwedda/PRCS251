$(document).ready(function () {
    $('.addBasket').click(function() {
        var loadBasket = sessionStorage.getItem("basket");
        var loadPizzas = sessionStorage.getItem("pizzas");
        var basketInfo = JSON.parse(loadBasket);
        var pizzas = JSON.parse(loadPizzas);
        var newItem = true;

        var reference = $(this).attr('class');
        var i = reference.substring(10,13);
        var selected = $(".price." + i + " option:selected").val();
        var name = selected.substring(0, 6);
        
        if (name !== "Medium"){
            name = name.substring(0, 5);
        }
        
        name = $(".name."+i).text() + " (" + name + ")";
        
        var price = Number(selected.substring(6, 12));
        var reference = $(this).attr('class');
        var manipulated = reference.substring(10,13);
        
        $('.basketAdd.'+ manipulated +'').show();
        $('.infoimg.'+ manipulated +'').hide();

        if (pizzas === null){
            pizzas = [];
        }

        if (basketInfo === null){
            basketInfo = [2];
            basketInfo[0] = 0;
            basketInfo[1] = 0.0;
        }
        
        for (var i = 0; i < pizzas.length; i++){
            if (pizzas[i][0] === name & pizzas[i][2] === price){
                Number(pizzas[i][1] += 1);
                newItem = false;
                basketInfo[0] += 1;
                basketInfo[1] += price;
                $('h4.basketAddMessage.'+manipulated).text('There are now ' +pizzas[i][1]+' in your basket');
            }
        }

        if (newItem){
            var newPizza = [];
            newPizza.push(name);
            newPizza.push(1);
            newPizza.push(price);
            pizzas.push(newPizza);
            basketInfo[0] += 1;
            basketInfo[1] += price;
            $('h4.basketAddMessage.'+manipulated).text('There is now ' +pizzas[pizzas.length-1][1]+' in your basket');
        }
        
        if (basketInfo[0] > 9){
            $('.basketQuantity').css("padding-left","13.5px");
        }
        $('.basketQuantity').text(basketInfo[0]);
        $('.basketTotal').text('£'+basketInfo[1].toFixed(2));
    

        window.sessionStorage.setItem("basket", JSON.stringify(basketInfo));
        window.sessionStorage.setItem("pizzas", JSON.stringify(pizzas));
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