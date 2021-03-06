package dto;

import lombok.Data;

@Data
public class PersonDto {
    private Integer id;
    private String name;
    private String birthday;
    private Long weight;
    private String passportId;
    private Integer locationId;
}
