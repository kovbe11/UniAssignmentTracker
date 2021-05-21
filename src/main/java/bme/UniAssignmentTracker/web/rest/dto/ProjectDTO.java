package bme.UniAssignmentTracker.web.rest.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    @Size(max = 255)
    private String experiences;

}
