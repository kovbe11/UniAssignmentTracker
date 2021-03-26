package bme.UniAssignmentTracker.domain;


import bme.UniAssignmentTracker.domain.requirements.Assignment;
import bme.UniAssignmentTracker.domain.requirements.Exam;
import bme.UniAssignmentTracker.domain.requirements.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String description;
    private String experiences;
    private String scoring;

    //this will contain project assignments too
    @OneToMany(mappedBy = "subject", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Assignment> assignments;
    @OneToMany(mappedBy = "subject", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Project> projects;
    @OneToMany(mappedBy = "subject", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Exam> exams;
}
