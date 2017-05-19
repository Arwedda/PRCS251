<%@page import="pizzaheaven.models.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Pizza Heaven</title>
        <meta charset="UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
        <link rel=stylesheet type="text/css" href="css/banner.css">
        <link rel=stylesheet type="text/css" href="css/mediaqueries.css">
        <link rel=stylesheet type="text/css" href="css/menu.css">
        <link rel=stylesheet type="text/css" href="css/footer.css">
        <link rel=stylesheet type="text/css" href="css/account.css">
        <script	src="https://code.jquery.com/jquery-1.12.4.min.js" type="text/javascript"></script>
	<script	src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
        <script src="script/jsFunctions.js" type="text/javascript"></script>
        <script type="text/javascript" src="script/banner.js"></script>
        <script src="script/Address_JS.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">  
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
        <div id="customerdetails">
            <h4>
                <form name="account" method="post" action="AccountUpdateServlet" >
                    <% 
                        try{
                            Customer customer = (Customer)session.getAttribute("customer");
                            out.println("<table id='userDetails'><tr><th id='personal'>Personal Details</th></tr>");
                            out.println("<tr><td>First name<font color='#DA4246'>*</font><input type='text' "
                                    + "name='firstname' value='"+customer.getFirstName()+"' maxlength='30' required></td></tr>");
                            out.println("<tr><td>Surname<font color='#DA4246'>*</font><input type='text' "
                                    + "name='surname' value='"+customer.getSurname()+"' maxlength='30' required></td></tr>");
                            out.println("<tr><td>Date Of Birth<font color='#DA4246'>*</font><input type='date' "
                                    + "name='dob' value='"+customer.getDob().substring(0, 10)+"' min='1900-01-01' max='2001-03-28' required></td></tr>");
                            out.println("<tr><td>Phone Number<font color='#DA4246'>*</font><input type='text' "
                                    + "name='phonenumber' value='"+customer.getPhoneNumber()+"' pattern='\\b\\d{11}$\\b' maxlength='11' required></td></tr>");
                            out.println("<tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>");
                            out.println("<tr><th id='delivery'>Delivery Address</th></tr>");
                            out.println("<tr><td><div id='postcode_lookup'></div></td></tr>");
                            out.println("<tr><td>Delivery House Number/Name<font color='#DA4246'>*</font><input type='text'"
                                    + " name='deliverylineone' id='deliverylineone' value='"+customer.getDeliveryLineOne()+"' maxlength='30' required></td></tr>");
                            out.println("<tr><td>Delivery Street Name<font color='#DA4246'>*</font><input type='text' "
                                    + "name='deliverylinetwo' id='deliverylinetwo' value='"+customer.getDeliveryLineTwo()+"' maxlength='30' required></td></tr>");
                            out.println("<tr><td>Delivery Town/City<font color='#DA4246'>*</font><input type='text' "
                                    + "name='deliverycity' id='deliverycity' value='"+customer.getDeliveryCity()+"' maxlength='30' required></td></tr>");
                            out.println("<tr><td>Delivery County<font color='#DA4246'>*</font><input type='text' "
                                    + "name='deliverycounty' id='deliverycounty' value='"+customer.getDeliveryCounty()+"' maxlength='30' required></td></tr>");
                            out.println("<tr><td>Delivery PostCode<font color='#DA4246'>*</font><input type='text' "
                                    + "name='deliverypostcode' id='deliverypostcode' value='"+customer.getDeliveryPostCode()+"' "
                                    + "pattern='^[A-Za-z]{1,2}[0-9R][0-9A-Za-z]? ?[0-9][ABD-HJLNP-UW-Zabd-hjlnp-uw-z]{2}$' maxlength='8' required></td></tr>");
                            out.println("<tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>");
                            out.println("<tr><th id='login'>Login details</th></tr>");
                            out.println("<tr><td><h4>Please confirm your e-mail address and password to make any changes.</h4>"
                                    + "</td></tr><td>Email<font color='#DA4246'>*</font><input type='text' id='email' "
                                    + "name='email' value='"+customer.getEmail()+"'disabled></td></tr>");
                            out.println("<tr><td>Re-type Email<font color='#DA4246'>*</font><input type='text' id='email2' "
                                    + "name='email2' required></td></tr>");
                            out.println("<tr><td>Password<font color='#DA4246'>*</font><input type='password' id='password' "
                                    + "name='password' required></td></tr>");
                            out.println("<tr><td>Re-type Password<font color='#DA4246'>*</font><input type='password' "
                                    + "id='password2' name='password2' required></td></tr></table>");
                        } catch (Exception e){
                            response.sendRedirect("login.jsp");
                        }
                    %>
                    <input id="submit" type="image" src="img/btnUpdate.png" border="0" name="submit" alt="Update" height="90px" width="180px">
                </form>
            </h4>
        </div>
        <script>
            $('#postcode_lookup').getAddress({
                api_key: 'NaMLIRKsOUe5KuruziE-5w8523',  
                output_fields:{
                    line_1: '#deliverylineone',
                    line_2: '#deliverylinetwo',
                    post_town: '#deliverycity',
                    county: '#deliverycounty',
                    postcode: '#deliverypostcode'
                },
                onLookupSuccess: function(data){/* Your custom code */},
                onLookupError: function(){/* Your custom code */},
                onAddressSelected: function(elem,index){/* Your custom code */}
            });
        </script>
        <footer>
            <h10>
                Legal details.
                Social media links.
            </h10>
        </footer>
    </body>
</html>