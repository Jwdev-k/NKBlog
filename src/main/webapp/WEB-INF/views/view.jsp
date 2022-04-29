<%@ page import="web.nkblog.domain.impl.boardDAO" %>
<%@ page import="web.nkblog.domain.boardDTO" %>
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
    <c:set var="userID" value="${userID}"/>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/NKBlog/main">메인</a></li>
            <li><a href="/NKBlog/bbs?pageNumber=1">게시판</a></li>
        </ul>
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
<c:set var ="board" value="${boardData}"/>
<c:set var="bno" value="${bbsID}"/>
<div class="container">
    <div class="row">
        <table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
            <thead>
            <tr>
                <th colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글 보기</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td style="width: 20%;">글 제목</td>
                <td colspan="2">${board.title}
                </td>
            </tr>
            <tr>
                <td>작성자</td>
                <td colspan="2">${board.uid}
                </td>
            </tr>
            <tr>
                <td>작성일자</td>
                <td colspan="2">${board.created}
                </td>
            </tr>
            <tr>
                <td>글 내용</td>
                <td colspan="2" style="min-height: 200px; text-align: left;">${board.content}
                </td>
            </tr>
            <c:if test="${File != null}">
            <tr>
                <td>첨부파일</td>
                <td colspan="2"><a href="/NKBlog/download?bno=${bno}">${File}</a> 파일크기: ${FileSize} byte
                </td>
            </tr>
            </c:if>
            </tbody>
        </table>
        <c:if test="${userID == board.uid}">
        <a href="/NKBlog/bbs/view/update?bno=${bno}" class="btn btn-primary">수정</a>
        <a href="/NKBlog/bbs/view/deleteAction?bno=${bno}" class="btn btn-primary">삭제</a>
        </c:if>
    </div>
</div>
<c:if test="${userID != null}">
<div class="container">
    <div class="row">
        <form method="post" action="/NKBlog/comment/add?bno=${bno}">
            <table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
                <tbody>
                <tr><td style="width: 20%;">댓글</td></tr>
                <tr><td><textarea class="form-control" placeholder="내용" name="comment" maxlength="100" style="height: 40px; resize: none;"> </textarea></td></tr>
                </tbody>
            </table>
            <input type="submit" class="btn btn-primary pull-right" value="댓글 등록">
        </form>
    </div>
    <a href="/NKBlog/bbs" class="btn btn-primary">목록</a>
</div>

</c:if>
<div class="container">
    <div class="row">
        <table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
            <thead>
            <tr>
                <th style="background-color: #eeeeee; text-align: center;">작성자</th>
                <th style="background-color: #eeeeee; text-align: center;">내용</th>
                <th style="background-color: #eeeeee; text-align: center;">작성일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${commentList}" var="list2">
                <tr>
                    <td>${list2.uid}</td>
                    <td>${list2.comment}</td>
                    <td>${list2.created}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
