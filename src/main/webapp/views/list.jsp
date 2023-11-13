<%@ page import="dto.WifiDTO" %>
<%@ page import="dto.HistoryDTO" %>
<%@ page import="service.WifiService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title>LIST</title>
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
<%
    Double lat = Double.parseDouble(request.getParameter("lat"));
    Double lnt = Double.parseDouble(request.getParameter("lnt"));
    HistoryDTO historyDTO = new HistoryDTO();
    historyDTO = historyDTO.builder()
            .CREATED_TIME(new Timestamp(System.currentTimeMillis()))
            .LAT(lat)
            .LNT(lnt)
            .build();
    WifiService service = new WifiService();
    service.saveHistory(historyDTO);

%>
<div>

    <form action="list.jsp" method="post" onsubmit="return getWifi();" >
        LAT : <input type="text" id="lat" , name="lat" value=<%=lat%>> ,
        LNT : <input type="text" id="lnt" , name="lnt" value=<%=lnt%>>
        <button type="button" onclick="getLocation();">내 위치 가져오기</button>
        <button >근처 WIFI 정보 보기</button>
    </form>
</div>
<br>
<table class="table table-striped table-bordered">
    <thead>
    <tr style="font-size: 13px;">
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
    <%
        WifiService wifiService = new WifiService();
        List<WifiDTO> resWifi = wifiService.showWifi(lat, lnt);
    %>
    <% for(WifiDTO wifiDTO : resWifi){ %>
    <tr style="font-size: 15px;">
        <td><%= wifiDTO.getDistance() %>km </td >
        <td><%= wifiDTO.getX_SWIFI_MGR_NO()%></td>
        <td><%= wifiDTO.getX_SWIFI_WRDOFC() %> </td>
        <td><a href="detail.jsp?WifiNo=<%= wifiDTO.getX_SWIFI_MGR_NO() %>&distance=<%=wifiDTO.getDistance()%>&wifiname=<%=wifiDTO.getX_SWIFI_MAIN_NM()%>"><%= wifiDTO.getX_SWIFI_MAIN_NM() %></a></td>
        <td><%= wifiDTO.getX_SWIFI_ADRES1() %> </td>
        <td><%= wifiDTO.getX_SWIFI_ADRES2() %> </td>
        <td><%= wifiDTO.getX_SWIFI_INSTL_FLOOR() %> </td>
        <td><%= wifiDTO.getX_SWIFI_INSTL_TY() %> </td>
        <td><%= wifiDTO.getX_SWIFI_INSTL_MBY() %> </td>
        <td><%= wifiDTO.getX_SWIFI_SVC_SE() %> </td>
        <td><%= wifiDTO.getX_SWIFI_CMCWR() %> </td>
        <td><%= wifiDTO.getX_SWIFI_CNSTC_YEAR() %> </td>
        <td><%= wifiDTO.getX_SWIFI_INOUT_DOOR() %> </td>
        <td><%= wifiDTO.getX_SWIFI_REMARS3() %> </td>
        <td><%= wifiDTO.getLAT() %> </td>
        <td><%= wifiDTO.getLNT() %> </td>
        <td><%= wifiDTO.getWORK_DTTM() %> </td>
    </tr>
    <%}%>
    </tbody>
</table>
</body>
</html>
<style>
    form{
        display: inline;
    }
    td{
        text-align: center;
        align-content: center;

    }
    th{
        text-align: center;
        align-content: center;
        color: white;
        background-color: #04AA6D;
    }
</style>