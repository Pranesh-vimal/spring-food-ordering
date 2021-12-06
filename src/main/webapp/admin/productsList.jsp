<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<t:layout>
    <div class="container mt-3">
        <h2 class="d-inline">Products</h2>
        <a href="${contextPath}/admin/products/create" class="btn d-inline float-end btn-success"> Create </a>
        <table class="table mt-2 table-striped">
            <tr>
                <th>ID</th>
                <th>Image</th>
                <th>Name</th>
                <th>Price</th>
                <th>Description</th>
                <th>Category</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td><img class="rounded" src="${product.imageUrl}" width="100" height="100"/></td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.description}</td>
                    <td>${product.category}</td>
                    <td>
                        <a class="btn btn-warning" href="/admin/products/edit?id=${product.id}">Edit</a>
                        <a class="btn btn-dark" href="/admin/products/delete?id=${product.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</t:layout>
