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
                    <form:form
                        method="POST"
                        modelAttribute="userForm"
                        class="form-signin"
                    >
                        <h2 class="mb-5"><span class="text-warning">Food Order</span> Management</h2>
                        <h2 class="form-signin-heading">Create your account</h2>
                        <spring:bind path="username">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="mb-3">
                                    <label class="form-label">Username</label>
                                    <form:input
                                        type="text"
                                        path="username"
                                        class="mb-1 form-control"
                                        placeholder="Enter your username"
                                        autofocus="true"
                                    ></form:input>
                                    <form:errors class="text-danger " path="username"></form:errors>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="password">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="mb-3">
                                    <label class="form-label">Password</label>
                                    <form:input
                                        type="password"
                                        path="password"
                                        class="mb-1 form-control"
                                        placeholder="Password"
                                    ></form:input>
                                    <form:errors class="text-danger " path="password"></form:errors>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="passwordConfirm">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="mb-3">
                                    <label class="form-label">Confirm Password</label>
                                    <form:input
                                        type="password"
                                        path="passwordConfirm"
                                        class="mb-1 form-control"
                                        placeholder="Confirm your password"
                                    ></form:input>
                                    <form:errors class="text-danger " path="passwordConfirm"></form:errors>
                                </div>
                            </div>
                        </spring:bind>

                        <button class="btn w-100 btn-warning " type="submit">
                            Submit
                        </button>
                    </form:form>
                </div>
            </div>

        </div>
    </div>
</t:authLayout>
