package dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode()
public class BookmarkGroupDTO {
    Integer ID;
    String NAME;
    Integer ORDERNO;
    Timestamp CREATETIME;
    Timestamp RETIME;
}