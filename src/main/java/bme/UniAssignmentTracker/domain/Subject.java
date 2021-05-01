package bme.UniAssignmentTracker.domain;


import bme.UniAssignmentTracker.domain.requirements.Assignment;
import bme.UniAssignmentTracker.domain.requirements.Exam;
import bme.UniAssignmentTracker.domain.requirements.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "subscribedUsers")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4, max = 100)
    @Column(length = 100)
    private String name;

    @Size(max = 255)
    private String description;
    @Size(max = 511)
    @Column(length = 511)
    private String experiences;
    @Size(max = 63)
    @Column(length = 63)
    private String scoring;

    //this will contain project assignments too
    @OneToMany(mappedBy = "subject", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<Assignment> assignments = new HashSet<>();
    @OneToMany(mappedBy = "subject", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<Project> projects = new HashSet<>();
    @OneToMany(mappedBy = "subject", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<Exam> exams = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "usersSubjects")
    private Set<User> subscribedUsers = new HashSet<>();

    public void subscribeUser(User user) {
        subscribedUsers.add(user);
    }

    public void unsubscribeUser(User user) {
        subscribedUsers.remove(user);
    }


}
