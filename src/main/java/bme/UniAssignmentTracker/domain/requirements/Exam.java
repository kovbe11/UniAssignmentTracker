package bme.UniAssignmentTracker.domain.requirements;

import bme.UniAssignmentTracker.domain.Subject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Date zhDate;
    @Size(max = 255)
    private String experiences;
    @Size(max = 255)
    private String minimumRequirements;
    @Size(max = 63)
    @Column(length = 63)
    private String scoring;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnore
    private Subject subject;
}
