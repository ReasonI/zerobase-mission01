package dto;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode()
public class WifiDTO {
    Integer ID;
    String X_SWIFI_MGR_NO;
    String X_SWIFI_WRDOFC;
    String X_SWIFI_MAIN_NM;
    String X_SWIFI_ADRES1;
    String X_SWIFI_ADRES2;
    String X_SWIFI_INSTL_FLOOR;
    String X_SWIFI_INSTL_TY;
    String X_SWIFI_INSTL_MBY;
    String X_SWIFI_SVC_SE;
    String X_SWIFI_CMCWR;
    String X_SWIFI_CNSTC_YEAR;
    String X_SWIFI_INOUT_DOOR;
    String X_SWIFI_REMARS3;
    Double LAT;
    Double LNT;
    Double distance;
    Timestamp WORK_DTTM;

}