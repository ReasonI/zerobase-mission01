package api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.WifiDTO;
import service.WifiService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;

public class getWifiInfo {

    public static Integer loadWifi() throws IOException {
        WifiService wifiService = new WifiService();

        int cnt = 0;
        int idx = 1;

        while (true) {

            String urlBuilder = "http://openapi.seoul.go.kr:8088" +
                    "/" + URLEncoder.encode("6469754f4979756e353251454f734b", "UTF-8") +
                    "/" + URLEncoder.encode("json", "UTF-8") +
                    "/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8") +
                    "/" + URLEncoder.encode(String.valueOf(idx), "UTF-8") +
                    "/" + URLEncoder.encode(String.valueOf(idx + 999), "UTF-8");

            URL url = new URL(urlBuilder);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json; charset=UTF-8");

            System.out.println("--------------------------------");
            System.out.println("호출 상태 : " + conn.getResponseCode());
            System.out.println("--------------------------------");


            BufferedReader rd;
            // 서비스코드가 정상이면 200~300사이의 숫자
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                responseBuilder.append(line);
            }

            rd.close();
            conn.disconnect();


            String[] JSON = responseBuilder.toString().split("row");
            if (!(JSON.length == 2)) {
                break;
            }

            String wJson = JSON[1].substring(2, JSON[1].length() - 2);

            JsonArray jsonArray = JsonParser.parseString(wJson).getAsJsonArray();
            for (int i = 0; i < jsonArray.size(); i++) {

                cnt += 1;

                JsonObject object = (JsonObject) jsonArray.get(i);

                WifiDTO wifiDTO = WifiDTO.builder()
                        .X_SWIFI_MGR_NO(object.get("X_SWIFI_MGR_NO").getAsString())
                        .X_SWIFI_WRDOFC(object.get("X_SWIFI_WRDOFC").getAsString())
                        .X_SWIFI_MAIN_NM(object.get("X_SWIFI_MAIN_NM").getAsString())
                        .X_SWIFI_ADRES1(object.get("X_SWIFI_ADRES1").getAsString())
                        .X_SWIFI_ADRES2(object.get("X_SWIFI_ADRES2").getAsString())
                        .X_SWIFI_INSTL_FLOOR(object.get("X_SWIFI_INSTL_FLOOR").getAsString())
                        .X_SWIFI_INSTL_TY(object.get("X_SWIFI_INSTL_TY").getAsString())
                        .X_SWIFI_INSTL_MBY(object.get("X_SWIFI_INSTL_MBY").getAsString())
                        .X_SWIFI_SVC_SE(object.get("X_SWIFI_SVC_SE").getAsString())
                        .X_SWIFI_CMCWR(object.get("X_SWIFI_CMCWR").getAsString())
                        .X_SWIFI_CNSTC_YEAR(object.get("X_SWIFI_CNSTC_YEAR").getAsString())
                        .X_SWIFI_INOUT_DOOR(object.get("X_SWIFI_INOUT_DOOR").getAsString())
                        .X_SWIFI_REMARS3(object.get("X_SWIFI_REMARS3").getAsString())
                        .LAT(object.get("LAT").getAsDouble())
                        .LNT(object.get("LNT").getAsDouble())
                        .WORK_DTTM(Timestamp.valueOf(object.get("WORK_DTTM").getAsString()))
                        .build();
                wifiService.loadWifi(wifiDTO);
            }
            idx += 1000;
        }
        return cnt;
    }
}