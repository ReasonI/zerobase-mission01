package service;

import dto.HistoryDTO;
import dto.WifiDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiService {
    public void loadWifi(WifiDTO wifiDTO) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:/Users/yunal/DataGripProjects/Data-Study/wifi.db";

            connection = DriverManager.getConnection(url);
            String sql = "INSERT INTO wifi(X_SWIFI_MGR_NO, " +
                    "X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, " +
                    "X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, " +
                    "X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, " +
                    "X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, " +
                    "lat, lnt, WORK_DTTM)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, wifiDTO.getX_SWIFI_MGR_NO());
            preparedStatement.setString(2, wifiDTO.getX_SWIFI_WRDOFC());
            preparedStatement.setString(3, wifiDTO.getX_SWIFI_MAIN_NM());
            preparedStatement.setString(4, wifiDTO.getX_SWIFI_ADRES1());
            preparedStatement.setString(5, wifiDTO.getX_SWIFI_ADRES2());
            preparedStatement.setString(6, wifiDTO.getX_SWIFI_INSTL_FLOOR());
            preparedStatement.setString(7, wifiDTO.getX_SWIFI_INSTL_TY());
            preparedStatement.setString(8, wifiDTO.getX_SWIFI_INSTL_MBY());
            preparedStatement.setString(9, wifiDTO.getX_SWIFI_SVC_SE());
            preparedStatement.setString(10, wifiDTO.getX_SWIFI_CMCWR());
            preparedStatement.setString(11, wifiDTO.getX_SWIFI_CNSTC_YEAR());
            preparedStatement.setString(12, wifiDTO.getX_SWIFI_INOUT_DOOR());
            preparedStatement.setString(13, wifiDTO.getX_SWIFI_REMARS3());
            preparedStatement.setDouble(14, wifiDTO.getLAT());
            preparedStatement.setDouble(15, wifiDTO.getLNT());
            preparedStatement.setTimestamp(16, wifiDTO.getWORK_DTTM());

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

    public List<WifiDTO> showWifi(Double Lat, Double Lnt) {
        List<WifiDTO> wifiList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:/Users/yunal/DataGripProjects/Data-Study/wifi.db";

            connection = DriverManager.getConnection(url);
            String sql = "SELECT *, 6371 * 2 * ASIN(" +
                    "SQRT(" +
                    "POWER(SIN(((LAT - ?) * PI() / 180) / 2), 2) " +
                    "+ " +
                    "COS(? * PI() / 180) " +
                    "* COS((LAT * PI() / 180)) * " +
                    "POWER(SIN(((LNT - ?) * PI() / 180) / 2), 2))) " +
                    "AS distance " +
                    "FROM WIFI " +
                    "ORDER BY distance LIMIT 0, 20";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, Lat);
            preparedStatement.setDouble(2, Lat);
            preparedStatement.setDouble(3, Lnt);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Double distance = resultSet.getDouble("distance");
                distance = Math.round(distance * 1000.0) / 1000.0;
                WifiDTO wifiDTO = WifiDTO.builder()
                        .ID(resultSet.getInt("WIFI_ID"))
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
                        .distance(distance)
                        .WORK_DTTM(resultSet.getTimestamp("WORK_DTTM"))
                        .build();
                wifiList.add(wifiDTO);
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
        return wifiList;
    }

    public List<WifiDTO> showDetail(String wifiNO, Double distance) throws SQLException {
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
            preparedStatement.setString(1, wifiNO);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                WifiDTO wifiDTO = WifiDTO.builder()
                        .distance(distance)
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

    public void saveHistory(HistoryDTO hitoryDTO) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:/Users/yunal/DataGripProjects/Data-Study/wifi.db";

            connection = DriverManager.getConnection(url);
            String sql = "insert into HISTORY(LAT, LNT, CREATED_TIME) " +
                    "values (?, ?, ?); ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, hitoryDTO.getLAT());
            preparedStatement.setDouble(2, hitoryDTO.getLNT());
            preparedStatement.setTimestamp(3, hitoryDTO.getCREATED_TIME());

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

    public List<HistoryDTO> showHistory() {
        List<HistoryDTO> hitoryDTOList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:/Users/yunal/DataGripProjects/Data-Study/wifi.db";

            connection = DriverManager.getConnection(url);
            String sql = "SELECT * FROM HISTORY ORDER BY ID DESC";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                HistoryDTO hitoryDTO = HistoryDTO.builder()
                        .ID(resultSet.getInt("ID"))
                        .LAT(resultSet.getDouble("LAT"))
                        .LNT(resultSet.getDouble("LNT"))
                        .CREATED_TIME(resultSet.getTimestamp("CREATED_TIME"))
                        .build();
                hitoryDTOList.add(hitoryDTO);
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
        return hitoryDTOList;
    }

    public void deleteHistory(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:/Users/yunal/DataGripProjects/Data-Study/wifi.db";

            connection = DriverManager.getConnection(url);

            String sql = "DELETE FROM HISTORY WHERE ID = ?";
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
}