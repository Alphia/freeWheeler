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
            if (document.getElementById('checker').checked) {
                return true;
            }

            alert("No Item Selected");
            document.getElementById("caution").style.visibility = "visible";
            return false;

        }
        function myFunction()
        {
            var value=document.getElementById("quantity").value;
            document.getElementById("price").innerHTML=(parseFloat(${shoppingCartItem.getItem().price})*parseFloat(value)).toFixed(2);
            document.getElementById("tax").innerHTML=(parseFloat(${shoppingCartItem.getTax()})*parseFloat(value)).toFixed(2);

            calculateTotal();

        }
        function calculateTotal()
        {
            var price=document.getElementById("price").innerHTML;
            var tax=document.getElementById("tax").innerHTML;

            document.getElementById("tax1").innerHTML=(parseFloat(${shoppingCartItem.getTax()})).toFixed(2);
            document.getElementById("total").innerHTML=(parseFloat(price)+parseFloat(tax)).toFixed(2);
            document.getElementById("itemTotal").innerHTML=(parseFloat(price)+parseFloat(tax)).toFixed(2);
        }
        function startLoading()
        {
            document.getElementById("tax").innerHTML=(parseFloat(${shoppingCartItem.getTax()})).toFixed(2);
            document.getElementById("price").innerHTML=(parseFloat(${shoppingCartItem.getItem().price})).toFixed(2);
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
            <th><b>Tax</b></th>
            <th><b>Item Total</b></th>
            <th></th>
        </tr>

        </thead>
        <tbody>
        <tr>
            <td><input type="checkbox" size="100" id="checker" checked></td>
            <td>${shoppingCartItem.getItem().name}</td>
            <td>${shoppingCartItem.getItem().price}</td>
            <td><input type="text" style="width:20px" value="${shoppingCartItem.quantity}" name="qqty" id="quantity" onchange="myFunction()"></td>
            <td><label id="tax1">${shoppingCartItem.getTax()}</label></td>
            <td><label id="itemTotal" onclick="calculateTotal()"></label></td>
            <td></td>

        </tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>

        </tbody>
    </table><br/>
    <label id="caution" class="caution">Please select at least one item.</label>
    <br/>
    <table class="total">
        <tr>
            <td width="90%">Total :</td>
            <td style="text-align: left"><b><label id="price">${shoppingCartItem.getItem().price}</label></b></td>

        </tr>
        <tr>
            <td>Total taxes :</td>
            <td style="text-align: left"><b><label id="tax">${shoppingCartItem.getTax()}</label></b></td>
        </tr>
        <tr>
            <td>Total with taxes :</td>
            <td style="text-align: left"><b><label id="total" onclick="calculateTotal()"></label></b></td>
        </tr>
        <tr><td></td></tr>
        <tr><td></td></tr>
        <tr><td></td></tr>
        <tr>
            <td>

            </td>
            <td style="text-align: left"><input type="hidden" value="${shoppingCartItem.getItem().itemId}" name="id" />
                <button type="submit" name="shoppingCart" id="shoppingCart" value="Buy Item" onclick="return validate()">Check Out</button>
            </td>
        </tr>
    </table>
</form:form>
</body>

<%--<div class="total">--%>
<%--Total</br>--%>
<%--Total taxes</br>--%>
<%--Total with taxes</br>--%>
<%--</div>--%>

<%@ include file="footer.jsp" %>