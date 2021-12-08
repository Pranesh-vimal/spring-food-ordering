<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<t:layout>
    <div class="container pb-5 mt-3">
        <h2 class="d-inline">Orders</h2>
        <table class="table mt-2 table-striped">
            <tr>
                <th>ID</th>
                <th>Name</th>               
                <th>Phone</th>
                <th>Email</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            <c:forEach items="${orders}" var="item">
                <tr>
                    <td>${item.id}</td>                    
                    <td>${item.name}</td>                    
                    <td>${item.phone}</td>
                    <td>${item.email}</td>
                    <td>${item.status}</td>
                    <td>
                        <a class="btn btn-warning" href="/admin/orders/${item.id}">View</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</t:layout>
