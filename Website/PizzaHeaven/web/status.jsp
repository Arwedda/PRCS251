<%@page import="java.text.DecimalFormat"%>
<%@page import="pizzaheaven.models.OrderItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pizzaheaven.models.Order"%>
<%@page import="pizzaheaven.models.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pizza Heaven</title>
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" >
        <meta http-equiv="Pragma" content="no-cache" >
        <meta http-equiv="Expires" content="0" >
        <link rel=stylesheet type="text/css" href="css/banner.css">
        <link rel=stylesheet type="text/css" href="css/mediaqueries.css">
        <link rel=stylesheet type="text/css" href="css/footer.css">
        <link rel=stylesheet type="text/css" href="css/status.css">
        <link rel=stylesheet type="text/css" href="css/menu.css">
        <script
	src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous"></script>
	<script
	src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
	integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="
	crossorigin="anonymous"></script>
        <script type="text/javascript" src="script/status.js"></script>    
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
            <img id="progressimg" src="img/BasketBanner3.png" />
        </div>
        <div id ="order">
            <h4>
            <%
                try {
                    ArrayList<Order> arlOrders = (ArrayList<Order>) session.getAttribute("activeOrders");
                    ArrayList<OrderItem> arlOrderItems = (ArrayList<OrderItem>) session.getAttribute("activeOrderItems");
                    DecimalFormat df = new DecimalFormat("0.00");
                    String id;
                    Double price;
                    String priceStr;
                    Double linePrice;
                    String linePriceStr;
                    Double cost;
                    String costStr;
                    Double calcCost;
                    String calcCostStr;
                    int quantity;
                    int j = 0;

                    for (Order order : arlOrders){
                        id = order.getOrderID();
                        calcCost = 0.00;
                        out.println("<b>Order Number: " + id + ".<br />");
                        out.println("Order Status: " + order.getStatus() + ".<br />");
                        if (order.getPaymentMethod().equals("Cash")){
                            out.println("Please be ready to pay your delivery driver the order value.<br />");
                        } else if (order.getPaymentMethod().equals("PayPal")) {
                            out.println("Paid for using PayPal.<br />");
                        }
                        if (order.getStatus() != "Delivered"){
                            out.println("Estimated delivery time: " + order.getRequestedDateTime().substring(11, 16) + ".<br /><br />");
                        } else {
                            out.println("Delivered at: " + order.getDeliveryDateTime() + ".<br /><br />");
                        }
                        out.println("Order Summary:</b>");
                        out.println("<table><tr><th></th><th>Name</th><th>Price</th><th id='quantity'>Quantity</th><th>Line Price</th></tr>");
                        for (OrderItem item : arlOrderItems){
                            if (item.getId().equals(id)){
                                price = Double.parseDouble(item.getPrice());
                                quantity = Integer.parseInt(item.getQuantity());
                                linePrice = (price * quantity);
                                priceStr = df.format(price);
                                linePriceStr = df.format(linePrice);

                                out.println("<tr><td id='label'></td><td class='name " + j + "'>" + item.nameToString() + "</td>");
                                out.println("<td>£<div class='itemprice " + j + "'>" + priceStr + "</div></td>");
                                out.println("<td><div class='quantity " + j + "'>" + String.valueOf(quantity) + "</div></td>");
                                out.println("<td>£<div class='lineprice " + j + "'>" + linePriceStr + "</div></td></tr>");
                                j++;
                                calcCost += linePrice;
                            }
                        }
                        cost = Double.parseDouble(order.getOrderCost());
                        costStr = df.format(cost);
                        calcCostStr = df.format(calcCost);
                        if (!costStr.equals(calcCostStr)){
                            Double saving = calcCost - cost;
                            String savingStr = df.format(saving);
                            out.println("<tr><td></td><td></td><td></td><td class='saving'>Amount saved</td><td class='saving'>£" + savingStr + "</td></tr>");
                        }
                        out.println("<tr><td></td><td></td><td></td><td>Total Price</td><td>£" + costStr + "</td></tr></table>");
                    }
                        response.setHeader("Refresh", "30; url = OrderStatusServlet");
                } catch(Exception e){
                    out.println("<center>There are no open orders on your account. Please contact the store if you believe this is incorrect.</center>");
                }
            %>
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