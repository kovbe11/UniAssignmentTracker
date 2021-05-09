package bme.UniAssignmentTracker.web.rest;

import bme.UniAssignmentTracker.domain.requirements.Project;
import bme.UniAssignmentTracker.domain.requirements.ProjectAssignment;
import bme.UniAssignmentTracker.service.ProjectService;
import bme.UniAssignmentTracker.web.rest.dto.AssignmentDTO;
import bme.UniAssignmentTracker.web.rest.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("projects/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id) {
        var optProject = projectService.findProject(id);
        //TODO: can i throw exceptions that are handled with 404 error?
        if (optProject.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optProject.get());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/subjects/{subjectId}/projects")
    public ResponseEntity<Project> createProject(@PathVariable Long subjectId,
                                                 @RequestBody @Valid ProjectDTO projectDTO) throws URISyntaxException {
        var project = projectService.createProject(subjectId, projectDTO);
        return ResponseEntity.created(new URI("/projects/" + project.getId()))
                .body(project);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("projects/{id}")
    public ResponseEntity<Project> patchProject(@PathVariable Long id,
                                                @RequestBody @Valid ProjectDTO projectDTO) {
        var project = projectService.patchProject(id, projectDTO);
        return ResponseEntity.ok(project);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("projects/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/subjects/{subjectId}/projects/{projectId}/assignments")
    public ResponseEntity<ProjectAssignment> createProjectAssignment(@PathVariable Long subjectId,
                                                                     @PathVariable Long projectId,
                                                                     @RequestBody @Valid AssignmentDTO assignmentDTO) throws URISyntaxException {
        var projectAssignment = projectService.createProjectAssignment(subjectId, projectId, assignmentDTO);

        return ResponseEntity.created(new URI("/api/assignments/" + projectAssignment.getId()))
                .body(projectAssignment);
    }

}
