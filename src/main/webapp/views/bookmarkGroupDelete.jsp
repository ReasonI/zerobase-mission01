<%@ page import="service.BookmarkService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
<body>
<%
    String id = request.getParameter("id");
    BookmarkService bookMarkService = new BookmarkService();
    bookMarkService.deleteBookmarkGroup(Integer.parseInt(id));
    response.sendRedirect("bookmarkGroup.jsp");
%>
</body>
</html>
