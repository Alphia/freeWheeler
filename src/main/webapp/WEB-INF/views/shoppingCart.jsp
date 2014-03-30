<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Reserve Item"/>
<%@ include file="header.jsp" %>

<script type="text/javascript">
    $(function () {
        new Survey().showSurvey(new SurveyPopUp());
    })
</script>

<div class="page-action">Item reserved !</div>

<table class="table">
    <thead>
    <tr>
        <th>Description</th>
        <th>Type</th>
        <th>taxes</th>
    </tr>

    </thead>
    <tbody>
    <tr>
        <td>${item.description}</td>
        <td>${item.type}</td>
        <%--<td>${item.taxes}</td>--%>
        <td>
            <button>check out</button>
        </td>
    </tr>

    </tbody>
</table>

<%@ include file="footer.jsp" %>