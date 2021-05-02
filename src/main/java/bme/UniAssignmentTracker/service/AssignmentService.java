package bme.UniAssignmentTracker.service;

import bme.UniAssignmentTracker.domain.requirements.Assignment;
import bme.UniAssignmentTracker.repository.AssignmentRepository;
import bme.UniAssignmentTracker.web.rest.dto.AssignmentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Transactional
public class AssignmentService {

    private final Logger log = LoggerFactory.getLogger(AssignmentService.class);

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private SubjectService subjectService;

    public Assignment findAssignmentOrThrow(Long id) {
        return findAssignment(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public Optional<Assignment> findAssignment(Long id) {
        log.debug("Find assignment by id requested");
        return assignmentRepository.findById(id);
    }

    public Assignment saveAssignment(Assignment assignment) {
        log.debug("Save Assignment requested");
        return assignmentRepository.save(assignment);
    }

    public void deleteAssignment(Long id) {
        log.debug("Delete Assignment by id requested");
        assignmentRepository.deleteById(id);
    }

    public void deleteAssignment(Assignment assignment) {
        log.debug("Delete Assignment by assignment requested");
        assignmentRepository.delete(assignment);
    }


    public Assignment createAssignment(Long subjectId, @NotNull AssignmentDTO assignmentDTO) {
        log.debug("Create assignment requested");
        var subject = subjectService.findSubjectOrThrow(subjectId);
        var assignment = new Assignment();

        assignment.setOptional(assignmentDTO.getIsOptional());
        assignment.setDeadline(assignmentDTO.getDeadline());
        assignment.setDescription(assignmentDTO.getDescription());
        assignment.setMinimalRequirement(assignmentDTO.getMinimalRequirement());
        assignment.setSubject(subject);

        return assignmentRepository.save(assignment);
    }

    public Assignment patchAssignment(Long id, @NotNull AssignmentDTO assignmentDTO) {
        log.debug("Patch assignment requested");
        var assignment = findAssignmentOrThrow(id);

        if (assignmentDTO.getIsOptional() != null) {
            assignment.setOptional(assignmentDTO.getIsOptional());
        }

        if (assignmentDTO.getDeadline() != null) {
            assignment.setDeadline(assignmentDTO.getDeadline());
        }

        if (assignmentDTO.getDescription() != null) {
            assignment.setDescription(assignmentDTO.getDescription());
        }

        if (assignmentDTO.getMinimalRequirement() != null) {
            assignment.setMinimalRequirement(assignmentDTO.getMinimalRequirement());
        }

        return assignmentRepository.save(assignment);
    }
}