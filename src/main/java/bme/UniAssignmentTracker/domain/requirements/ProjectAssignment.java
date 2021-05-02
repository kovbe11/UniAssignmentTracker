package bme.UniAssignmentTracker.domain.requirements;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "assignment_id")
public class ProjectAssignment extends Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(AccessLevel.NONE)
    private Project project;

    @JsonBackReference
    Project getProject() {
        return project;
    }
}
