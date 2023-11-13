<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!DOCTYPE html>
<html>
<head>
    <title>WIFI</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <script type="text/javascript" src = "../js/wifi.js"></script>

</head>
<h1>와이파이 정보 구하기</h1>
<a style="font-size: 20px; " href="/index.jsp">홈</a> |
<a style="font-size: 20px; " href="/views/history.jsp">위치 히스토리 목록</a> |
<a style="font-size: 20px; " href="/views/save.jsp">Open API 와이파이 정보 가져오기</a>|
<a style="font-size: 20px; " href="/views/bookmarkShow.jsp">북마크 보기</a>|
<a style="font-size: 20px; " href="/views/bookmarkGroup.jsp">북마크 그룹 관리</a>
</br><br>

<body>
<div>

    <form action="/views/list.jsp" method="post" onsubmit="return getWifi();">
        LAT : <input type="text" id="lat" , name="lat" value="0.0"> ,
        LNT : <input type="text" id="lnt" , name="lnt" value="0.0">
        <button type="button" onclick="getLocation();">내 위치 가져오기</button>
        <button>근처 WIFI 정보 보기</button>
    </form>

</div>
<br>
<table class="table table-striped table-bordered" >
    <thead style="background: #04AA6D">
    <tr style="font-size: 13px ; color: white">
        <th>거리(km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <tbody>
    <tr style="font-size: 15px;">
        <td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
    </tr>
    </tbody>
</table>
</body>
</html>