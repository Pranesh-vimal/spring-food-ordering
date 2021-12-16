<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<t:authLayout>
    <div class="auth">
        <div class="row h-100 me-0">
            <div class="col-md-6 d-none d-md-block">
                <div class="authimg"></div>
            </div>
            <div class="col-md-6">
                <div class="d-flex justify-content-center h-100 align-items-center auth-form">
                    <form method="POST" action="${contextPath}/admin/login" class="form-signin">
                        <h2 class="mb-5"><span class="text-warning">Food Order</span> Management</h2>
                        <h2 class="form-heading">Log in</h2>
                        <div class="form-group ${error != null ? 'has-error' : ''}">
                            <p class="mb-3 text-success">${message}</p>

                            <div class="mb-3">
                                <label class="form-label">Username</label>
                                <input
                                    name="username"
                                    type="text"
                                    class="form-control"
                                    placeholder="Enter your Username"
                                    autofocus="true"
                                />
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Password</label>
                                <input
                                    name="password"
                                    type="password"
                                    class="form-control"
                                    placeholder="Enter your Password"
                                />
                            </div>
                            <p class="mb-3 text-danger">${error}</p>
                            <input
                                type="hidden"
                                name="${_csrf.parameterName}"
                                value="${_csrf.token}"
                            />
                            <button
                                class="btn mb-3 w-100 btn-warning btn-block"
                                type="submit"
                            >
                                Log In
                            </button>                        
                            <a href="${contextPath}/"
                                class="btn w-100 btn-dark "
                                >Visit Product Home</a
                            >                            
                        </div>
                    </form>
                </div>
            </div>
            
        </div>
    </div>
</t:authLayout>
