package bme.UniAssignmentTracker.repository;

import bme.UniAssignmentTracker.domain.requirements.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ExamRepository extends CrudRepository<Exam, Long> {
}
