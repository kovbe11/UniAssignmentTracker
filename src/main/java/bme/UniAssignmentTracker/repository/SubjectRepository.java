package bme.UniAssignmentTracker.repository;

import bme.UniAssignmentTracker.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
