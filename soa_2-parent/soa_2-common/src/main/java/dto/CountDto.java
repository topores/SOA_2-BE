package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountDto {
    private long count;
}
