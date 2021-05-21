package bme.UniAssignmentTracker.web.rest.dto;


import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.sql.Date;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {

    private Date dueDate;
    @Size(max = 255)
    private String experiences;
    @Size(max = 255)
    @Column(length = 63)
    private String scoring;
    private Boolean resit;
    @Size(max = 31)
    private String label;

}
