<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Reserve Item"/>
<%@ include file="header.jsp" %>

<head>
    <link rel="stylesheet" href="<c:url value='/scripts/css/main.css' />" type="text/css"/>

    <script type="text/javascript">
        $(function () {
            new Survey().showSurvey(new SurveyPopUp());
        })
        function validate() {
            var value = parseInt(document.getElementById("quantity").value);
            var total =
            ${shoppingCartItem.getItem().getQuantity()}
            var flag = true

            if (!document.getElementById('checker').checked) {

                document.getElementById("caution").style.visibility = "visible";
                flag = false;
            }
            else {
                document.getElementById("caution").style.visibility = "hidden";
            }

            if (document.getElementById("quantity").value == 0) {
                document.getElementById("caution1").style.visibility = "visible";
                flag = false;
            }
            else {
                document.getElementById("caution1").style.visibility = "hidden";
            }

            if (value > total) {
                document.getElementById("outOfStock").style.visibility = "visible";
                flag = false;
            }
            else {
                document.getElementById("outOfStock").style.visibility = "hidden";
            }
            if (isNaN(value)) {
                document.getElementById("caution2").style.visibility = "visible";
                flag = false;
            }
            else {
                document.getElementById("caution2").style.visibility = "hidden";
            }
            if (flag) {
                return true;
            }
            return false;
        }
        function myFunction() {
            var value = parseInt(document.getElementById("quantity").value);
            var total = ${shoppingCartItem.getItem().getQuantity()}
            if (value > total) {
                document.getElementById("checker").checked = false;
                document.getElementById("outOfStock").style.visibility = "visible";
                document.getElementById("caution1").style.visibility = "hidden";
                document.getElementById("caution2").style.visibility = "hidden";
            }
            if (value == 0) {
                document.getElementById("checker").checked = false;
                document.getElementById("outOfStock").style.visibility = "hidden";
                document.getElementById("caution1").style.visibility = "visible";
                document.getElementById("caution2").style.visibility = "hidden";
            }
            if (value < total && value != 0)
            {
                document.getElementById("checker").checked = true;
                document.getElementById("caution").style.visibility = "hidden";
                document.getElementById("caution1").style.visibility = "hidden";
                document.getElementById("caution2").style.visibility = "hidden";
                document.getElementById("outOfStock").style.visibility = "hidden";
            }
            if (isNaN(value))
            {
                document.getElementById("checker").checked = false;
                document.getElementById("caution2").style.visibility = "visible";
                document.getElementById("caution1").style.visibility = "hidden";
                document.getElementById("outOfStock").style.visibility = "hidden";
            }
            document.getElementById("price").innerHTML = (parseFloat(${shoppingCartItem.getItem().price}) * parseFloat(value)).toFixed(2);
            if (${region!='OUTSIDE_EU'}) {
                document.getElementById("tax").innerHTML = (parseFloat(${shoppingCartItem.getTax()}) * parseFloat(value)).toFixed(2);
            }
            calculateTotal();

        }
        function calculateTotal() {
            if (${region=='OUTSIDE_EU'}) {

                var price = document.getElementById("price").innerHTML;

                document.getElementById("itemTotal").innerHTML = (parseFloat(price)).toFixed(2);

            }
            else {
                var price = document.getElementById("price").innerHTML;
                var tax = document.getElementById("tax").innerHTML;
                document.getElementById("total").innerHTML = (parseFloat(price) + parseFloat(tax)).toFixed(2);
                document.getElementById("itemTotal").innerHTML = (parseFloat(price)).toFixed(2);
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
<body onload="startLoading()">
<div class="page-action">Shopping Cart</div>
<form:form action="/shoppingCart/confirm" method="post" modelAttribute="shoppingCartItem">
    <table class="table">
        <thead>
        <tr>
            <th></th>
            <th style="width:40%;"><b>Name</b></th>
            <th><b>Price</b></th>
            <th><b>Quantity</b></th>
            <th></th>
            <th><b>Item Total</b></th>
            <th></th>
        </tr>

        </thead>
        <tbody>
        <tr>
            <td><input type="checkbox" size="100" id="checker" checked></td>
            <td>${shoppingCartItem.getItem().name}</td>
            <td>${shoppingCartItem.getItem().price}</td>
            <td><input type="text" style="width:20px" value="${shoppingCartItem.quantity}" name="qqty" id="quantity"
                       onchange="myFunction()"></td>
            <td><label id="caution1" class="caution">Quantity cannot be zero.</label></td>
            <td><label id="itemTotal"></label></td>
            <td><label id="outOfStock" class="caution">Out Of Stock</label><label id="totalQuantity"
                                                                                  class="caution">${shoppingCartItem.getItem().getQuantity()}</label>
            </td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td><label id="caution2" class="caution">Quantity must be a number.</label></td>
        </tr>
        <tr></tr>
        <tr></tr>

        </tbody>
    </table>
    <br/>
    <label id="caution" class="caution">Please select at least one item.</label>
    <br/>
    <table class="total">
        <tr>
            <td width="100%">Total :</td>
            <td style="text-align: center"><b><label id="price">${shoppingCartItem.getItem().price}</label></b></td>

        </tr>
        <tr>
            <c:if test="${region == 'UK'}">
                <td><span id="vat_percentage_message">VAT(20%) :</span></td>
            </c:if>
            <c:if test="${region == 'EU_EXCEPT_UK'}">
                <td><span id="vat_exempt">VAT Exempt :</span></td>

            </c:if>

                <c:if test="${region != 'OUTSIDE_EU'}">
            <td class="price-justification"><b><label id="tax">${shoppingCartItem.getTax()}</label></b></td>
                </c:if>

        </tr>

        <c:if test="${region != 'OUTSIDE_EU'}">
            <tr>
                <td>Total with VAT :</td>
                <td style="text-align: center"><b><label id="total" onclick="calculateTotal()"></label></b></td>
            </tr>
        </c:if>

        <tr>
            <td>
                <a href="<c:url value='/' />"> <span
                        style="text-decoration: blue; font: italic; text-decoration: underline">Continue Shopping</span></a>
            </td>
            <td style="text-align: center"><input type="hidden" value="${shoppingCartItem.getItem().itemId}" name="id"/>
                <button type="submit" name="shoppingCart" id="shoppingCart" value="Buy Item"
                        onclick="return validate()">Check Out
                </button>
            </td>
        </tr>
        <tr>
            <td></td>

            <td class="method-of-payment-message"><br/>Cash On Delivery only</td>
        </tr>
    </table>
</form:form>
</body>


<%@ include file="footer.jsp" %>