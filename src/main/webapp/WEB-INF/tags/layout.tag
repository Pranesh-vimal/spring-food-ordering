<%@ tag description="Home Wrapper Tag" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title> ${title} | Food Management System</title>

        <link
            href="${contextPath}/resources/css/bootstrap.min.css"
            rel="stylesheet"
        />
        <link href="${contextPath}/resources/css/common.css" rel="stylesheet" />
    </head>
    <body>
        <div class="user-layout">
            <div class="header pb-5">
                <jsp:include page="${contextPath}/common/header.jsp" />
            </div>
            <div class="contents">
                <jsp:doBody/>
            </div>
            <div class="footer">
                <jsp:include page="${contextPath}/common/footer.jsp" />
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    </body>
</html>