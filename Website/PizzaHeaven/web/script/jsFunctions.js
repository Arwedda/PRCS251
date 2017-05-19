$(document).ready(function(){
    $(".mobileMenu").hide();
    $(".mobilelogin").hide();
    $(".descriptiondiv").hide();
    
    $('#email2').on('change',function() {
        var email = $('#email').val();
        var email2 = $('#email2').val();
        if(email !== "" && email2 !== ""){
                if(email !== email2){
                    $('#email2').val("");
                }
        }
    });
        
    $('#password2').on('change',function() {
        var password = $('#password').val();
        var password2 = $('#password2').val();
            if(password !== password2){
                $('#password').val("");
                $('#password2').val("");
            } else if (password !== "" && password2 !== ""){
                $('#submit').show();
            }  
    });    
    
  $('input').bind("cut copy paste",function(e) {
      e.preventDefault();
  });
});

//Mobile drop down menu
$(function() {
    $('.menuDown').click(function(){
        if($('.mobileMenu').is(":visible")){
            $('.mobileMenu').hide();
            $('.menuDown').attr('src','img/menu-down.png');
        } else {
            $('.mobileMenu').show();
            $('.menuDown').attr('src','img/menu-up.png');
        }
    });
});

//Mobile login function
$(function() {
    $('#userIcon').click(function(){
        if($('.mobilelogin').is(":visible")){
            $('.mobilelogin').hide();
            $('.bottombanner').show();
        } else {
            $('.mobilelogin').show();
            $('.bottombanner').hide();
            $('.mobileMenu').hide();
            $('.menuDown').attr('src','img/menu-down.png');
        }
    });
});

//hover on add to basket button on menu page
$(function(){
    $(".addBasket").hover(function() {
	$(this).attr("src","img/addBasketHover.png");
    },
    function() {
        $(this).attr("src","img/addBasket.png");
    });
});

//info on click function on pizza page
$(function() {
    $('.infoimg').click(function(){
        var reference = $(this).attr('class');
        var manipulated = reference.substring(8,12);
        if($('.descriptiondiv.'+ manipulated +'').is(":visible")){
            $('.descriptiondiv.'+ manipulated +'').hide();
            $('.infoimg.'+ manipulated +'').attr('src','img/info.png');
            var brightness = "brightness(100%)";
            $('.menuimg.' + manipulated+ '').css("filter",brightness);
        } else {
            $('.descriptiondiv.'+ manipulated +'').show();
            var brightness = "brightness(30%)";
            $('.menuimg.' + manipulated+ '').css("filter",brightness);
            $('.infoimg.'+ manipulated +'').attr('src','img/infoClose.png');
        }
    });
});

//disable enter keypress submitting forms
$(function() {
    $(document).keypress(function(e) {
        var key = e.keyCode;
        if (key === 13 || key === 34 || key === 39 || key === 59) {
            e.preventDefault();
            return false;
        }
    });
});