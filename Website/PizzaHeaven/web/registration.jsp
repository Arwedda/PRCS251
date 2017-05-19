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
        <link rel=stylesheet type="text/css" href="css/registration.css">
        <link rel=stylesheet type="text/css" href="css/menu.css">
        <script	src="https://code.jquery.com/jquery-1.12.4.min.js" type="text/javascript"></script>
	<script	src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="script/jsFunctions.js"></script>
        <script type="text/javascript" src="script/banner.js"></script>
        <script src="script/Address_JS.js"></script>
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
                <form name="register" method="post" action="RegistrationServlet" >
                    <table>
                        <tr>
                            <th id="personal">
                                <h77>Personal Details</h77>
                            </th>
                        </tr>
                        <tr>
                            <td>
                                First name<font color="#DA4246">*</font>
                                <input type="text" class="input" name="firstname" maxlength="30" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Surname<font color="#DA4246">*</font> 
                                <input type="text" class="input" name="surname" maxlength="30" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Date Of Birth<font color="#DA4246">*</font> 
                                <input type="date" class="input" name ="dob" min="1900-01-01" max="2001-03-28" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Phone Number<font color="#DA4246">*</font> 
                                <input type="text" class="input" name="phonenumber" pattern="\b\d{11}$\b" maxlength="11" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                
                            </td>
                        </tr>
                        <tr>
                            <td>
                                
                            </td>
                        </tr>
                        <tr>
                            <td>
                                
                            </td>
                        </tr>
                        <tr>
                            <th id="delivery">
                                <h77>Delivery Address</h77>
                            </th>
                        </tr>
                        <tr>
                            <td>
                                <div id="postcode_lookup">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Delivery House Number/Name<font color="#DA4246">*</font> 
                                <input type="text" class="input" id="deliverylineone" name="deliverylineone" maxlength="30" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Delivery Street Name<font color="#DA4246">*</font> 
                                <input type="text" class="input" id="deliverylinetwo" name="deliverylinetwo" maxlength="30" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Delivery Town/City<font color="#DA4246">*</font> 
                                <input type="text" class="input" id="deliverycity" name="deliverycity" maxlength="30" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Delivery County<font color="#DA4246">*</font> 
                                <input type="text" class="input" id="deliverycounty" name="deliverycounty" maxlength="30" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Delivery PostCode<font color="#DA4246">*</font> 
                                <input type="text" class="input" id="deliverypostcode" name="deliverypostcode" 
                                        pattern='^[A-Za-z]{1,2}[0-9R][0-9A-Za-z]? ?[0-9][ABD-HJLNP-UW-Zabd-hjlnp-uw-z]{2}$' maxlength="8" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                
                            </td>
                        </tr>
                        <tr>
                            <td>
                                
                            </td>
                        </tr>
                        <tr>
                            <td>
                                
                            </td>
                        </tr>
                        <tr>
                            <th id="login">
                                <h77>Login details</h77>
                            </th>
                        </tr>
                        <tr>
                            <td>
                                Email<font color="#DA4246">*</font> 
                                <input type="email" class="input" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" 
                                      oninvalid="setCustomValidity('Please enter a valid e-mail address. For example: j.smith@email.com')"
    onchange="try                      {setCustomValidity('');}catch(e){}" id="email" name="email" maxlength="80" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Re-type Email<font color="#DA4246">*</font> 
                                <input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" 
                                      oninvalid="setCustomValidity('Please enter a valid e-mail address. For example: j.smith@email.com')"
    onchange="try                      {setCustomValidity('');}catch(e){}" class="input" id="email2" name="email2" maxlength="80" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Password<font color="#DA4246">*</font> 
                                <input type="password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!£*@#$%^&+=])(?=\S+$()).{8,}$"
                                       oninvalid="setCustomValidity('Password must contain upper and lower case letters, a number and at least one special character (!£*@#$%^&+=)')"
    onchange="try                      {setCustomValidity('');}catch(e){}" class="input" id="password" name="password" required>
                            </td>
                        </tr>
                        <tr>
                           <td>
                                Re-type Password<font color="#DA4246">*</font> 
                                <input type="password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!£*@#$%^&+=])(?=\S+$()).{8,}$"
                                oninvalid="setCustomValidity('Password must contain upper and lower case letters, a number and at least one special character (!£*@#$%^&+=)')"
    onchange="try                      {setCustomValidity('');}catch(e){}" class="input" id="password2" name="password2" required>
                            </td>
                        </tr>
                    </table>                         
                <br />
                <input id="submit" type="image" src="img/btnRegister.png" border="0" name="submit" alt="Register" height="90px" width="180px">
                </form>
            </h4>
        </div>
        <script>
            $('#postcode_lookup').getAddress({
                api_key: 'rOp9tWS8pEabHxu2E6k6Cg8522',  
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