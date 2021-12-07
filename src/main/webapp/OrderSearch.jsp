<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<t:layout>
    <div class="container mt-5">
        <div class="order-search">
            <form method="post" action="${contextPath}/orders">
                <div class="mb-3">
                    <label for="orderId">Order ID</label>
                    <input type="text" class="form-control" id="orderId" name="orderId" placeholder="Order ID">
                </div>
                <div class="mb-3">
                    <label for="phoneNumber">Phone Number</label>
                    <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Enter Your Phone">
                </div>
                <button class="btn btn-warning">Search Order</button>
            </form>
            ${error}
        </div>
    </div>
</t:layout>
