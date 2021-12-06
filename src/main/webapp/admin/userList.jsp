<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<t:layout>
    <div class="container mt-3">
        <h2 class="d-inline">Users</h2>
        <a href="${contextPath}/admin/products/create" class="btn d-inline float-end btn-success"> Create </a>
        <table class="table mt-2 table-striped">
            <tr>
                <th>ID</th>
                <th>Username</th>               
                <th>Role</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>                    
                    <td>${user.username}</td>                    
                    <td>${user.role.getName()}</td>
                    <td>
                        <a class="btn btn-warning" href="/admin/users/${user.id}/edit">Edit</a>
                        <a class="btn btn-dark" href="/admin/users/${user.id}/delete">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</t:layout>
