<%@ page contentType="text/html;charset=UTF-8" language="java"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html"; charset="UTF-8">
    <meta name="viewport" content="width=device-width", initial-scale="1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <title>NKBlog</title>
</head>
<body>
    <nav class="navbar navbar-default">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
                    aria-expanded="false">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/NKBlog">NKBlog</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/NKBlog/main">메인</a></li>
                <li><a href="/NKBlog/bbs">게시판</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle"
                       data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">메뉴 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/NKBlog/login">로그인</a></li>
                        <li><a href="/NKBlog/register">회원가입</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
    <h1>현재 서버 시간${serverTime}</h1>
    <p><a href="/NKBlog/main">메인 페이지 이동</a></p>
    <p><a href="/NKBlog/404error">404 에러 페이지 이동</a></p>
    <p><a href="/NKBlog/500error">500 에러 페이지 이동</a></p>
</body>
</html>
