package lt.vu.restapi.contracts;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TreeDto {
    private String name;
    private String description;
    private List<ForestDto> forests;
}
