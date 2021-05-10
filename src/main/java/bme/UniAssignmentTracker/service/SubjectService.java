package bme.UniAssignmentTracker.service;


import bme.UniAssignmentTracker.domain.Subject;
import bme.UniAssignmentTracker.repository.SubjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubjectService {

    private final Logger log = LoggerFactory.getLogger(SubjectService.class);

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public List<Subject> findAll() {
        log.debug("Find all subjects requested");
        var user = userService.getCurrentUserOrNull();
        var subjects = subjectRepository.findAll();
        if(user != null){
            subjects.forEach(subject -> subject.setSubscribed(user.getUsersSubjects().contains(subject)));
        }
        return subjects;
    }

    @Transactional(readOnly = true)
    public Subject findSubjectOrThrow(Long id) {
        //TODO: custom exception
        return findSubject(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public Optional<Subject> findSubject(Long id) {
        log.debug("Find subject by id requested");
        return subjectRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Subject> findUsersSubjects() {
        var user = userService.getCurrentUser();
        log.debug("Find user's subjects requested for {}", user.getUsername());
        return List.copyOf(user.getUsersSubjects());
    }

    public void delete(Long id) {
        log.debug("Delete subject by id requested");
        subjectRepository.deleteById(id);
    }

    public void delete(Subject subject) {
        log.debug("Delete subject by subject requested");
        subjectRepository.delete(subject);
    }

    public Subject save(Subject subject) {
        log.debug("Save subject requested");
        return subjectRepository.save(subject);
    }

    public void subscribeToSubject(Subject subject) {
        var user = userService.getCurrentUser();

        log.debug("Subscribe to {} was requested for {}", subject.getName(), user.getUsername());

        user.subscribeToSubject(subject);
        subject.subscribeUser(user);
        userService.saveUser(user);
    }

    public void unsubscribeFromSubject(Subject subject) {
        var user = userService.getCurrentUser();

        log.debug("Unsubscribe from {} was requested for {}", subject.getName(), user.getUsername());

        user.unsubscribeFromSubject(subject);
        subject.unsubscribeUser(user);
        userService.saveUser(user);
    }

}
