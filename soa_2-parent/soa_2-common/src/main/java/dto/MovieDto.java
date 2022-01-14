package dto;

import lombok.Data;

@Data
public class MovieDto {
    private Integer id;

    private String name;
    private Long oscarsCount;

    private String genre;
    private String rating;

    private Integer coordinatesId;
    private Integer directorId;

}
