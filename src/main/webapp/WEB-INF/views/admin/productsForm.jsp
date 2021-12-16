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
        >
            <h2 class="mt-2 form-signin-heading">Product ${productForm.getId() == null ? "Create" : "Edit"}</h2>
            <spring:bind path="name">
                <div class="mb-3 ${status.error ? 'has-error' : ''}">
                    <label class="form-label">Enter Name</label>
                    <form:input
                        type="text"
                        path="name"
                        class="form-control"
                        placeholder="name"
                    ></form:input>
                    <form:errors class="text-danger" path="name"></form:errors>
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
                    <form:errors class="text-danger" path="description"></form:errors>
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
                    <form:errors class="text-danger" path="price"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="category">
                <div class="mb-3 ${status.error ? 'has-error' : ''}">
                    <label class="form-label">Enter Category</label>
                    <form:select
                        path="category"
                        class="form-control"
                        items="${categories}"
                    ></form:select>
                    <form:errors class="text-danger" path="category"></form:errors>
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
                    <form:errors class="text-danger" path="unit"></form:errors>
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
                    <form:errors class="text-danger" path="image"></form:errors>
                </div>
            </spring:bind>

            <form:input type="hidden" path="id" placeholder="id"></form:input>

            <button class="btn btn-warning mb-5" type="submit">
                Submit
            </button>
            <a class="btn btn-dark mb-5" href="${contextPath}/admin/products">
                Back
            </a>
        </form:form>
    </div>

</t:layout>
