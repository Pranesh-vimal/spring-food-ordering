<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Admin | Products</title>

        <link
            href="${contextPath}/resources/css/bootstrap.min.css"
            rel="stylesheet"
        />
        <link href="${contextPath}/resources/css/common.css" rel="stylesheet" />
    </head>

    <body class="container">
        Product
        <div class="row">
            <c:forEach items="${products}" var="item">
            <div class="card mx-2" style="width: 18rem;">
                <img src="${item.imageUrl}" class="card-img-top" alt="${item.name}">
                    <div class="card-body">
                    <h5 class="card-title">${item.name}</h5>
                    <p class="card-text">${item.description}</p>
                    <%-- <a href="#" class="btn btn-primary">Go somewhere</a> --%>
                </div>
            </div>
        </c:forEach>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    </body>
</html>
