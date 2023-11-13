<%@ page import="dto.BookmarkGroupDTO" %>
<%@ page import="service.BookmarkService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title>BOOKMARKGROUPUPDATE</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script type="text/javascript" src = "../js/wifi.js"></script>
</head>
<h1>북마크 그룹 수정</h1>
<body>

<a style="font-size: 20px; " href="/index.jsp">홈</a> |
<a style="font-size: 20px; " href="/views/history.jsp">위치 히스토리 목록</a> |
<a style="font-size: 20px; " href="/views/save.jsp">Open API 와이파이 정보 가져오기</a>|
<a style="font-size: 20px; " href="/views/bookmarkShow.jsp">북마크 보기</a>|
<a style="font-size: 20px; " href="/views/bookmarkGroup.jsp">북마크 그룹 관리</a>
</br><br>
<%
    String id = request.getParameter("id");
    BookmarkService bookMarkService = new BookmarkService();
    List<BookmarkGroupDTO> bookmarkGroupDTOList = bookMarkService.showBookmarkGroup(Integer.parseInt(id));
%>
<form id="bookmark-form" action="bookmarkGroupUpdateOk.jsp">
    <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
    <table class="table table-striped table-bordered">
        <% for(BookmarkGroupDTO bookmarkGroupDTO : bookmarkGroupDTOList){ %>
        <tr>
            <th>북마크 이름</th>
            <td><input type="text" name="name" value="<%=bookmarkGroupDTO.getNAME()%>"></td>
        </tr>
        <tr>
            <th>순서</th>
            <td><input type="text" name="orderNo" value="<%=bookmarkGroupDTO.getORDERNO()%>"></td>
        </tr>
        <tr>
            <td colspan="2">
                <a href="bookmarkGroup.jsp">돌아가기</a>
                <button type="submit">수정</button>
            </td>
        </tr>
        <% }%>
    </table>
</form>
</body>
</html>
<style>
    th{
        background: #04AA6D;
        text-align: center;
        align-content: center;
        font-size: 20px;
        color: white;
    }
    td{
        font-size: 20px;
        text-align: center;
    }
</style>