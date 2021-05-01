package bme.UniAssignmentTracker.repository;

import bme.UniAssignmentTracker.domain.requirements.Assignment;
import org.springframework.data.repository.CrudRepository;

public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
}
