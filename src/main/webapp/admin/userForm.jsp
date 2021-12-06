<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<t:layout>
    <div class="container">
        <form:form
            method="POST"
            modelAttribute="userForm"
            class="form-signin"
            action="${contextPath}/admin/users/create"
        >
            <h2 class="mt-2 form-signin-heading">Product Create</h2>
            <spring:bind path="name">
                <div class="mb-3 ${status.error ? 'has-error' : ''}">
                    <label class="form-label">Enter Username</label>
                    <form:input
                        type="text"
                        path="username"
                        class="form-control"
                        placeholder="Enter Username"
                    ></form:input>
                    <form:errors path="username"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="email">
                <div class="mb-3 ${status.error ? 'has-error' : ''}">
                    <label class="form-label">Enter Email</label>
                    <form:input
                        type="email"
                        path="email"
                        class="form-control"
                        placeholder="Enter Email"
                    ></form:input>
                    <form:errors path="Enter Email"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="mb-3 ${status.error ? 'has-error' : ''}">
                    <label class="form-label">Enter Password</label>
                    <form:input
                        type="password"
                        path="password"
                        class="form-control"
                        placeholder="Enter Password"
                    ></form:input>
                    <form:errors path="password"></form:errors>
                </div>
            </spring:bind>

            <div class="mb-3">
                <label class="form-label">Enter Role</label>
                <form:select
                    path="role"
                    class="form-control"
                    items="${roles}"
                    itemValue="id"
                    itemLabel="name"
                ></form:select>
            </div>
            <form:input type="hidden" path="id" placeholder="id"></form:input>

            <button class="btn btn-warning" type="submit">
                Submit
            </button>
            <a class="btn btn-dark" href="${contextPath}/admin/products">
                Back
            </a>
        </form:form>
    </div>

</t:layout>
