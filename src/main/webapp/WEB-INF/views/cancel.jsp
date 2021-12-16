<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<t:layout>
    <div class="container">
        <div class="mt-5 text-center payment-failed">
            <div class="payment-failed-header">
                <h1><i class="far text-warning fa-frown-open"></i></h1>
                <h1 class="text-warning">Payment Failed</h1>
            </div>
            <div class="payment-failed-body">
                <p>Your payment was not successful. Please try again.</p>
                <p>If you continue to have problems, please contact us.</p>
            </div>
            <div class=" payment-failed-footer">
                <a class="btn-warning btn" href="${contextPath}/">Back to Home</a>
            </div>
        </div>
    </div>
</t:layout>