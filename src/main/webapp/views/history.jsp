<%@ page import="dto.HistoryDTO" %>
<%@ page import="service.WifiService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title>HISTORY</title>
    <script type="text/javascript" src = "../js/wifi.js"></script>
</head>
<body>
<h1>위치 히스토리 목록</h1>
<a style="font-size: 20px; " href="/index.jsp">홈</a> |
<a style="font-size: 20px; " href="/views/history.jsp">위치 히스토리 목록</a> |
<a style="font-size: 20px; " href="/views/save.jsp">Open API 와이파이 정보 가져오기</a>|
<a style="font-size: 20px; " href="/views/bookmarkShow.jsp">북마크 보기</a>|
<a style="font-size: 20px; " href="/views/bookmarkGroup.jsp">북마크 그룹 관리</a>
</br><br>
<%
    WifiService service = new WifiService();
    List<HistoryDTO> historyList = service.showHistory();

    String id = request.getParameter("id");
    if (id != null) {
        try {
            service.deleteHistory(Integer.parseInt(id));
            response.setStatus(204);
            return;
        } finally {
            return;
        }
    }
%>
<table class="table table-striped table-bordered">
    <thead>
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <% if (historyList.isEmpty()) { %>
    <tr>
        <td colspan="5">히스토리 정보가 존재하지 않습니다.</td>
    </tr>
    <% } else { %>
    <% for (HistoryDTO history : historyList) { %>
    <tr>
        <td><%= history.getID() %></td>
        <td><%= history.getLAT() %></td>
        <td><%= history.getLNT() %></td>
        <td><%= history.getCREATED_TIME() %></td>
        <td><button onclick="deleteHistory('<%= history.getID() %>')">삭제</button></td>
    </tr>
    <% }} %>
    </tbody>
</table>
</body>
</html>
<script>
    function deleteHistory(ID) {
        if (confirm("삭제하시겠습니까?")) {
            $.ajax({
                url: "history.jsp",
                data: {id: ID},
                success: function () {
                    location.reload();
                }
            });
        }
    }
</script>
<style>
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