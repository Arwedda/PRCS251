<%@page import="pizzaheaven.models.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pizza Heaven</title>
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel=stylesheet type="text/css" href="css/banner.css">
        <link rel=stylesheet type="text/css" href="css/mediaqueries.css">
        <link rel=stylesheet type="text/css" href="css/footer.css">
        <link rel=stylesheet type="text/css" href="css/delivery.css">
        <link rel=stylesheet type="text/css" href="css/menu.css">
        <script	src="https://code.jquery.com/jquery-1.12.4.min.js" type="text/javascript"></script>
	<script	src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
        <script src="script/jsFunctions.js" type="text/javascript"></script>
        <script type="text/javascript" src="script/banner.js"></script>
    </head>
    <body>
        <div id="main">
            <header class="banner">
                <header class="subbanner">
                    <a href="index.jsp"><div class="topbanner logo">
                            <img id="mainLogo" src="img/PizzaHeavenLogo1.png"/>
                    </div></a>
                    <%
                        try{
                            session = request.getSession();
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
        <div id="progressbanner">
            <img id="progressimg" src="img/BasketBanner2.png" />
        </div>
        <div id="delivery">
            <h4>
                    <%
                        Customer customer = (Customer) session.getAttribute("customer");
                        if (customer != null){
                            out.println("<table id='header'><tr><th>Delivery Address</th></tr></table>");
                            out.println("<table><tr><td id='left'>House Name/Number:</td><td>"+customer.getDeliveryLineOne()+"</td></tr>");
                            out.println("<tr><td id='left'>Street Name:</td><td>"+customer.getDeliveryLineTwo()+"</td></tr>");
                            out.println("<tr><td id='left'>Town/City:</td><td>"+customer.getDeliveryCity()+"</td></tr>");
                            out.println("<tr><td id='left'>County:</td><td>"+customer.getDeliveryCounty()+"</td></tr>");
                            out.println("<tr><td id='left'>PostCode:</td><td>"+customer.getDeliveryPostCode()+"</td></tr></table>");
                            out.println("<br /><br /><br />");
                            out.println("If this is the incorrect delivery address please change it from the <a href='account.jsp'>account</a> page <br /><br />");
                        } else {
                            response.sendRedirect("login.jsp");
                        }
                        %>
            </h4>
        </div>
        <div id="payment">
            <h4>
                <%/*
                    Our real button worked but obviously we didn't want to waste money making fake purchases through PayPal.
                    PayPal's sandbox threw errors whenever we tried to input our own transaction value so each transaction is 1p.

                    <form action="https://www.paypal.com/cgi-bin/webscr" method="post" id="paypal">
                        <input type="hidden" name="business" value="pizzaheaven@students.plymouth.ac.uk">
                        <input type="hidden" name="cmd" value="_xclick">
                        <input type="hidden" name="item_name" value="PizzaHeaven">
                            out.println("<input type='hidden' name='amount' value='" + session.getAttribute("orderPrice") + "'>");
                        <input type="hidden" name="currency_code" value="GBP">
                        <input id="paypalbtn" type="image" src="img/paypal.png" border="0" name="submit" alt="Pay by PayPal" height="90px" width="180px">
                    </form>*/

                    /*Sandbox button wont allow variable prices - presumably to prevent security risks
                    out.println("<input type='hidden' name='amount' value='" + session.getAttribute("orderPrice") + "'>");*/
                %>
                <form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post" id="paypal" target="_top">
                    <input type="hidden" name="cmd" value="_s-xclick">
                    <input type="hidden" name="hosted_button_id" value="WUC6SFBCVYV3C">
                    <input id="paypalbtn" type="image" src="img/paypal.png" border="0" name="submit" alt="PayPal – The safer, easier way to pay online!" height="90px" width="180px">
                    <img alt="" border="0" src="https://www.sandbox.paypal.com/en_GB/i/scr/pixel.gif" width="1" height="1">
                </form>
                <form action="OrderServlet" method="post" id="cash">
                    <input id="cashbtn" type="image" src="img/paycash.png" border="0" name="submit" alt="Cash on Delivery" height="90px" width="180px">
                </form>
            </h4>
            <footer>
                <h10>
                    Legal details.
                    Social media links.
                </h10>
            </footer>
        </div>
    </body>
</html>