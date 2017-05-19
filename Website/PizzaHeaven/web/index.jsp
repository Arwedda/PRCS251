<%@page import="pizzaheaven.models.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Lobster" />
        <link rel="stylesheet" type="text/css" href="css/banner.css">
        <link rel="stylesheet" type="text/css" href="css/mediaqueries.css">
        <link rel="stylesheet" type="text/css" href="css/footer.css">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <link rel="stylesheet" type="text/css" href="css/menu.css">
        <script
	src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous"></script>
	<script
	src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
	integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="
	crossorigin="anonymous"></script>
        <script type="text/javascript" src="script/jsFunctions.js"></script>
        <script type="text/javascript" src="script/banner.js"></script>
        <title>Pizza Heaven</title>
    </head>
    <body>
        <div id="mobile">
            <div class="banner">
                <div class="subbanner">
                    <div class="topbanner icon">
                            <img id="userIcon" src="img/usericon.png"/>
                    </div>
                    <a href="index.jsp"><div  class="topbanner logo">
                            <img id="logo" src="img/PizzaHeavenLogo1.png" />
                    </div></a>
                    <a href="login.jsp"><div class="topbanner icon">
                            <img id="basket" src="img/basket.png"/>
                    </div></a>
                </div><br />
                <div class="subbanner">
                    <div class="mobilelogin">
                        <form name="login" method="post" action="LoginServlet">
                            <div class="loginText">
                                <h3>Email: &nbsp;</h3> <input type="text" name="email" ><br/>
                                <h3>Password: &nbsp;</h3> <input type="password" name="password" ><br/>
                            </div>
                <input type="submit" value="Login" >
            </form>
                    </div>
                    <div class="bottombanner"><img class="menuDown" id="subimg" src="img/menu-down.png" /></div>
                    <div class="bottombanner"><img id="subimg" src="img/offers.png" /></div>
                </div>
            </div>
            <div class="mobileMenu">
                <a href="PizzaMenuServlet"><div class="pizzaMobile">
                    <img id="pizzaMobile" src="img/mobilePizza.png">
                    </div></a>
                <a href="SideMenuServlet"><div class="sidesMobile">
                    <img id="sidesMobile" src="img/mobileSides.png">
                    </div></a>
                <a href="DrinkMenuServlet"><div class="drinksMobile">
                    <img id="drinkMobile" src="img/mobileDrinks.png">
                    </div></a>
            </div>
            <div id="offer">

            </div>
        </div>
        <div id="tablet">
            <div class="banner">
                <div class="subbanner">
                    <a href="login.jsp"><div class="topbanner icon">
                            <img id="userIcon" src="img/usericon.png"/>
                    </div></a>
                    <a href="index.jsp"><div  class="topbanner logo">
                            <img id="logo" src="img/PizzaHeavenLogo1.png" />
                    </div></a>
                    <a href="login.jsp"><div class="topbanner icon">
                            <img id="basket" src="img/basket.png"/>
                    </div></a>
                </div><br />
                <div class="subbanner">
                    <div class="bottombanner"><img class="menuDown" id="subimg" src="img/menu-down.png" /></div>
                    <div class="bottombanner"><img id="subimg" src="img/offers.png" /></div>
                </div>
            </div>
            <div class="mobileMenu">
                <a href="PizzaMenuServlet"><div class="pizzaMobile">
                    <img id="pizzaMobile" src="img/mobilePizza.png">
                    </div></a>
                <a href="SideMenuServlet"><div class="sidesMobile">
                    <img id="sidesMobile" src="img/mobileSides.png">
                    </div></a>
                <a href="DrinkMenuServlet"><div class="drinksMobile">
                    <img id="drinkMobile" src="img/mobileDrinks.png">
                    </div></a>
            </div>
            <div id="offer">

            </div>
        </div>
        <div id="main">
            <header class="banner">
                <header class="subbanner">
                    <a href="index.jsp"><div class="topbanner logo">
                            <img id="mainLogo" src="img/PizzaHeavenLogo1.png"/>
                    </div></a>
                    <%
                        try{
                            session = request.getSession();
                            session.setAttribute("previousPage", request.getRequestURL());
                            Customer customer = (Customer) session.getAttribute("customer");
                            if (!customer.getCustomerID().equals("") || !customer.getCustomerID().equals(null)){
                                out.println("<div class='topbanner icon'><a href='LogOutServlet'>");
                                out.println("<h2>Logout</h2></a></div><div class='topbanner icon'><a href='account.jsp'>");
                                out.println("<h2>" + customer.getFirstName() + "'s Account</h2></a></div>");
                                out.println("<a href='basket.jsp'><div class='topbanner basket'><div class='basketIcon'><img src='img/basket.png' height='45px' width='45px'><h3 class='basketQuantity'>0</h3></div>");
                                out.println("<h3 class='basketTotal'>£0.00</h3></div></a>");
                            }
                        } catch(Exception ex){
                            out.println("<div class='topbanner icon'>");
                            out.println("<a href='login.jsp'><h2>Login</h2></a></div><div class='topbanner icon'>");
                            out.println("<a href='registration.jsp'><h2>Register</h2></a></div>");
                            out.println("<a href='basket.jsp'><div class='topbanner basket'><div class='basketIcon'><img src='img/basket.png' height='45px' width='45px'><h3 class='basketQuantity'>0</h3></div>");
                            out.println("<h3 class='basketTotal'>£0.00</h3></div></a>");
                        }
                    %>
                </header><br />
                <header id="menubanner" class="subbanner" style="padding-bottom: 0.7%">
                     <a href="PizzaMenuServlet"><div id="b">
                        <img src="img/pizzaIcon.png"/><h1>Pizza</h1>
                    </div></a>
                    <a href="SideMenuServlet"><div id="b">
                        <img src="img/sidesIcon.png"/><h1>Sides</h1>
                    </div></a>
                    <a href="DrinkMenuServlet"><div id="b">
                            <img src="img/drinksIcon.png"/><h1>Drinks</h1>
                    </div></a>
                    <a href="OfferServlet"><div id="b">
                        <h1>Offers</h1>
                    </div></a>
                </header>
            </header>
        </div>
        <div id ="welcome">
            <h8>Do you fancy a pizza heaven?</h8>
        </div>
        <div id="offer">
            <img src="img/banner1.png"/><br />
            <footer>
            <h10>
                Legal details.
                Social media links.
            </h10>
            </footer>
        </div>
    </body>
</html>