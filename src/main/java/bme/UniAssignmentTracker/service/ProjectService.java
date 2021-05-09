package bme.UniAssignmentTracker.service;

import bme.UniAssignmentTracker.domain.requirements.Project;
import bme.UniAssignmentTracker.domain.requirements.ProjectAssignment;
import bme.UniAssignmentTracker.repository.ProjectAssignmentRepository;
import bme.UniAssignmentTracker.repository.ProjectRepository;
import bme.UniAssignmentTracker.web.rest.dto.AssignmentDTO;
import bme.UniAssignmentTracker.web.rest.dto.ProjectDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {

    private final Logger log = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectAssignmentRepository projectAssignmentRepository;

    @Autowired
    private SubjectService subjectService;

    public Project findProjectOrThrow(Long id) {
        return findProject(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public Optional<Project> findProject(Long id) {
        log.debug("Find project by id requested");
        return projectRepository.findById(id);
    }

    public Project createProject(Long subjectId, ProjectDTO projectDTO) {
        log.debug("Create project requested");
        var subject = subjectService.findSubjectOrThrow(subjectId);
        var project = new Project();

        project.setSpecification(projectDTO.getSpecification());
        project.setDocumentation(projectDTO.getDocumentation());
        project.setExperiences(projectDTO.getExperiences());
        project.setSubject(subject);

        return projectRepository.save(project);
    }

    public Project patchProject(Long id, @NotNull ProjectDTO projectDTO) {
        log.debug("Patch project requested");
        var project = findProjectOrThrow(id);

        if (projectDTO.getDocumentation() != null) {
            project.setDocumentation(projectDTO.getDocumentation());
        }
        if (projectDTO.getExperiences() != null) {
            project.setDocumentation(projectDTO.getDocumentation());
        }
        if (projectDTO.getSpecification() != null) {
            project.setSpecification(projectDTO.getSpecification());
        }

        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        log.debug("Delete Project by id requested");
        projectRepository.deleteById(id);
    }

    public void deleteProject(Project project) {
        log.debug("Delete Project by project requested");
        projectRepository.delete(project);
    }

    public ProjectAssignment createProjectAssignment(Long subjectId,
                                                     Long projectId,
                                                     AssignmentDTO assignmentDTO){
        log.debug("Create project assignment requested");
        var project = findProjectOrThrow(projectId);
        var subject = subjectService.findSubjectOrThrow(subjectId);

        var projectAssignment = new ProjectAssignment();
        projectAssignment.setProject(project);
        projectAssignment.setSubject(subject);

        projectAssignment.setDeadline(assignmentDTO.getDeadline());
        projectAssignment.setDescription(assignmentDTO.getDescription());
        projectAssignment.setMinimalRequirements(assignmentDTO.getMinimalRequirements());
        projectAssignment.setOptional(assignmentDTO.getIsOptional());

        return projectAssignmentRepository.save(projectAssignment);
    }



}