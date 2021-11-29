<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.blog.domain.impl.boardDAO" %>
<%@ page import="com.blog.domain.boardDTO" %>
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
        int bbsID = 0;
        if (request.getParameter("bno") != null) {
            bbsID = Integer.parseInt(request.getParameter("bno"));
        }
        if (bbsID == 0) {
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('유효하지 않은 글입니다.')");
            script.println("location.href = '/NKBlog/bbs'");
            script.println("</script>");
        }
        boardDTO bbs = new boardDAO().getBbs(bbsID);
    %>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/NKBlog/main">메인</a></li>
            <li><a href="/NKBlog/bbs">게시판</a></li>
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
                <th colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글 보기</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td style="width: 20%;">글 제목</td>
                    <td colspan="2"><%= bbs.getTitle()%></td>
                </tr>
                <tr>
                    <td>작성자</td>
                    <td colspan="2"><%= bbs.getUid()%></td>
                </tr>
                <tr>
                    <td>작성일자</td>
                    <td colspan="2"><%= bbs.getCreated()%></td>
                </tr>
                <tr>
                    <td>글 내용</td>
                    <td colspan="2" style="min-height: 200px; text-align: left;"><%= bbs.getContent()%></td>
                </tr>
            </tbody>
        </table>
        <a href="/NKBlog/bbs" class="btn btn-primary">목록</a>
        <%
        if (userID != null && userID.equals(bbs.getUid())) {
        %>
            <a href="update.jsp?bno=<%= bbsID %>" class="btn btn-primary">수정</a>
            <a href="deleteAction.jsp?bno=<%= bbsID %>" class="btn btn-primary">삭제</a>
        <%
        }
        %>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>