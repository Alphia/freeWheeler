<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Reserve Item"/>
<%@ include file="header.jsp" %>

<head>
    <link rel="stylesheet" href="<c:url value='/scripts/css/main.css' />" type="text/css"/>

    <script type="text/javascript">
        $(function () {
            new Survey().showSurvey(new SurveyPopUp());
        })
        function myFunction()
        {
            var value=document.getElementById("quantity").innerHTML;
            document.getElementById("price").innerHTML=(parseFloat(${shoppingCartItem.getItem().price})*parseFloat(value)).toFixed(2);
            document.getElementById("tax").innerHTML=(parseFloat(${shoppingCartItem.getTax()})*parseFloat(value)).toFixed(2);
            calculateTotal();

        }
        function calculateTotal()
        {
            var price=document.getElementById("price").innerHTML;
            var tax=document.getElementById("tax").innerHTML;
            document.getElementById("total").innerHTML=(parseFloat(price)+parseFloat(tax)).toFixed(2);
            document.getElementById("total1").innerHTML=(parseFloat(price)+parseFloat(tax)).toFixed(2);
        }
    </script>
</head>
<body onload="myFunction()">
<div class="page-action">Confirmation Page</div>
<form action="/shoppingCart/buy" method="post" modelAttribute="shoppingCartItem">
    <table class="table">
        <thead>
        <tr>
            <th></th>
            <th><b>QTY</b></th>
            <th style="width:40%;"><b>NAME</b></th>
            <th><b>PRICE</b></th>
            <th><b>EXTENDED PRICE</b></th>
            <th><b></b></th>
            <th></th>
            <th></th>
        </tr>

        </thead>
        <tbody>
        <tr>
            <td><input type="hidden" value="${shoppingCartItem.getItem().itemId}" name="id" /><input type="hidden" value="${shoppingCartItem.getQuantity()}" name="qqty" /></td>
            <td><label id="quantity">${shoppingCartItem.getQuantity()}</label></td>
            <td>${shoppingCartItem.getItem().name}</td>
            <td>${shoppingCartItem.getItem().price}</td>
            <td><label id="total1" onclick="calculateTotal()"></label></td>
            <td></td>

        </tr>

        </tbody>
    <table class="total">
        <tr>
            <td width="90%">Total</td>
            <td><label id="price">${shoppingCartItem.getItem().price}</label></td>
        </tr>
        <tr>
            <td>Total taxes</td>
            <td><label id="tax">${shoppingCartItem.getTax()}</label></td>
        </tr>
        <tr>
            <td>Total with taxes</td>
            <td><label id="total" onclick="calculateTotal()"></label></td>
        </tr>
        <tr>
            <td>

            </td>
            <td>
                <button type="submit" type="submit" name="shoppingCart" id="shoppingCart" value="Buy">Buy</button></td>
            </td>
        </tr>
    </table>
</form>
</body>

<%--<div class="total">--%>
<%--Total</br>--%>
<%--Total taxes</br>--%>
<%--Total with taxes</br>--%>
<%--</div>--%>

<%@ include file="footer.jsp" %>