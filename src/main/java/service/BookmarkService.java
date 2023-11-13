package service;

import dto.BookmarkGroupDTO;
import dto.BookmarkListDTO;
import dto.WifiDTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookmarkService {
    public void saveBookmarkGroup(BookmarkGroupDTO bookmarkGroupDTO) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:/Users/yunal/DataGripProjects/Data-Study/wifi.db";

            connection = DriverManager.getConnection(url);
            String sql = "insert into BookmarkGroup(NAME, ORDERNO, CREATETIME) " +
                    "values (?, ?, ?); ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookmarkGroupDTO.getNAME());
            preparedStatement.setInt(2, bookmarkGroupDTO.getORDERNO());
            preparedStatement.setTimestamp(3, bookmarkGroupDTO.getCREATETIME());

            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public List<BookmarkGroupDTO> showBookmarkGroup() {

        ArrayList<BookmarkGroupDTO> bookmarkGroupDTOS = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:/Users/yunal/DataGripProjects/Data-Study/wifi.db";

            connection = DriverManager.getConnection(url);

            String sql = "select * from BookmarkGroup order by ORDERNO asc";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BookmarkGroupDTO bookmarkGroupDTO = BookmarkGroupDTO.builder()
                        .ID(resultSet.getInt("ID"))
                        .NAME(resultSet.getString("NAME"))
                        .ORDERNO(resultSet.getInt("ORDERNO"))
                        .CREATETIME(resultSet.getTimestamp("CREATETIME"))
                        .RETIME(resultSet.getTimestamp("RETIME"))
                        .build();
                bookmarkGroupDTOS.add(bookmarkGroupDTO);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bookmarkGroupDTOS;
    }

    public List<BookmarkGroupDTO> showBookmarkGroup(int id) {
        ArrayList<BookmarkGroupDTO> bookmarkGroupDTOS = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:/Users/yunal/DataGripProjects/Data-Study/wifi.db";

            connection = DriverManager.getConnection(url);

            String sql = "select * from BookmarkGroup WHERE ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BookmarkGroupDTO bookmarkGroupDTO = BookmarkGroupDTO.builder()
                        .ID(resultSet.getInt("ID"))
                        .NAME(resultSet.getString("NAME"))
                        .ORDERNO(resultSet.getInt("ORDERNO"))
                        .CREATETIME(resultSet.getTimestamp("CREATETIME"))
                        .RETIME(resultSet.getTimestamp("RETIME"))
                        .build();
                bookmarkGroupDTOS.add(bookmarkGroupDTO);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bookmarkGroupDTOS;
    }

    public void updateBookmarkGroup(int id, String name, int orderNo) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:/Users/yunal/DataGripProjects/Data-Study/wifi.db";

            connection = DriverManager.getConnection(url);
            String sql = "UPDATE BookmarkGroup SET NAME = ?, ORDERNO = ?, RETIME = ? WHERE id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, orderNo);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteBookmarkGroup(int id) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:/Users/yunal/DataGripProjects/Data-Study/wifi.db";

            connection = DriverManager.getConnection(url);
            String sql = "SELECT * FROM BookmarkGroup WHERE ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return;
            }
            resultSet.close();

            sql = "DELETE FROM BookmarkList WHERE BOOKMARKGROUPID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            sql = "DELETE FROM BookmarkGroup WHERE ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<BookmarkListDTO> showBookmarkList() {

        ArrayList<BookmarkListDTO> bookmarkListDTOS = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:/Users/yunal/DataGripProjects/Data-Study/wifi.db";

            connection = DriverManager.getConnection(url);

            String sql = "SELECT BookmarkList.*, BookmarkGroup.NAME\n" +
                    "FROM BookmarkList\n" +
                    "JOIN BookmarkGroup ON BookmarkList.BOOKMARKGROUPID = BookmarkGroup.ID";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BookmarkListDTO bookmarkListDTO = BookmarkListDTO.builder()
                        .ID(resultSet.getInt("ID"))
                        .GroupId(resultSet.getInt("BOOKMARKGROUPID"))
                        .NAME(resultSet.getString("NAME"))
                        .WifiName(resultSet.getString("WiFINAME"))
                        .WifiNo(resultSet.getString("WIFINO"))
                        .CREATETIME(resultSet.getTimestamp("CREATETIME"))
                        .build();
                bookmarkListDTOS.add(bookmarkListDTO);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bookmarkListDTOS;
    }


    public void deleteBookmarkList(int id) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:/Users/yunal/DataGripProjects/Data-Study/wifi.db";

            connection = DriverManager.getConnection(url);
            String sql = "DELETE  FROM BookmarkList WHERE ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addBookmark(String groupName, String wifiName, String wifiNo) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:/Users/yunal/DataGripProjects/Data-Study/wifi.db";

            connection = DriverManager.getConnection(url);
            String getIdQuery = "SELECT id FROM bookmarkgroup WHERE name = ?";
            preparedStatement = connection.prepareStatement(getIdQuery);
            preparedStatement.setString(1, groupName);
            resultSet = preparedStatement.executeQuery();
            int bookmarkGroupId = 0;
            if (resultSet.next()) {
                bookmarkGroupId = resultSet.getInt("id");
            }

            String sql = "INSERT INTO bookmarklist (WIFINAME, WIFINO, CREATETIME, GROUPNAME, bookmarkGroupid) " +
                    "VALUES (?, ?, ?, ?, ?)";
//            String sql = "INSERT INTO bookmarklist (groupName, WIFINAME, CREATETIME, bookmarkGroupid, WIFINO) " +
//                    "VALUES (?, ?, NOW(), ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, wifiName);
            preparedStatement.setString(2, wifiNo);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(4, groupName);
            preparedStatement.setInt(5, bookmarkGroupId);

//            preparedStatement.setString(1, groupName);
//            preparedStatement.setString(2, wifiName);
//            preparedStatement.setInt(3, bookmarkGroupId);
//            preparedStatement.setString(4, wifiNo);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public List<WifiDTO> showDetail(String wifi) {
        List<WifiDTO> wifiDTOList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:/Users/yunal/DataGripProjects/Data-Study/wifi.db";

            connection = DriverManager.getConnection(url);
            String sql = "SELECT * FROM WIFI WHERE X_SWIFI_MGR_NO = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, wifi);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                WifiDTO wifiDTO = WifiDTO.builder()
                        .X_SWIFI_MGR_NO(resultSet.getString("X_SWIFI_MGR_NO"))
                        .X_SWIFI_WRDOFC(resultSet.getString("X_SWIFI_WRDOFC"))
                        .X_SWIFI_MAIN_NM(resultSet.getString("X_SWIFI_MAIN_NM"))
                        .X_SWIFI_ADRES1(resultSet.getString("X_SWIFI_ADRES1"))
                        .X_SWIFI_ADRES2(resultSet.getString("X_SWIFI_ADRES2"))
                        .X_SWIFI_INSTL_FLOOR(resultSet.getString("X_SWIFI_INSTL_FLOOR"))
                        .X_SWIFI_INSTL_TY(resultSet.getString("X_SWIFI_INSTL_TY"))
                        .X_SWIFI_INSTL_MBY(resultSet.getString("X_SWIFI_INSTL_MBY"))
                        .X_SWIFI_SVC_SE(resultSet.getString("X_SWIFI_SVC_SE"))
                        .X_SWIFI_CMCWR(resultSet.getString("X_SWIFI_CMCWR"))
                        .X_SWIFI_CNSTC_YEAR(resultSet.getString("X_SWIFI_CNSTC_YEAR"))
                        .X_SWIFI_INOUT_DOOR(resultSet.getString("X_SWIFI_INOUT_DOOR"))
                        .X_SWIFI_REMARS3(resultSet.getString("X_SWIFI_REMARS3"))
                        .LAT(resultSet.getDouble("LAT"))
                        .LNT(resultSet.getDouble("LNT"))
                        .WORK_DTTM(resultSet.getTimestamp("WORK_DTTM"))
                        .build();

                wifiDTOList.add(wifiDTO);
            }
            return wifiDTOList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}