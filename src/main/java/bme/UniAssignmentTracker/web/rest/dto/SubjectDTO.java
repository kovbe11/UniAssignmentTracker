package bme.UniAssignmentTracker.web.rest.dto;

import bme.UniAssignmentTracker.domain.Subject;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class SubjectDTO {

    @Size(min = 4, max = 100)
    @Column(length = 100)
    private String name;

    @Size(max = 511)
    @Column(length = 511)
    private String description;

    @Size(max = 63)
    @Column(length = 63)
    private String scoring;


    @Min(1)
    @Max(30)
    private Short officialCredit;

    @Min(1)
    @Max(30)
    private Short experiencedCredit;


    public Subject toSubject(){
        var subject = new Subject();

        subject.setName(name);
        subject.setDescription(description);
        subject.setScoring(scoring);
        subject.setOfficialCredit(officialCredit);
        subject.setExperiencedCredit(experiencedCredit);

        return subject;
    }

}
