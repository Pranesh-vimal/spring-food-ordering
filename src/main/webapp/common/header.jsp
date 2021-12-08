<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="header">
    <nav class="navbar p-1 navbar-expand-lg fixed-top navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand fw-bold" href="${contextPath}/"><span class="text-warning">Food Order</span> Management</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.getName() != null}">
                            <li class="nav-item">
                                <a class="nav-link" aria-current="page" href="${contextPath}/admin/dashboard">Dashboard</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${contextPath}/admin/orders">Orders</a>
                            </li>
                            <c:if test="${pageContext.request.userPrincipal.getAuthorities() == '[ADMIN]'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="${contextPath}/admin/products">Products</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="${contextPath}/admin/users">Users</a>
                                </li>
                            </c:if>
                            <li class="nav-item">
                            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                                <a href="#" class="nav-link" onclick="document.forms['logoutForm'].submit()">Logout</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item">
                                <a class="nav-link" aria-current="page" href="${contextPath}/">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${contextPath}/orders">Orders</a>
                            </li>
                        </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
        </div>
      </nav>
</div>

