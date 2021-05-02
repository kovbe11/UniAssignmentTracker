package bme.UniAssignmentTracker.domain.requirements;

import bme.UniAssignmentTracker.domain.Subject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "subject")
@Inheritance(strategy = InheritanceType.JOINED)
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isOptional;
    private Date deadline;
    @Size(max = 255)
    private String minimalRequirement;
    @Size(max = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(AccessLevel.NONE)
    Subject subject;

    @JsonBackReference
    Subject getSubject() {
        return subject;
    }
}
