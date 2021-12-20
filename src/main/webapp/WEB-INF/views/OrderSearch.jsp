<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<t:layout>
    <div class="container mt-5">
        <div class="order-search">
            <form method="POST" action="${contextPath}/orders">
                <div class="mb-3">
                    <label for="orderId">Order ID</label>
                    <input type="text" class="form-control" id="orderId" name="orderId" placeholder="Order ID" autocomplete="off">
                </div>
                <div class="mb-3">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Enter Your Email" autocomplete="off">
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <button class="btn btn-warning">Search Order</button>
            </form>            
            <c:if test ="${ not empty error }">
                <p class="text-danger">${error}</p>
            </c:if>
        </div>
    </div>
</t:layout>
