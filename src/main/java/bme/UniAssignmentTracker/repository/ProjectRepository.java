package bme.UniAssignmentTracker.repository;

import bme.UniAssignmentTracker.domain.requirements.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
}
