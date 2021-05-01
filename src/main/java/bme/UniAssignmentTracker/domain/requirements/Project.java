package bme.UniAssignmentTracker.domain.requirements;

import bme.UniAssignmentTracker.domain.Subject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Getter
@Setter
@EqualsAndHashCode
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

    @OneToMany(mappedBy = "project", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private List<ProjectAssigment> assignments;

    @ManyToOne
    Subject subject;

}
