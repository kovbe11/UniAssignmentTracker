package bme.UniAssignmentTracker.web.rest.dto;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.sql.Date;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class ExamDTO {

    private Date zhDate;
    @Size(max = 255)
    private String experiences;
    @Size(max = 255)
    private String minimumRequirements;
    @Size(max = 63)
    @Column(length = 63)
    private String scoring;

    Long subjectId;
}
