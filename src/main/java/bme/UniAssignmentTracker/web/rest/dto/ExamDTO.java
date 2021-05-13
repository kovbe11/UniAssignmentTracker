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

    private Date dueDate;
    @Size(max = 255)
    private String experiences;
    @Size(max = 255)
    @Column(length = 63)
    private String scoring;
    private boolean isResit;
    @Size(max = 31)
    private String label;

}
