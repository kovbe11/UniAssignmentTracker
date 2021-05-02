package bme.UniAssignmentTracker.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.sql.Date;


@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class AssignmentDTO {

    private Boolean isOptional;
    private Date deadline;
    @Size(max = 255)
    private String minimalRequirement;
    @Size(max = 255)
    private String description;

}
