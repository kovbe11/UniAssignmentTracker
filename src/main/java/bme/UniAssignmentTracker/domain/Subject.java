package bme.UniAssignmentTracker.domain;


import bme.UniAssignmentTracker.domain.requirements.Assignment;
import bme.UniAssignmentTracker.domain.requirements.Exam;
import bme.UniAssignmentTracker.domain.requirements.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    @Transient
    private boolean isSubscribed = false;

    //this will contain project assignments too
    @OneToMany(mappedBy = "subject", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @Getter(AccessLevel.NONE)
    private Set<Assignment> assignments = new HashSet<>();

    @OneToMany(mappedBy = "subject", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @Getter(AccessLevel.NONE)
    private Set<Project> projects = new HashSet<>();

    @OneToMany(mappedBy = "subject", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @Getter(AccessLevel.NONE)
    private Set<Exam> exams = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "usersSubjects")
    private Set<User> subscribedUsers = new HashSet<>();

    @JsonManagedReference
    public Set<Assignment> getAssignments() {
        return assignments;
    }

    @JsonManagedReference
    public Set<Project> getProjects() {
        return projects;
    }

    @JsonManagedReference
    public Set<Exam> getExams() {
        return exams;
    }

    public void subscribeUser(User user) {
        subscribedUsers.add(user);
    }

    public void unsubscribeUser(User user) {
        subscribedUsers.remove(user);
    }

}
