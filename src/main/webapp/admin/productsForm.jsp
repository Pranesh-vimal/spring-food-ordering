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
            modelAttribute="productForm"
            class="form-signin"
            enctype="multipart/form-data"
            action="${contextPath}/admin/products/create"
        >
            <h2 class="mt-2 form-signin-heading">Product Create</h2>
            <spring:bind path="name">
                <div class="mb-3 ${status.error ? 'has-error' : ''}">
                    <label class="form-label">Enter Name</label>
                    <form:input
                        type="text"
                        path="name"
                        class="form-control"
                        placeholder="name"
                    ></form:input>
                    <form:errors path="name"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="description">
                <div class="mb-3 ${status.error ? 'has-error' : ''}">
                    <label class="form-label">Enter Description</label>
                    <form:input
                        type="text"
                        path="description"
                        class="form-control"
                        placeholder="description"
                    ></form:input>
                    <form:errors path="description"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="price">
                <div class="mb-3 ${status.error ? 'has-error' : ''}">
                    <label class="form-label">Enter Price</label>
                    <form:input
                        type="text"
                        path="price"
                        class="form-control"
                        placeholder="price"
                    ></form:input>
                    <form:errors path="price"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="category">
                <div class="mb-3 ${status.error ? 'has-error' : ''}">
                    <label class="form-label">Enter Category</label>
                    <form:input
                        type="text"
                        path="category"
                        class="form-control"
                        placeholder="category"
                    ></form:input>
                    <form:errors path="category"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="unit">
                <div class="mb-3 ${status.error ? 'has-error' : ''}">
                    <label class="form-label">Enter Unit</label>
                    <form:input
                        type="text"
                        path="unit"
                        class="form-control"
                        placeholder="unit"
                    ></form:input>
                    <form:errors path="unit"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="image">
                <div class="mb-3 ${status.error ? 'has-error' : ''}">
                    <label class="form-label">Upload Image</label>
                    <form:input
                        type="file"
                        path="image"
                        class="form-control"
                        placeholder="image"
                    ></form:input>
                    <form:errors path="image"></form:errors>
                </div>
            </spring:bind>

            <form:input type="hidden" path="id" placeholder="id"></form:input>

            <button class="btn btn-success" type="submit">
                Submit
            </button>
            <a class="btn btn-primary" href="${contextPath}/admin/products">
                Back
            </a>
        </form:form>
    </div>

</t:layout>
