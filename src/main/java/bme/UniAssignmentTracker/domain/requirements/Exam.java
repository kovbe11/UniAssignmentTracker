package bme.UniAssignmentTracker.domain.requirements;

import bme.UniAssignmentTracker.domain.Subject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dueDate;
    @Size(max = 255)
    private String experiences;
    @Size(max = 63)
    @Column(length = 63)
    private String scoring;
    private boolean isResit;

    @Size(max = 31)
    private String label;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(AccessLevel.NONE)
    private Subject subject;

    @JsonBackReference
    Subject getSubject() {
        return subject;
    }
}
