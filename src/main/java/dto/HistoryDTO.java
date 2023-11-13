package dto;

import lombok.*;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode()
public class HistoryDTO {
    Integer ID;
    Double LAT;
    Double LNT;
    Timestamp CREATED_TIME;
}