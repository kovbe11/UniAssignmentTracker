package bme.UniAssignmentTracker.service;

import bme.UniAssignmentTracker.domain.requirements.Assignment;
import bme.UniAssignmentTracker.repository.AssignmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AssignmentService {

    private final Logger log = LoggerFactory.getLogger(AssignmentService.class);

    @Autowired
    private AssignmentRepository assignmentRepository;

    public Assignment findAssignmentOrThrow(Long id){
        return findAssignment(id).orElseThrow();
    }
    
    @Transactional(readOnly = true)
    public Optional<Assignment> findAssignment(Long id){
        log.debug("Find assignment by id requested");
        return assignmentRepository.findById(id);
    }

    public Assignment saveAssignment(Assignment assignment){
        log.debug("Save Assignment requested");
        return assignmentRepository.save(assignment);
    }

    public void deleteAssignment(Long id){
        log.debug("Delete Assignment by id requested");
        assignmentRepository.deleteById(id);
    }

    public void deleteAssignment(Assignment assignment){
        log.debug("Delete Assignment by assignment requested");
        assignmentRepository.delete(assignment);
    }


}