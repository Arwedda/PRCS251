<%@page import="pizzaheaven.models.Topping"%>
<%@page import="pizzaheaven.models.Cheese"%>
<%@page import="pizzaheaven.models.Sauce"%>
<%@page import="pizzaheaven.models.Crust"%>
<%@page import="pizzaheaven.models.Base"%>
<%@page import="pizzaheaven.models.Size"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="pizzaheaven.models.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
        <link rel=stylesheet type="text/css" href="css/banner.css">
        <link rel=stylesheet type="text/css" href="css/mediaqueries.css">
        <link rel=stylesheet type="text/css" href="css/footer.css">
        <link rel=stylesheet type="text/css" href="css/menu.css">
        <link rel=stylesheet type="text/css" href="css/buildapizza.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script
	src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous"></script>
	<script
	src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
	integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="
	crossorigin="anonymous"></script>
        <script type="text/javascript" src="script/jsFunctions.js"></script>
        <script type="text/javascript" src="script/buildapizza.js"></script>
        <title>Pizza Heaven</title>
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
        <div class='menupage'>
        <%
            try {
                Object objectList = (Object)session.getAttribute("sizeList");
                Size[] sizeList = (Size[]) objectList;
                objectList = (Object)session.getAttribute("baseList");
                Base[] baseList = (Base[]) objectList;
                objectList = (Object)session.getAttribute("crustList");
                Crust[] crustList = (Crust[]) objectList;
                objectList = (Object)session.getAttribute("sauceList");
                Sauce[] sauceList = (Sauce[]) objectList;
                objectList = (Object)session.getAttribute("cheeseList");
                Cheese[] cheeseList = (Cheese[]) objectList;
                objectList = (Object)session.getAttribute("toppingList");
                Topping[] toppingList = (Topping[]) objectList;
                int i = 0;

                DecimalFormat decim = new DecimalFormat("0.00");

                out.println("<h4><div id='size'>Size<br /><br />");
                for (Size size : sizeList){
                    out.println("<input type='radio' name='size' class='size "+size.getName()+"' value='" + size.getName() + "^" + size.getPrice() + "'>" + size.getName() + " (£" + decim.format(Double.parseDouble(size.getPrice())) + ")<br />");
                }
                out.println("</div><div id='base'>Base<br /><br />");
                for (Base base : baseList){
                    out.println("<input type='radio' name='base' class='base "+base.getName()+"' value='" + base.getName() + "^" + base.getPrice() + "'>" + base.getName() + " (+£" + decim.format(Double.parseDouble(base.getPrice())) + ")<br />");
                }
                out.println("</div><div id='crust'>Crust<br /><br />");
                for (Crust crust : crustList){
                    out.println("<input type='radio' name='crust' class='crust "+crust.getName()+"' value='" + crust.getName() + "^" + crust.getPrice() + "'>" + crust.getName() + " (+£" + decim.format(Double.parseDouble(crust.getPrice())) + ")<br />");
                }
                out.println("</div><br /><div id='sauce'>Sauce<br /><br />");
                for (Sauce sauce : sauceList){
                    out.println("<input type='radio' name='sauce' class='sauce "+sauce.getName()+"' value='" + sauce.getName() + "^" + sauce.getPrice() + "'>" + sauce.getName() + " (+£" + decim.format(Double.parseDouble(sauce.getPrice())) + ")<br />");
                }
                out.println("</div><div id='cheese'>Cheese<br /><br />");
                for (Cheese cheese : cheeseList){
                    out.println("<input type='radio' name='cheese' class='cheese "+cheese.getName()+"' value='" + cheese.getName() + "^" + cheese.getPrice() + "'>" + cheese.getName() + " (+£" + decim.format(Double.parseDouble(cheese.getPrice())) + ")<br />");
                }
                out.println("</div><div id='topping'>Toppings<br /><br />");
                for (Topping topping : toppingList){
                    out.println("<input type='checkbox' name='topping' class='topping "+ i + "' value='" + topping.getName() + "^" + topping.getPrice() + "'>" + topping.getName() + " (+£" + decim.format(Double.parseDouble(topping.getPrice())) + ")");
                    i++;
                    if (i == 3){
                        i = 0;
                        out.println("<br />");
                    }
                }
                out.println("</div></h4>");
            } catch (Exception ex){
                response.sendRedirect("CustomPizzaServlet");
            } finally {
                out.println("Sorry, Build-A-Pizza doesn't seem to be working! :(");
            }
        %>
            <input type='image' src='img/addBasket.png' class='addBasket' />
            <footer>
                <h10>
                    Legal details.
                    Social media links.
                </h10>
            </footer>
        </div>
    </body>
</html>