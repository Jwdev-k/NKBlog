<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <li class="active"><a href="/NKBlog/bbs?pageNumber=1">게시판</a></li>
        </ul>
        <c:set var="userID" value="${userID}" />
        <c:if test= "${userID == null}">
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
        <c:if test= "${userID != null}">
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
        </c:if>
    </div>
</nav>
<div class="container">
    <div class="row">
        <table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
            <thead>
            <tr>
                <th style="background-color: #eeeeee; text-align: center;">번호</th>
                <th style="background-color: #eeeeee; text-align: center;">제목</th>
                <th style="background-color: #eeeeee; text-align: center;">작성자</th>
                <th style="background-color: #eeeeee; text-align: center;">작성일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${boardList}" var="list">
                <tr>
                    <td>${list.bno}</td>
                    <td><a href="/NKBlog/bbs/view?bno=${list.bno}"/>${list.title}</td></a>
                    <td>${list.uid}</td>
                    <td>${list.created}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:set var="pageNumber" value="${pageNumber}" />
        <c:set var="nextPageNumber" value="${nextPageNumber}" />
        <c:set var="userID" value="${userID}" />
        <c:if test= "${nextPageNumber != null}">
            <a href="/NKBlog/bbs?pageNumber=${nextPageNumber}" class="btn btn-success btn-arraw-left">◀</a>
        </c:if>
        <c:if test= "${pageNumber > 1}">
            <a href="/NKBlog/bbs?pageNumber=${pageNumber - 1}" class="btn btn-success btn-arraw-left">▶</a>
        </c:if>
        <c:if test= "${userID != null}">
            <a href="/NKBlog/bbs/write" class="btn btn-primary pull-right">글쓰기</a>
        </c:if>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
