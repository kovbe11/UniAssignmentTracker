package bme.UniAssignmentTracker.web.rest;


import bme.UniAssignmentTracker.domain.requirements.Assignment;
import bme.UniAssignmentTracker.service.AssignmentService;
import bme.UniAssignmentTracker.web.rest.dto.AssignmentDTO;
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
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/assignments/{id}")
    public ResponseEntity<Assignment> getAssignment(@PathVariable Long id) {
        var optAssignment = assignmentService.findAssignment(id);
        //TODO: can i throw exceptions that are handled with 404 error?
        if (optAssignment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optAssignment.get());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/subjects/{subjectId}/assignments")
    public ResponseEntity<Assignment> create(@PathVariable Long subjectId, @RequestBody @Valid AssignmentDTO assignmentDTO) throws URISyntaxException {
        var savedAssignment = assignmentService.createAssignment(subjectId, assignmentDTO);
        return ResponseEntity.created(new URI("/api/assignments/" + savedAssignment.getId()))
                .body(savedAssignment);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/assignments/{id}")
    public ResponseEntity<Assignment> patch(@PathVariable Long id, @RequestBody @Valid AssignmentDTO assignmentDTO) {
        var savedAssignment = assignmentService.patchAssignment(id, assignmentDTO);
        return ResponseEntity.ok(savedAssignment);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.ok().build();
    }
}
