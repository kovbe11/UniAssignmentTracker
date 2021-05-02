package bme.UniAssignmentTracker.domain.requirements;

import bme.UniAssignmentTracker.domain.Subject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = "subject")
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 127)
    @Column(length = 127)
    private String specification;
    @Size(max = 255)
    private String experiences;
    @Size(max = 127)
    @Column(length = 127)
    private String documentation;

    @Getter(AccessLevel.NONE)
    Subject subject;
    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<ProjectAssignment> assignments;

    @JsonBackReference
    Subject getSubject() {
        return subject;
    }

    public void addAssignment(ProjectAssignment projectAssignment) {
        assignments.add(projectAssignment);
    }
}
