<%@ page import="service.BookmarkService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title>LIST</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script type="text/javascript" src = "../js/wifi.js"></script>
</head>
<h1>북마크 그룹 추가</h1>
<body>
<%
    String wifiNo = request.getParameter("wifiNo");
    String groupName = request.getParameter("groupName");
    String wifiname = request.getParameter("wifiname");
    BookmarkService bookmarkService = new BookmarkService();
    bookmarkService.addBookmark(groupName, wifiname, wifiNo);
    response.sendRedirect("bookmarkShow.jsp");
%>
</body>
</html>