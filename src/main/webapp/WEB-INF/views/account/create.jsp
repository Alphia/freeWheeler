<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Create Account"/>

<%@ include file="../header.jsp" %>

    <div class="page-action">
        Create a new account
    </div>

    <c:if test="${not empty validationMessage.errors}">
        <div id="resultsMessage" class="page-action error">
            There were errors.
        </div>
    </c:if>

	<form action="/account/create" method="post">
            <label for="fld_email">Email</label>
            <div class="controls">

                    <c:choose>
                        <c:when test="${empty validationMessage.errors['email']}">
                            <c:choose>
                                <c:when test="${empty validationMessage.userDetails}">
                                    <input type="text" id="fld_email" placeholder="somebody@something.com" name="email">
                                </c:when>
                                <c:otherwise>
                                    <input type="text" id="fld_email" placeholder="somebody@something.com" name="email" value="${validationMessage.userDetails.getEmail_address()}">
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="fld_email" placeholder="somebody@something.com" name="email">
                            <span class="text-error">${validationMessage.errors["email"]}</span>
                        </c:otherwise>
                    </c:choose>
        </div>

        <div>
            <label for="fld_password">Password</label>
            <div class="controls">
                <input type="password" id="fld_password" placeholder="secret password" name="password">
                <c:if test="${not empty validationMessage.errors['password']}">
                    <span class="text-error">${validationMessage.errors["password"]}</span>
                </c:if>
                <c:if test="${not empty validationMessage.errors['validPassword']}">
                    <span class="text-error">${validationMessage.errors["validPassword"]}</span>
                </c:if>
                <c:if test="${empty validationMessage.errors['validPassword'] and empty validationMessage.errors['password']}">
                    <span class="text-countryNote" style="overflow:hidden; width:50px; max-width:50px;">Must be between 8-20 characters, and contain at least 1 number, 1 lowercase letter, 1 uppercase letter, 1 special character</span>
                </c:if>
            </div>
        </div>

        <div>
            <label for="fld_confirmPassword">Confirm Password</label>
            <div class="controls">
                <input type="password" id="fld_confirmPassword" placeholder="secret password" name="confirmPassword" >
                <c:if test="${not empty validationMessage.errors['confirmPassword']}">
                    <span class="text-error">${validationMessage.errors["confirmPassword"]}</span>
                </c:if>
            </div>
        </div>

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
                                <input type="text" id="fld_name" placeholder="Your Name" name="name" value="${validationMessage.userDetails.getAccount_name()}">
                            </c:otherwise>
                           </c:choose>
                    </c:when>
                    <c:otherwise>
                        <input type="text" id="fld_name" placeholder="Your Name" name="name">
                        <span class="text-error">${validationMessage.errors["name"]}</span>
                    </c:otherwise>
                 </c:choose>
            </div>
        </div>

        <div>
            <label for="fld_phoneNumber">Phone Number</label>
            <div class="controls">
                <c:choose>
                    <c:when test="${empty validationMessage.errors['phoneNumber']}">
                        <c:choose>
                            <c:when test="${empty validationMessage.userDetails}">
                                <input type="text" id="fld_phoneNumber" placeholder="123456789" name="phoneNumber">
                            </c:when>
                            <c:otherwise>
                                <input type="text" id="fld_phoneNumber" placeholder="123456789" name="phoneNumber" value="${validationMessage.userDetails.getPhoneNumber()}">
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <input type="text" id="fld_phoneNumber" placeholder="123456789" name="phoneNumber">
                        <span class="text-error">${validationMessage.errors["phoneNumber"]}</span>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>

        <div>
            <label for="fld_country">Country</label>
            <div class="controls">
                <select id="fld_country" name="country">
                    <option value="0">Select</option>
                    <c:forEach var="country" items="${validationMessage.countries}" varStatus="row">
                        <c:if test="${country.getCountry_id() == validationMessage.userDetails['country_id']}">
                            <option selected value="${country.getCountry_id()}">${country.getCountry_name()}</option>
                        </c:if>

                        <option value="${country.getCountry_id()}">${country.getCountry_name()}</option>
                    </c:forEach>
                </select>
                <c:if test="${not empty validationMessage.errors['country']}">
                    <span class="text-error">${validationMessage.errors["country"]}</span>
                </c:if>
            </div><br/>
            <div class="controls">
                <label></label>
                <span class="text-countryNote">If your country is not listed then we don't ship there. Please check back later.</span>
            </div>
        </div>

        <div>
            <div class="controls">
                <button type="submit" id="createAccount" value="Submit">Create Account</button>
            </div>
        </div>

	</form>

<%@ include file="../footer.jsp" %>
