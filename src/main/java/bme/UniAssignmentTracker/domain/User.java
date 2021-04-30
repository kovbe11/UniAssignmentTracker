package bme.UniAssignmentTracker.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=4, max = 20)
    @Column(unique = true, length = 20)
    private String username;

    @Size(min = 4, max = 120)
    @Column(length = 120)
    private String password;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "subject_subscription",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id", referencedColumnName = "id")},
            uniqueConstraints= @UniqueConstraint(columnNames={"subject_id", "user_id"}))
    private List<Subject> usersSubjects = new ArrayList<>();

    public void subscribeToSubject(Subject subject){
        usersSubjects.add(subject);
    }

    public void unsubscribeFromSubject(Subject subject){
        usersSubjects.remove(subject);
    }

}
