<%@ page import="java.util.ArrayList" %>
<%@ page import="com.blog.domain.boardDTO" %>
<%@ page import="com.blog.service.boardService" %>
<%@ page import="com.blog.service.impl.boardServiceimpl" %>
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
    <%
        String userID = null;
        if (session.getAttribute("userID") != null) {
            userID = (String) session.getAttribute("userID");
        }
        int pageNumber = 1;
        if (request.getParameter("pageNumber") != null) {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
    %>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li><a href="/NKBlog/main">메인</a></li>
            <li class="active"><a href="/NKBlog/bbs">게시판</a></li>
        </ul>
        <%
            if(userID == null) {

        %>
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
        <%
        } else {
        %>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle"
                   data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="false">메뉴<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/NKBlog/main">로그아웃</a></li>
                    <%
                        session.invalidate();
                    %>
                </ul>
            </li>
        </ul>
        <%
            }
        %>
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
            <%
                boardServiceimpl bbs = new boardServiceimpl();
                ArrayList<boardDTO> list = bbs.getboardlist(pageNumber);
                for (int i = 0; i < list.size(); i++) {
            %>
            <tr>
                <td><%= list.get(i).getBno()%></td>
                <td><a href="/NKBlog/bbs/view?bno=<%= list.get(i).getBno()%>"><%= list.get(i).getTitle()%></a></td>
                <td><%= list.get(i).getUid()%></td>
                <td><%= list.get(i).getCreated()%></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <%
            if (pageNumber != 1) {
                int n = pageNumber - 1;
        %>
        <a href="/NKBlog/bbs?pageNumber=<%=n%>" class="btn btn-success btn-arraw-left">이전</a>
        <%}
            if (bbs.nextpage(pageNumber)) {
                int n = pageNumber + 1;
        %>
        <a href="/NKBlog/bbs?pageNumber=<%=n%>" class="btn btn-success btn-arraw-left">다음</a>
        <%}%>
        <a href="/NKBlog/bbs/write" class="btn btn-primary pull-right">글쓰기</a>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
