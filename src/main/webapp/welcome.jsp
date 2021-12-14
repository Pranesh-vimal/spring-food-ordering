<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<t:layout>
    <div class="container">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h2>
                Welcome ${pageContext.request.userPrincipal.getAuthorities()}  
            </h2>
            ${userCount}
            ${orderCount}
            ${productCount}
        </c:if>
    </div>
</t:layout>
