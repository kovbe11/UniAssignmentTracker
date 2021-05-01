package bme.UniAssignmentTracker.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class ProjectDTO {

    @Size(max = 127)
    @Column(length = 127)
    private String specification;
    @Size(max = 255)
    private String experiences;
    @Size(max = 127)
    @Column(length = 127)
    private String documentation;

}
