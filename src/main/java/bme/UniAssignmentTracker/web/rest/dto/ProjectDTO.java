package bme.UniAssignmentTracker.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class ProjectDTO {

    @Size(max = 255)
    private String experiences;

    private List<AssignmentDTO> projectAssignments;

}
