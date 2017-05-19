$(document).ready(function () {
    $('#pay').click(function() {
        sessionStorage.removeItem("orderedCustoms");
        sessionStorage.removeItem("orderedDrinks");
        sessionStorage.removeItem("orderedPizzas");
        sessionStorage.removeItem("orderedSides");  
    });
});