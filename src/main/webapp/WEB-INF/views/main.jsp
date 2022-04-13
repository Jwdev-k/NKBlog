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
            <li class="active"><a href="/NKBlog/main">메인</a></li>
            <li><a href="/NKBlog/bbs?pageNumber=1">게시판</a></li>
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
    <div class="container">
        <div class="jumbotron">
            <div class="container">
                <h1>INFO</h1>
                <p>부트스트랩을 이용하여 만든 자바 스프링 웹 사이트 입니다.</p>
                <p>마이바티스를 이용하여 블로그에필요한 간단한 DB 로직만 구현하였습니다.</p>
                <a class="btn btn-primary btn-pull" role="button">NEXT</a>
            </div>
        </div>
    </div>
    <center>
        <div id="map" style="width:500px;height:400px;"></div>
    </center>
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=685318fc5604f6208564f2b40c40c4ba"></script>
    <script>
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div
            mapOption = {
                center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
            };
        var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
        // 마커가 표시될 위치입니다
        var markerPosition = new kakao.maps.LatLng(33.450701, 126.570667);
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            position: markerPosition
        });
        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map);
        // 아래 코드는 지도 위의 마커를 제거하는 코드입니다
        // marker.setMap(null);
    </script>
</nav>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
