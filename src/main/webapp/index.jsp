<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<t:layout>
    <div class="container">
        <div class="row w-100 mt-3 mb-5">
            <div class="col-md-8">
                <div class="row">
                    <c:forEach items="${products}" var="item">
                        <div class="col-md-6">
                            <div class="card d-flex flex-row" style="height: 12rem; width: 20rem;">
                                <img src="${item.imageUrl}" class=" w-50 card-img-top" alt="${item.name}">
                                <div class="card-body">
                                    <h5 class="card-title">${item.name}</h5>
                                    <p class="mb-2 prod-desc card-text">${item.description}</p>
                                    <p class="mb-2 card-text fw-bold">Rs. ${item.price} <br><small class=" fw-normal text-muted">Per ${item.unit} Kg</small></p>
                                    
                                    <a class="btn btn-warning"><i class="me-1 fas fa-cart-plus"></i>Add to Cart</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</t:layout>

