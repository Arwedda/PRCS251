$(document).ready(function(){
    var loadCustoms = sessionStorage.getItem("customs");
    var loadDrinks = sessionStorage.getItem("drinks");
    var loadPizzas = sessionStorage.getItem("pizzas");
    var loadSides = sessionStorage.getItem("sides");
    
    drawBasket(loadCustoms, loadDrinks, loadPizzas, loadSides);
});

$(function() {
    $('.minus').click(function(){
        var loadBasket = sessionStorage.getItem("basket");
        var loadCustoms = sessionStorage.getItem("customs");
        var loadDrinks = sessionStorage.getItem("drinks");
        var loadPizzas = sessionStorage.getItem("pizzas");
        var loadSides = sessionStorage.getItem("sides");
        var basket = JSON.parse(loadBasket);
        var customs = JSON.parse(loadCustoms);
        var drinks = JSON.parse(loadDrinks);
        var pizzas = JSON.parse(loadPizzas);
        var sides = JSON.parse(loadSides);

        var reference = $(this).attr('class');
        var manipulated = reference.substring(6,10);
        var quantity = $(".quantity." + manipulated).text();

        if (quantity > 1){
            var price = $(".itemprice." + manipulated).text();
            var total = Number($("#total").text());
            var name = $(".name." + manipulated).text();
            var found = false;

            if (basket === null){
                basket = [2];
                basket[0] = 0;
                basket[1] = 0.0;
            }
            
            basket[0] -= 1;
            basket[1] -= price;
            sessionStorage.setItem("basket", JSON.stringify(basket));

            quantity -= 1;
            price = price * quantity;

            total -= Number($(".lineprice." + manipulated).text());
            $(".lineprice." + manipulated).text(price.toFixed(2));
            total += Number($(".lineprice." + manipulated).text());

            $(".quantity."+manipulated).text(quantity);
            $("#total").text(total.toFixed(2));

            if (customs === null){
                customs = [];
            }
            if (drinks === null){
                drinks = [];
            }
            if (pizzas === null){
                pizzas = [];
            }
            if (sides === null){
                sides = [];
            }

            for (var i = 0; i < customs.length; i++){
                if (customs[i][9] === name){
                    customs[i][6] -= 1;
                    sessionStorage.setItem("customs", JSON.stringify(customs));
                    loadCustoms = JSON.stringify(customs);
                    found = true;
                    break;
                }
            }
            if (!found){
                for (var i = 0; i < drinks.length; i++){
                    if (drinks[i][0] === name){
                        drinks[i][1] -= 1;
                        sessionStorage.setItem("drinks", JSON.stringify(drinks));
                        loadDrinks = JSON.stringify(drinks);
                        found = true;
                        break;
                    }
                }
            }
            if (!found){
                for (var i = 0; i < pizzas.length; i++){
                    if (pizzas[i][0] === name){
                        pizzas[i][1] -= 1;
                        sessionStorage.setItem("pizzas", JSON.stringify(pizzas));
                        loadPizzas = JSON.stringify(pizzas);
                        found = true;
                        break;
                    }
                }
            }
            if (!found){
                for (var i = 0; i < sides.length; i++){
                    if (sides[i][0] === name){
                        sides[i][1] -= 1;
                        sessionStorage.setItem("sides", JSON.stringify(sides));
                        loadSides = JSON.stringify(sides);
                        break;
                    }
                }
            }
            fillForm(loadCustoms, loadDrinks, loadPizzas, loadSides);
            drawBanner();
        }
    });

    $('.plus').click(function(){
        var loadBasket = sessionStorage.getItem("basket");
        var loadCustoms = sessionStorage.getItem("customs");
        var loadDrinks = sessionStorage.getItem("drinks");
        var loadPizzas = sessionStorage.getItem("pizzas");
        var loadSides = sessionStorage.getItem("sides");
        var basket = JSON.parse(loadBasket);
        var customs = JSON.parse(loadCustoms);
        var drinks = JSON.parse(loadDrinks);
        var pizzas = JSON.parse(loadPizzas);
        var sides = JSON.parse(loadSides);

        var reference = $(this).attr('class');
        var manipulated = reference.substring(5,9);
        var quantity = Number($(".quantity." + manipulated).text());

        if (quantity < 99){
            var price = $(".itemprice." + manipulated).text();
            var total = Number($("#total").text());
            var name = $(".name." + manipulated).text();
            var found = false;

            if (basket === null){
                basket = [2];
                basket[0] = 0;
                basket[1] = 0.0;
            }
            
            basket[0] += 1;
            basket[1] += Number(price);
            sessionStorage.setItem("basket", JSON.stringify(basket));

            quantity += 1;
            price = price * quantity;

            total -= Number($(".lineprice." + manipulated).text());
            $(".lineprice." + manipulated).text(price.toFixed(2));
            total += Number($(".lineprice." + manipulated).text());

            $(".quantity." + manipulated).text(quantity);
            $("#total").text(total.toFixed(2));

            if (customs === null){
                customs = [];
            }
            if (drinks === null){
                drinks = [];
            }
            if (pizzas === null){
                pizzas = [];
            }
            if (sides === null){
                sides = [];
            }

            for (var i = 0; i < customs.length; i++){
                if (customs[i][9] === name){
                    customs[i][6] += 1;
                    sessionStorage.setItem("customs", JSON.stringify(customs));
                    loadCustoms = JSON.stringify(customs);
                    found = true;
                    break;
                }
            }
            if (!found){
                for (var i = 0; i < drinks.length; i++){
                    if (drinks[i][0] === name){
                        drinks[i][1] += 1;
                        sessionStorage.setItem("drinks", JSON.stringify(drinks));
                        loadDrinks = JSON.stringify(drinks);
                        found = true;
                        break;
                    }
                }
            }
            if (!found){
                for (var i = 0; i < pizzas.length; i++){
                    if (pizzas[i][0] === name){
                        pizzas[i][1] += 1;
                        sessionStorage.setItem("pizzas", JSON.stringify(pizzas));
                        loadPizzas = JSON.stringify(pizzas);
                        found = true;
                        break;
                    }
                }
            }
            if (!found){
                for (var i = 0; i < sides.length; i++){
                    if (sides[i][0] === name){
                        sides[i][1] += 1;
                        sessionStorage.setItem("sides", JSON.stringify(sides));
                        loadSides = JSON.stringify(sides);
                        break;
                    }
                }
            }
            fillForm(loadDrinks, loadPizzas, loadSides);
            drawBanner();
        }
    });

    $('.remove').click(function(){
        var loadBasket = sessionStorage.getItem("basket");
        var loadCustoms = sessionStorage.getItem("customs");
        var loadDrinks = sessionStorage.getItem("drinks");
        var loadPizzas = sessionStorage.getItem("pizzas");
        var loadSides = sessionStorage.getItem("sides");
        var basket = JSON.parse(loadBasket);
        var customs = JSON.parse(loadCustoms);
        var drinks = JSON.parse(loadDrinks);
        var pizzas = JSON.parse(loadPizzas);
        var sides = JSON.parse(loadSides);
        
        var reference = $(this).attr('class');
        var manipulated = reference.substring(7,11);
        var name = $(".name." + manipulated).text();
        var found = false;
        var quantity = Number($(".quantity." + manipulated).text());
        var price = $(".itemprice." + manipulated).text();

        if (customs === null){
            customs = [];
        }
        if (drinks === null){
            drinks = [];
        }
        if (pizzas === null){
            pizzas = [];
        }
        if (sides === null){
            sides = [];
        }
        
        if (basket === null){
            basket = [2];
            basket[0] = 0;
            basket[1] = 0.0;
        }
 
        basket[0] -= quantity;
        basket[1] -= (quantity * price);
        sessionStorage.setItem("basket", JSON.stringify(basket));
        
        for (var i = 0; i < customs.length; i++){
            if (customs[i][9] === name){
                customs.splice(i, 1);
                sessionStorage.setItem("customs", JSON.stringify(customs));
                loadCustoms = JSON.stringify(customs);
                found = true;
                break;
            }
        }
        if (!found){
            for (var i = 0; i < drinks.length; i++){
                if (drinks[i][0] === name){
                    drinks.splice(i, 1);
                    sessionStorage.setItem("drinks", JSON.stringify(drinks));
                    loadDrinks = JSON.stringify(drinks);
                    found = true;
                    break;
                }
            }
        }
        if (!found){
            for (var i = 0; i < pizzas.length; i++){
                if (pizzas[i][0] === name){
                    pizzas.splice(i, 1);
                    sessionStorage.setItem("pizzas", JSON.stringify(pizzas));
                    loadPizzas = JSON.stringify(pizzas);
                    found = true;
                    break;
                }
            }
        }
        if (!found){
            for (var i = 0; i < sides.length; i++){
                if (sides[i][0] === name){
                    sides.splice(i, 1);
                    sessionStorage.setItem("sides", JSON.stringify(sides));
                    loadSides = JSON.stringify(sides);
                    break;
                }
            }
        }
        location.reload(true);
    });
});
    
function drawBasket(loadCustoms, loadDrinks, loadPizzas, loadSides){
    var customs = JSON.parse(loadCustoms);
    var drinks = JSON.parse(loadDrinks);
    var pizzas = JSON.parse(loadPizzas);
    var sides = JSON.parse(loadSides);
    
    if (customs === null){
        customs = [];
    }
    if (drinks === null){
        drinks = [];
    }
    if (pizzas === null){
        pizzas = [];
    }
    if (sides === null){
        sides = [];
    }

    if ((customs.length + drinks.length + pizzas.length + sides.length) > 0){
        var lineTotal = 0.0;
        var total = 0.0;
        var j = 0;
        var basketHTML = "<h4><table><tr><th></th><th>Name</th><th>Price</th><th id='quantity'>Quantity</th><th>Line Price</th></tr>";
        
        if (pizzas.length > 0){
            basketHTML += "<tr><td id='label'><b>Pizzas</b></td></tr>";
            for (var i = 0; i < pizzas.length; i++){
                lineTotal = Number(pizzas[i][1] * pizzas[i][2]);
                basketHTML += "<tr><td id='label'></td><td class='name " + j + "'>" + pizzas[i][0] + "</td>";
                basketHTML += "<td>£<div class='itemprice " + j + "'>" + pizzas[i][2].toFixed(2) + "</div></td>";
                basketHTML += "<td><input type='image' class='minus " + j + "' src='img/minus.png' height='25px' width='25px' />";
                basketHTML += "<div class='quantity " + j + "'>" + pizzas[i][1] + "</div>";
                basketHTML += "<input type='image' class='plus " + j + "' src='img/plus.png' height='25px' width='25px' />";
                basketHTML += "</td><td>£<div class='lineprice " + j + "'>" + lineTotal.toFixed(2) + "</div></td>";
                basketHTML += "<td><input type='image' class='remove " + j + "' src='img/infoClose.png' height='25px' width='25px' /></td></tr>";
                total += lineTotal;
                j++;
            }
        basketHTML += "<tr><td></td><td><br /></td><td></td><td></td><td></td></tr>";
        }
        
        if (customs.length > 0){
            basketHTML += "<tr><td id='label'><b>Custom Pizzas</b></td></tr>";
            for (var i = 0; i < customs.length; i++){
                lineTotal = Number(customs[i][6] * customs[i][7]);
                basketHTML += "<tr><td id='label'></td><td class='name " + j + "'>" + customs[i][9] + "</td>";
                basketHTML += "<td>£<div class='itemprice " + j + "'>" + customs[i][7].toFixed(2) + "</div></td>";
                basketHTML += "<td><input type='image' class='minus " + j + "' src='img/minus.png' height='25px' width='25px' />";
                basketHTML += "<div class='quantity " + j + "'>" + customs[i][6] + "</div>";
                basketHTML += "<input type='image' class='plus " + j + "' src='img/plus.png' height='25px' width='25px' />";
                basketHTML += "</td><td>£<div class='lineprice " + j + "'>" + lineTotal.toFixed(2) + "</div></td>";
                basketHTML += "<td><input type='image' class='remove " + j + "' src='img/infoClose.png' height='25px' width='25px' /></td></tr>";
                total += lineTotal;
                j++;
            }
        }

        if (sides.length > 0){
            basketHTML += "<tr><td id='label'><b>Sides</b></td></tr>";
            for (var i = 0; i < sides.length; i++){
                lineTotal = Number(sides[i][1]*sides[i][2]);
                basketHTML += "<tr><td id='label'></td><td class='name " + j + "'>" + sides[i][0] + "</td>";
                basketHTML += "<td>£<div class='itemprice " + j + "'>" + Number(sides[i][2]).toFixed(2) + "</div></td>";
                basketHTML += "<td><input type='image' class='minus " + j + "' src='img/minus.png' height='25px' width='25px' />";
                basketHTML += "<div class='quantity " + j + "'>" + sides[i][1] + "</div>";
                basketHTML += "<input type='image' class='plus " + j + "' src='img/plus.png' height='25px' width='25px' />";
                basketHTML += "</td><td>£<div class='lineprice " + j + "'>" + lineTotal.toFixed(2) + "</div></td>";
                basketHTML += "<td><input type='image' class='remove " + j + "' src='img/infoClose.png' height='25px' width='25px' /></td></tr>";
                total += lineTotal;
                j++;
            }
        basketHTML += "<tr><td></td><td><br /></td><td></td><td></td><td></td></tr>";
        }
        
        if (drinks.length > 0){
            basketHTML += "<tr><td id='label'><b>Drinks</b></td></tr>";
            for (var i = 0; i < drinks.length; i++){
                lineTotal = Number(drinks[i][1]*drinks[i][2]);
                basketHTML += "<tr><td id='label'></td><td class='name " + j + "'>" + drinks[i][0] + "</td>";
                basketHTML += "<td>£<div class='itemprice " + j + "'>" + Number(drinks[i][2]).toFixed(2) + "</div></td>";
                basketHTML += "<td><input type='image' class='minus " + j + "' src='img/minus.png' height='25px' width='25px' />";
                basketHTML += "<div class='quantity " + j + "'>" + drinks[i][1] + "</div>";
                basketHTML += "<input type='image' class='plus " + j + "' src='img/plus.png' height='25px' width='25px' />";
                basketHTML += "</td><td>£<div class='lineprice " + j + "'>" + lineTotal.toFixed(2) + "</div></td>";
                basketHTML += "<td><input type='image' class='remove " + j + "' src='img/infoClose.png' height='25px' width='25px' /></td></tr>";
                total += lineTotal;
                j++;
            }
        basketHTML += "<tr><td id='label'></td><td><br /></td><td></td><td></td><td></td></tr>";
        }
        
        basketHTML += "<tr><td id='label'></td><td><br /></td><td></td><td></td><td></td></tr>";
        basketHTML += "<tr><td id='label'></td><td></td><td></td><td><b>Total Price:</b></td><td><b>£<div id='total'>" + total.toFixed(2) + "</div></b></td></tr>";
        basketHTML += "<tr><td id='label'></td><td><br /></td><td></td><td></td><td></td></tr>";
        basketHTML += "<tr><td id='label'></td><td></td><td></td><td id='proceed'><form name='order' id='order' method='post' action='BuildBasketServlet'></form></td></tr></table></h4>";
        drawBanner();
        basketHTML += "<footer><h10>Legal details.Social media links.</h10></footer>";
        $('#basket').html(basketHTML);
        fillForm(loadCustoms, loadDrinks, loadPizzas, loadSides);
    }
}

function fillForm(customs, drinks, pizzas, sides){
    var formHTML;
    formHTML = "<input type='hidden' name='customs' value='" + customs + "'/>";
    formHTML += "<input type='hidden' name='drinks' value='" + drinks + "' />";
    formHTML += "<input type='hidden' name='pizzas' value='" + pizzas + "' />";
    formHTML += "<input type='hidden' name='sides' value='" + sides + "' />";
    formHTML += "<input id='submit' type='image' src='img/btnDelivery.png' border='0' name='submit' alt='Proceed to Delivery' height='90px' width='180px'>";
    $('#order').html(formHTML);
}

function drawBanner(){
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
    $('.basketTotal').text('£' + basketInfo[1].toFixed(2));
}