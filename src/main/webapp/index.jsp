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
                        <div class="col-md-6 mb-3">
                            <div class="card d-flex flex-row" style="height: 12rem;">
                                <img src="${item.imageUrl}" class=" w-50 card-img-top" alt="${item.name}">
                                <div class="card-body">
                                    <h5 class="card-title">${item.name}</h5>
                                    <p class="mb-2 prod-desc card-text">${item.description}</p>
                                    <p class="mb-2 card-text fw-bold">Rs. ${item.price} <br><small class=" fw-normal text-muted">${item.unit}</small></p>
                                    
                                    <button onclick="addtoCart(this.id)" id="${item.id}" class="btn btn-warning"><i class="me-1 fas fa-cart-plus"></i> Add to Cart</button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="col-md-4">
                <div class="cart-container">
                    <div class="cart border rounded">
                        <div class="cart-header px-3 py-2 rounded bg-dark text-white">
                            <h5 class="fw-bold text-center mb-0">Cart</h5>
                        </div>
                        <div class="d-flex mt-3 justify-content-center align-items-center no-items">
                            <p class="fw-bold">No Items in your cart</p>
                        </div>
                        <div class="mt-3 pw px-2 d-none cart-body">
                            <div class="cart-item ">                                
                            </div>
                                <a class="btn w-100 mb-5 btn-warning" href="#">Order Now</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</t:layout>

