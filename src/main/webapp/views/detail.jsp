<%@ page import="dto.WifiDTO" %>
<%@ page import="service.WifiService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="dto.BookmarkGroupDTO" %>
<%@ page import="service.BookmarkService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
<head>
  <title>DETAIL</title>
  <link rel="stylesheet" type="text/css" href="../css/style.css">
  <script type="text/javascript" src = "../js/wifi.js"></script>
</head>
<h1>와이파이 정보 구하기</h1>
<body>
<a style="font-size: 20px; " href="/index.jsp">홈</a> |
<a style="font-size: 20px; " href="/views/history.jsp">위치 히스토리 목록</a> |
<a style="font-size: 20px; " href="/views/save.jsp">Open API 와이파이 정보 가져오기</a>|
<a style="font-size: 20px; " href="/views/bookmarkShow.jsp">북마크 보기</a>|
<a style="font-size: 20px; " href="/views/bookmarkGroup.jsp">북마크 그룹 관리</a>
</br><br>
<%
  request.setCharacterEncoding("utf-8");
  BookmarkService bookmarkService = new BookmarkService();
  List<BookmarkGroupDTO> bookmarkLists = bookmarkService.showBookmarkGroup();
  request.setAttribute("bookmarkList", bookmarkLists);
%>
<%
  WifiService wifiService = new WifiService();
  String wifiNO = request.getParameter("WifiNo");
  String distance = request.getParameter("distance");
  String wifiname = request.getParameter("wifiname");
  List<WifiDTO> wifiDTOList;
  try {
    wifiDTOList = wifiService.showDetail(wifiNO, Double.parseDouble(distance));
  } catch (SQLException e) {
    throw new RuntimeException(e);
  }
%>
<select name="bookmarkList" id="bookmarkList">
  <option value="">북마크 그룹 이름 선택</option>
  <% for(BookmarkGroupDTO bookmark : bookmarkLists) { %>
  <option name="bookmarkList" id="bookmarkList" value="<%= bookmark.getNAME() %>"><%= bookmark.getNAME() %></option>
  <% } %>
</select>
<button onclick="addBookmark()">북마크 추가하기</button>
<script>
  function addBookmark() {
    var selectBox = document.getElementById("bookmarkList");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    var wifiNo = "<%= wifiNO %>";
    var wifiname = "<%= wifiname %>"
    if (selectedValue) {
      location.href = "bookmarkAddtoList.jsp?wifiNo=" + wifiNo + "&groupName=" + selectedValue + "&wifiname=" + wifiname;
    } else {
      alert("북마크 그룹을 선택하세요.");
    }
  }
</script>
<table class="table table-striped table-bordered" >
  <% for (WifiDTO wifiDTO : wifiDTOList) { %>
  <tr>
    <th>거리(km)</th>
    <td><%=wifiDTO.getDistance()%> KM</td>
  </tr>
  <tr>
    <th>관리번호</th>
    <td><%= wifiDTO.getX_SWIFI_MGR_NO()%></td>
  </tr>
  <tr>
    <th>자치구</th>
    <td><%=wifiDTO.getX_SWIFI_WRDOFC()%> </td>
  </tr>
  <tr>
    <th>와이파이명</th>
    <td><%= wifiDTO.getX_SWIFI_MAIN_NM() %></td>
  </tr>
  <tr>
    <th>도로명주소</th>
    <td><%= wifiDTO.getX_SWIFI_ADRES1() %></td>
  </tr>
  <tr>
    <th>상세주소</th>
    <td><%= wifiDTO.getX_SWIFI_ADRES2() %></td>
  </tr>
  <tr>
    <th>설치위치(층)</th>
    <td><%= wifiDTO.getX_SWIFI_INSTL_FLOOR() %></td>
  </tr>
  <tr>
    <th>설치유형</th>
    <td><%= wifiDTO.getX_SWIFI_INSTL_TY() %></td>
  </tr>
  <tr>
    <th>설치기관</th>
    <td><%= wifiDTO.getX_SWIFI_INSTL_MBY() %></td>
  </tr>
  <tr>
    <th>서비스구분</th>
    <td><%= wifiDTO.getX_SWIFI_SVC_SE() %></td>
  </tr>
  <tr>
    <th>망종류</th>
    <td><%= wifiDTO.getX_SWIFI_CMCWR() %> </td>
  </tr>
  <tr>
    <th>설치년도</th>
    <td><%= wifiDTO.getX_SWIFI_CNSTC_YEAR() %></td>
  </tr>
  <tr>
    <th>실내외구분</th>
    <td><%= wifiDTO.getX_SWIFI_INOUT_DOOR() %></td>
  </tr>
  <tr>
    <th>WIFI접속환경</th>
    <td><%= wifiDTO.getX_SWIFI_REMARS3() %></td>
  </tr>
  <tr>
    <th>X좌표</th>
    <td><%= wifiDTO.getLAT() %></td>
  </tr>
  <tr>
    <th>Y좌표</th>
    <td><%= wifiDTO.getLNT() %></td>
  </tr>
  <tr>
    <th>작업일자</th>
    <td><%= wifiDTO.getWORK_DTTM() %></td>
  </tr>
  <% }%>
</table>
</body>
</html>
<style>
  th{
    background: #04AA6D;
    text-align: center;
    align-content: center;
    color : white;
    font-size: 20px;
  }
  td{
    font-size: 20px;
    text-align: left;
  }
</style>