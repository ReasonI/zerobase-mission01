<%@ page import="dto.BookmarkGroupDTO" %>
<%@ page import="service.BookmarkService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title>BOOKMARKGROUP</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script type="text/javascript" src = "../js/wifi.js"></script>
</head>
<h1>북마크 그룹</h1>
<a style="font-size: 20px; " href="/index.jsp">홈</a> |
<a style="font-size: 20px; " href="/views/history.jsp">위치 히스토리 목록</a> |
<a style="font-size: 20px; " href="/views/save.jsp">Open API 와이파이 정보 가져오기</a>|
<a style="font-size: 20px; " href="/views/bookmarkShow.jsp">북마크 보기</a>|
<a style="font-size: 20px; " href="/views/bookmarkGroup.jsp">북마크 그룹 관리</a>
</br><br>
<body>
<%
    BookmarkService bookMarkService = new BookmarkService();
    List<BookmarkGroupDTO> bookmarkGroupDTOList = bookMarkService.showBookmarkGroup();

%>
<button onclick="location.href='bookmarkGroupAdd.jsp'">북마크 그룹 이름 추가</button>
<table class="table table-striped table-bordered" >
    <thead>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>순서</th>
        <th>등록일자</th>
        <th>수정일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <%
        if (bookmarkGroupDTOList.isEmpty()) {
    %>
    <tbody>
    <tr>
        <td colspan="6">정보가 존재하지 않습니다.</td>
    </tr>
    </tbody>
    <%
    } else {
        for (BookmarkGroupDTO bookmarkGroupDTO : bookmarkGroupDTOList) {
    %>
    <tbody>
    <tr>
        <td><%=bookmarkGroupDTO.getID()%></td>
        <td><%=bookmarkGroupDTO.getNAME()%></td>
        <td><%=bookmarkGroupDTO.getORDERNO()%></td>
        <td><%=bookmarkGroupDTO.getCREATETIME()%></td>
        <td><%= bookmarkGroupDTO.getRETIME() != null ? bookmarkGroupDTO.getRETIME() : "" %></td>
        <td>
            <a href="bookmarkGroupUpdate.jsp?id=<%=bookmarkGroupDTO.getID()%>">
                수정
            </a>
            <a href="#" onclick="confirmDelete(<%=bookmarkGroupDTO.getID()%>);">
                삭제
            </a>
        </td>
    </tr>
    </tbody>
    <% }}%>
</table>
</body>
</html>

<script>
    function confirmDelete(id) {
        if (confirm("삭제하시겠습니까?")) {
            window.location.href = "bookmarkGroupDelete.jsp?id=" + id;
        } else {
            return false;
        }
    }

</script>
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