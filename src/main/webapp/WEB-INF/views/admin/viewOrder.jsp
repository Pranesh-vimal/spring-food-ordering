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

                <h5>Order Status: 
                    <c:choose>
                        <c:when test="${order.status == 'Cancelled'}">
                            <span class="text-danger">Cancelled</span>
                        </c:when>
                        <c:otherwise>
                            <form method="post" name="updateStatus" action="/admin/orders/${order.id}" class="d-inline">
                                <select onchange="this.form.submit()" style="width: unset !important;" class="form-control d-inline" name="status" id="status">
                                    <option value="Created" ${order.status=='Created' ? 'selected' : ''}>Created</option>
                                    <option value="Pending" ${order.status=='Pending' ? 'selected' : ''}>Pending</option>
                                    <option value="Completed" ${order.status=='Completed' ? 'selected' : ''}>Completed</option>
                                </select>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                        </c:otherwise>
                    </c:choose>
                </h5>
                <h5>Order Payment: ${order.payment}</h5>
                <h5>Order Transaction: ${order.transaction_id}</h5>
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
                <div class="d-flex justify-content-center align-items-center">
                    <button class="btn btn-dark me-2" onclick="window.print()">Print</button>
                    <a href="/admin/orders" class="btn-warning btn">Back to Orders</a>
                </div>
            </c:if>
            <c:if test="${order.id == null}">
                <div class="d-flex flex-column align-items-center justify-content-center">
                    <h3 class="text-center">No order found</h3>
                    <a href="/admin/orders" class="btn-warning btn">Back to Orders</a>
                </div>
            </c:if>

        </div>
    </div>
</t:layout>