<%@page import="java.text.DecimalFormat"%>
<%@page import="pizzaheaven.models.Customer"%>
<%@page import="pizzaheaven.models.Image"%>
<%@page import="pizzaheaven.models.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
        <link rel=stylesheet type="text/css" href="css/banner.css">
        <link rel=stylesheet type="text/css" href="css/mediaqueries.css">
        <link rel=stylesheet type="text/css" href="css/footer.css">
        <link rel=stylesheet type="text/css" href="css/menu.css">
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
        <script type="text/javascript" src="script/pizzas.js"></script>
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
                     <a href="PizzaMenuServlet"><div id="b" style="background: #DA4246">
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
        <%
            try {
                Object objectList = (Object)session.getAttribute("pizzaList");
                Pizza[] pizzaList = (Pizza[]) objectList;
                objectList = (Object)session.getAttribute("imageList");
                Image[] imageList = (Image[]) objectList;
                String imageName;
                int heatRating;
                DecimalFormat decim = new DecimalFormat("0.00");

                out.println("<div class='menupage'>");
                out.println("<a href='buildapizza.jsp'><img id='custombanner' src='img/CustomPizzaBanner.png' /></a>");
                for (int i = 0; i < pizzaList.length; i++){
                    out.println("<div class='menudiv "+i+"'>");
                    out.println("<div class='imagediv'>");
                    out.println("<div class='basketAdd "+ i +"' style='display:none'>");
                    out.println("<h4 class='basketAddMessage "+i+"'>This item has been added to the basket</h4>");
                    out.println("</div>");
                    out.println("<div class='descriptiondiv "+ i +"' style='display:none'>");
                    out.println("<h4>"+pizzaList[i].getDescription()+"</h4>");
                    out.println("</div>");
                    out.println("<input type='image' src='img/info.png' class='infoimg "+ i +"'/>");
                    imageName = pizzaList[i].getImage();
                    for (int j = 0; j < imageList.length; j++){
                        if (imageName.equals(imageList[j].getName())){
                            out.println("<img src='data:image/png;base64,"+imageList[j].getBase64()+"' class='menuimg "+ i +"' />");
                        }
                    }
                    out.println("</div>");
                    out.println("<div class='infodiv'>");
                    out.println("<h4><div class='name "+i+"'>"+pizzaList[i].getName()+"</div></h4>");
                    out.println("<select id='itemPrice' name='itemPrice' class='price "+ i +"'>");
                    out.println("<option value='Small "+pizzaList[i].getSmallPrice()+"'>Small Pizza £"+decim.format(Double.parseDouble(pizzaList[i].getSmallPrice()))+"</option>");
                    out.println("<option value='Medium"+pizzaList[i].getMediumPrice()+"'>Medium Pizza £"+decim.format(Double.parseDouble(pizzaList[i].getMediumPrice()))+"</option>");
                    out.println("<option value='Large "+pizzaList[i].getLargePrice()+"'>Large Pizza £"+decim.format(Double.parseDouble(pizzaList[i].getLargePrice()))+"</option>");
                    out.println("</select>");
                    out.println("</br>");
                    out.println("</div>");
                    out.println("<div class='infodiv2'>");
                    heatRating = (int) Double.parseDouble(pizzaList[i].getHeatRating());
                    out.println("<img src='img/heat"+heatRating+".png' id='heatRating' />");
                    out.println("<input type='image' src='img/addBasket.png' class='addBasket "+ i +"' />");
                    out.println("</div>");
                    out.println("</div>");
                }
                out.println("<footer><h10>Legal details.Social media links.</h10></footer>");
                out.println("</div><br />");
            } catch (Exception ex){
                response.sendRedirect("PizzaMenuServlet");
            } finally {
                out.println("Sorry, the pizzas menu doesn't seem to be working! :(");
            }
        %>
    </body>
</html>