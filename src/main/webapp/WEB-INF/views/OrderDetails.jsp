<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<t:layout>
    <div class="container mt-5">
        <div class="order-details">
            <c:if test="${order.id != null}">
                <h5>Order #${order.id}</h5>
                <h5>Order Date: ${order.created_at}</h5>
                <h5>Order Status: ${order.status}</h5>
                <h5>Order Payment: ${order.payment}</h5>
                <h5>Order Total: Rs. ${order.total}</h5>
                <h5>Order Items:</h5>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Image</th>
                            <th>Product</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${order.orderItems}" var="item">
                            <tr>
                                <td width="100"><img src="${item.product.imageUrl}"  alt="${item.product.name}" class="w-100" /></td>
                                <td>${item.product.name}</td>
                                <td>${item.quantity}</td>
                                <td>Rs. ${item.product.price}</td>
                                <td>Rs. ${item.subTotal}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="d-flex flex-column justify-content-center align-items-center">
                    <h4 class="text-center">Thank You For Your Purchase!</h4>
                    <button class="btn btn-dark" onclick="window.print()">Print</button>
                </div>
            </c:if>
            <c:if test="${order.id == null}">
                <div class="d-flex flex-column align-items-center justify-content-center">
                    <h3 class="text-center">No order found</h3>
                    <a href="/orders" class="btn-warning btn">Back to Orders</a>
                </div>
            </c:if>

        </div>
    </div>
</t:layout>