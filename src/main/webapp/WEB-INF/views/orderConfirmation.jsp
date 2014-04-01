<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" scope="request" value="Reserve Item"/>
<%@ include file="header.jsp" %>

<head>
    <link rel="stylesheet" href="<c:url value='/scripts/css/main.css' />" type="text/css"/>

    <script type="text/javascript">
        $(function () {
            new Survey().showSurvey(new SurveyPopUp());
        })
        function myFunction() {
            var value = document.getElementById("quantity").innerHTML;
            document.getElementById("price").innerHTML = (parseFloat(${shoppingCartItem.getItem().price}) * parseFloat(value)).toFixed(2);
            document.getElementById("tax").innerHTML = (parseFloat(${shoppingCartItem.getTax()}) * parseFloat(value)).toFixed(2);
            calculateTotal();

        }
        function calculateTotal() {
            if (${region=='OUTSIDE_EU'}) {

                var price = document.getElementById("price").innerHTML;

                document.getElementById("itemTotal").innerHTML = (parseFloat(price)).toFixed(2);
                document.getElementById("total1").innerHTML = (parseFloat(price)).toFixed(2);

            }
            else {
                var price = document.getElementById("price").innerHTML;
                var tax = document.getElementById("tax").innerHTML;
                document.getElementById("total").innerHTML = (parseFloat(price) + parseFloat(tax)).toFixed(2);
                document.getElementById("itemTotal").innerHTML = (parseFloat(price) + parseFloat(tax)).toFixed(2);
            }

        }
        function startLoading() {

            if (${region=='OUTSIDE_EU'}) {
                document.getElementById("price").innerHTML = (parseFloat(${shoppingCartItem.getItem().price})).toFixed(2);
            }
            else {
                document.getElementById("tax").innerHTML = (parseFloat(${shoppingCartItem.getTax()})).toFixed(2);
                document.getElementById("price").innerHTML = (parseFloat(${shoppingCartItem.getItem().price})).toFixed(2);
            }


            calculateTotal();

        }
    </script>
</head>
<body onload="myFunction()">
<div class="page-action">Confirmation Page</div>
<form action="/shoppingCart/buy" method="post" modelAttribute="shoppingCartItem">
    <table class="table">
        <thead>
        <tr>
            <th style="width:40%;"><b>Name</b></th>
            <th><b>Price</b></th>
            <th><b>Quantity</b></th>
            <th><b>Item Total</b></th>
        </tr>

        </thead>
        <tbody>
        <tr>
            <td>${shoppingCartItem.getItem().name}</td>
            <td>${shoppingCartItem.getItem().price}</td>
            <td><label id="quantity" >${shoppingCartItem.getQuantity()}</label></td>
            <td><label id="itemTotal"></label></td>

        </tr>

        <tr>
            <td></td>
        </tr>

        <tr>
            <td></td>
        </tr>

        <tr>
            <td></td>
        </tr>

        </tbody>
    </table>
        <table class="total total-design">
            <tr>
                <td width="90%" style="text-align: right">Total</td>
                <td><b><label id="price">${shoppingCartItem.getItem().price}</label></b></td>
            </tr>
            <tr>
                <c:if test="${region == 'UK'}">
                    <td><span id="vat_percentage_message">VAT(20%) </span></td>
                </c:if>
                <c:if test="${region == 'EU_EXCEPT_UK'}">
                    <td><span id="vat_exempt">VAT Exempt :</span></td>
                </c:if>
                <c:if test="${region != 'OUTSIDE_EU'}">
                <td>
                    <b><label id="tax">
                        <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2"
                                          value="${shoppingCartItem.getTax()}"/>
                    </label></b>
                </td>
                    </c:if>
            </tr>
            <c:if test="${region != 'OUTSIDE_EU'}">
                <tr>
                    <td>Total with VAT </td>
                    <td><b><label id="total"></label></b></td>
                </tr>
            </c:if>
        </table>

    <div class="page-action">Delivery Address</div>

    <div>
        <label for="fld_name">Name</label>

        <div class="controls">

            <c:choose>
                <c:when test="${empty validationMessage.errors['name']}">
                    <c:choose>
                        <c:when test="${empty validationMessage.userDetails}">
                            <input type="text" id="fld_name" placeholder="Your Name" name="name">
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="fld_name" placeholder="Your Name" name="name"
                                   value="${validationMessage.userDetails.getAccount_name()}">
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <input type="text" id="fld_name" placeholder="Your Name" name="name">
                    <span class="text-error">${validationMessage.errors["name"]}</span>
                </c:otherwise>
            </c:choose>
        </div> *
    </div>

    <div>
        <label for="fld_phone">Phone</label>

        <div class="controls">

            <c:choose>
                <c:when test="${empty validationMessage.errors['phone']}">
                    <c:choose>
                        <c:when test="${empty validationMessage.userDetails}">
                            <input type="text" id="fld_phone" placeholder="Your Phone" name="phone">
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="fld_phone" placeholder="Your Phone" name="phone"
                                   value="${validationMessage.userDetails.getPhoneNumber()}">
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <input type="text" id="fld_phone" placeholder="Your Phone" name="phone">
                    <span class="text-error">${validationMessage.errors["phone"]}</span>
                </c:otherwise>
            </c:choose>
        </div> *
    </div>

    <div>
        <label for="fld_street1">Street1</label>

        <div class="controls">

            <c:choose>
                <c:when test="${empty validationMessage.errors['street1']}">
                    <c:choose>
                        <c:when test="${empty validationMessage.userDetails}">
                            <input type="text" id="fld_street1" placeholder="Street 1" name="street1">
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="fld_street1" placeholder="Street 1" name="street1"
                                   value="${validationMessage.userDetails.getStreet1()}">
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <input type="text" id="fld_street1" placeholder="Street 1" name="street1">
                    <span class="text-error">${validationMessage.errors["street1"]}</span>
                </c:otherwise>
            </c:choose>
        </div> *
    </div>

    <div>
        <label for="fld_street2">Street2</label>

        <div class="controls">
            <input type="text" id="fld_street2" placeholder="Street 1" name="street2">
        </div>
    </div>

    <div>
        <label for="fld_city">City</label>

        <div class="controls">

            <c:choose>
                <c:when test="${empty validationMessage.errors['city']}">
                    <c:choose>
                        <c:when test="${empty validationMessage.userDetails}">
                            <input type="text" id="fld_city" placeholder="Your City" name="city">
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="fld_city" placeholder="Your City" name="city"
                                   value="${validationMessage.userDetails.getCity()}">
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <input type="text" id="fld_city" placeholder="Your City" name="city">
                    <span class="text-error">${validationMessage.errors["city"]}</span>
                </c:otherwise>
            </c:choose>
        </div> *
    </div>

    <div>
        <label for="fld_state">State/Province</label>
        <div class="controls">
            <input type="text" id="fld_state" placeholder="Your State" name="state">
        </div>
    </div>

    <div>
        <label for="fld_country">Country</label>
        <div class="controls">
            <select id="fld_country" name="country">
                <option value="0">Select</option>
                <c:forEach var="country" items="${validationMessage.countries}" varStatus="row">
                    <option value="${country.getCountry_id()}">${country.getCountry_name()}</option>
                </c:forEach>
            </select>
            <c:if test="${not empty validationMessage.errors['country']}">
                <span class="text-error">${validationMessage.errors["country"]}</span>
            </c:if>
        </div> *
    </div>

    <div>
        <label for="fld_postcode">Postcode</label>

        <div class="controls">

            <c:choose>
                <c:when test="${empty validationMessage.errors['postcode']}">
                    <c:choose>
                        <c:when test="${empty validationMessage.userDetails}">
                            <input type="text" id="fld_postcode" placeholder="Your Postcode" name="postcode">
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="fld_postcode" placeholder="Your Postcode" name="postcode"
                                   value="${validationMessage.userDetails.getPostcode()}">
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <input type="text" id="fld_postcode" placeholder="Your postcode" name="postcode">
                    <span class="text-error">${validationMessage.errors["postcode"]}</span>
                </c:otherwise>
            </c:choose>
        </div> *
    </div>

    <p style="margin-left: 160px">* Mandatory Field</p>

    <div style="text-align: center">
        <button type="submit" name="shoppingCart" id="back_to_cart" value="back_to_cart">< Back to Cart</button>
        <input type="hidden" value="${shoppingCartItem.getItem().itemId}" name="id" />
        <input type="hidden" value="${shoppingCartItem.getQuantity()}" name="quantity" />
        <button style="margin-left: 100px" type="submit" id="confirm" >Confirm ></button>
    </div>
</form>
</body>

<%@ include file="footer.jsp" %>