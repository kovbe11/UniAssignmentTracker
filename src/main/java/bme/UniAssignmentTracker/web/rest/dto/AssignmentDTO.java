package bme.UniAssignmentTracker.web.rest.dto;

import lombok.*;

import javax.validation.constraints.Size;
import java.sql.Date;


@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDTO {

    private Boolean optional;
    private Date deadline;
    @Size(max = 255)
    private String description;
    @Size(max = 31)
    private String label;

}
