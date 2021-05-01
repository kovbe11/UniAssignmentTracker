package bme.UniAssignmentTracker.web.rest.dto;

import bme.UniAssignmentTracker.domain.Subject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Size;



@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class SubjectDTO {

    @Size(min = 4, max = 100)
    @Column(length = 100)
    private String name;

    @Size(max = 255)
    @Column(length = 255)
    private String description;
    @Size(max = 511)
    @Column(length = 511)
    private String experiences;
    @Size(max = 50)
    @Column(length = 50)
    private String scoring;

    public Subject toSubject(){
        var subject = new Subject();
        subject.setName(name);
        subject.setDescription(description);
        subject.setExperiences(experiences);
        subject.setScoring(scoring);

        return subject;
    }

}
