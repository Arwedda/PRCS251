$(document).ready(function () {
    $('.addBasket').click(function() {
        var loadBasket = sessionStorage.getItem("basket");
        var basketInfo = JSON.parse(loadBasket);
        var loadSides = sessionStorage.getItem("sides");
        var sides = JSON.parse(loadSides);
        var newItem = true;

        var reference = $(this).attr('class');
        var i = reference.substring(10,13);
        var quantity = Number($(".quantity." + i + " option:selected").text());
        var name = $(".name."+i).text();
        var price = Number($(".price."+i).text()).toFixed(2);
        
        var reference = $(this).attr('class');
        var manipulated = reference.substring(10,13);
        
        $('.basketAdd.'+ manipulated +'').show();
        $('.infoimg.'+ manipulated +'').hide();
        
        if (sides === null){
            sides = [];
        }
        
        if (basketInfo === null){
            basketInfo = [2];
            basketInfo[0] = 0;
            basketInfo[1] = 0.0;
        }

        for (var i = 0; i < sides.length; i++){
            if (sides[i][0] === name){
                Number(sides[i][1] += quantity);
                newItem = false;
                basketInfo[0] += quantity;
                basketInfo[1] += quantity*price;
                $('h4.basketAddMessage.'+manipulated).text('There are now ' +sides[i][1]+' in your basket');
            }
        }

        if (newItem){
            var newSide = [];
            newSide.push(name);
            newSide.push(quantity);
            newSide.push(price);
            sides.push(newSide);
            basketInfo[0] += quantity;
            basketInfo[1] += quantity*price;
            $('h4.basketAddMessage.'+manipulated).text('There is now ' +sides[sides.length-1][1]+' in your basket');
        }
        if (basketInfo[0] > 9){
            $('.basketQuantity').css("padding-left","13.5px");
        }
        $('.basketQuantity').text(basketInfo[0]);
        $('.basketTotal').text('£'+basketInfo[1].toFixed(2));

        window.sessionStorage.setItem("basket", JSON.stringify(basketInfo));
        window.sessionStorage.setItem("sides", JSON.stringify(sides));
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