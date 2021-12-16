<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<t:layout>
    <div class="container">
        <div class="mt-5 text-center payment-success">
            <div class="payment-success-header">
                <h1><i class="far text-warning fa-smile"></i></h1>
                <h1 class="text-warning">Payment Success</h1>
            </div>
            <div class="payment-success-body">
                <p>Your payment has been successfully processed.</p>
                <p>Your order number is <strong>${order.id}</strong>.</p>
                <p>You will receive an email confirmation shortly.</p>
            </div>
            <div class=" payment-success-footer">
                <a class="btn-warning btn" href="${contextPath}/orders">View Orders</a>
                <a class="btn-dark btn" href="${contextPath}/">Back to Home</a>
            </div>
        </div>
    </div>
</t:layout>