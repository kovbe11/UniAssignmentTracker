package bme.UniAssignmentTracker.repository;

import bme.UniAssignmentTracker.domain.requirements.ProjectAssignment;
import org.springframework.data.repository.CrudRepository;

public interface ProjectAssignmentRepository extends CrudRepository<ProjectAssignment, Long> {
}
