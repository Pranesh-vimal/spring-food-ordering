<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<t:layout>
    <div class="container">
        <c:if test="${pageContext.request.userPrincipal.name != null}">

            <h2 class=" pt-4">
                Welcome <span class="text-warning">${pageContext.request.userPrincipal.name}  </span>
            </h2>
            <div class="row mt-5">
                <c:if test="${pageContext.request.userPrincipal.getAuthorities() == '[ADMIN]'}">
                    <div class="col-md-4">
                        <div class="c1 card">
                            <div class="card-body">
                                <div class="card-title h5">Number of Users - <span >${userCount}</span></div>
                                <p class="card-text"><a href="/admin/users">Click Here To View</a></p>
                            </div>
                        </div>
                    </div>
                </c:if>                
                <div class="col-md-4">
                    <div class="c2 card">
                        <div class="card-body">
                            <div class="card-title h5">Number of Orders - <span >${orderCount}</span></div>
                            <p class="card-text"><a href="/admin/orders">Click Here To View</a></p>
                        </div>
                    </div>
                </div>
                <c:if test="${pageContext.request.userPrincipal.getAuthorities() == '[ADMIN]'}">
                    <div class="col-md-4">
                        <div class="c3 card">
                            <div class="card-body">
                                <div class="card-title h5">Number of Products - <span>${productCount}</span></div>
                                <p class="card-text"><a href="/admin/products">Click Here To View</a></p>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </c:if>
    </div>
</t:layout>
