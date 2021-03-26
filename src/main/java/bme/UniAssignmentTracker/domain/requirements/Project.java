package bme.UniAssignmentTracker.domain.requirements;

import bme.UniAssignmentTracker.domain.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specification;
    private String experiences;
    private String documentation;

    @OneToMany(mappedBy = "project", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private List<ProjectAssigment> assignments;

    @ManyToOne
    Subject subject;

}
