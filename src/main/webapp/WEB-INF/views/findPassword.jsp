<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html" ; charset="UTF-8">
    <meta name="viewport" content="width=device-width" , initial-scale="1">
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
        <c:set var="userID" value="${userID}"/>
        <c:if test="${userID == null}">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle"
                       data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">메뉴<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/NKBlog/login">로그인</a></li>
                        <li><a href="/NKBlog/register">회원가입</a></li>
                    </ul>
                </li>
            </ul>
        </c:if>
        <c:if test="${userID != null}">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle"
                       data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">메뉴<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/NKBlog/logout">로그아웃</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>${userID}</li>
            </ul>
        </c:if>
    </div>
</nav>
<div class="container">
    <div class="col-lg-4"></div>
    <div class="col-lg-4">
        <form method="post">
            <h3 style="text-align: center;">찾으실 계정의 이메일을 입력해 주세요.</h3>
            <div class="input-group">
                <input class="form-control" type="text" name="email"/>
                <input type="submit" class="btn btn-primary" value="요청">
            </div>
        </form>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
