<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Admin | Products Create</title>

        <link
            href="${contextPath}/resources/css/bootstrap.min.css"
            rel="stylesheet"
        />
        <link href="${contextPath}/resources/css/common.css" rel="stylesheet" />
    </head>

    <body>
        <div class="container">
            <form:form
                method="POST"
                modelAttribute="productForm"
                class="form-signin"
                enctype="multipart/form-data"
                action="${contextPath}/admin/products/create"
            >
                <h2 class="form-signin-heading">Product Create</h2>
                <spring:bind path="name">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
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
                    <div class="form-group ${status.error ? 'has-error' : ''}">
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
                    <div class="form-group ${status.error ? 'has-error' : ''}">
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
                    <div class="form-group ${status.error ? 'has-error' : ''}">
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
                    <div class="form-group ${status.error ? 'has-error' : ''}">
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
                    <div class="form-group ${status.error ? 'has-error' : ''}">
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

                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    Submit
                </button>
            </form:form>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    </body>
</html>
