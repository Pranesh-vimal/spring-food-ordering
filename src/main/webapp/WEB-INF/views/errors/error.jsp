<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<t:layout>
    <div class="container mt-5">
        <div class="row h-100 text-center">
            <div class="col-md-12">
                <div class=" error-template">
                    <h1>Oops!</h1>
                    <h2>403 Something Went Wrong</h2>
                    <div class="error-actions">
                        <a href="${contextPath}/admin" class="btn btn-warning "><span class="glyphicon glyphicon-home"></span>Take Me Home </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</t:layout>