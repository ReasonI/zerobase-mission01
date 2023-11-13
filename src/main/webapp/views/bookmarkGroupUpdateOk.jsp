<%@ page import="service.BookmarkService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="../css/style.css">
  <script type="text/javascript" src = "../js/wifi.js"></script>
</head>
<body>
<%
  int id = Integer.parseInt(request.getParameter("id"));
  String name = request.getParameter("name");
  int orderNo = Integer.parseInt(request.getParameter("orderNo"));


  BookmarkService bookMarkService = new BookmarkService();
  bookMarkService.updateBookmarkGroup(id, name, orderNo);

  response.sendRedirect("bookmarkGroup.jsp");
%>
</body>
</html>