package bme.UniAssignmentTracker.service;


import bme.UniAssignmentTracker.domain.Subject;
import bme.UniAssignmentTracker.domain.User;
import bme.UniAssignmentTracker.repository.SubjectRepository;
import bme.UniAssignmentTracker.repository.UserRepository;
import bme.UniAssignmentTracker.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<Subject> findAll(Pageable pageable) {
        log.debug("Find paged subjects requested");
        return subjectRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Subject> findAll() {
        log.debug("Find all subjects requested");
        return subjectRepository.findAll();
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
        User user = getCurrentUser();
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
        User user = getCurrentUser();

        log.debug("Subscribe to {} was requested for {}", subject.getName(), user.getUsername());

        user.subscribeToSubject(subject);
        subject.subscribeUser(user);
        userRepository.save(user);
    }

    public void unsubscribeFromSubject(Subject subject) {
        User user = getCurrentUser();

        log.debug("Unsubscribe from {} was requested for {}", subject.getName(), user.getUsername());

        user.unsubscribeFromSubject(subject);
        subject.unsubscribeUser(user);
        userRepository.save(user);
    }

    private User getCurrentUser() {
        //TODO: custom exception
        String username = SecurityUtils.getCurrentUserLogin().orElseThrow();
        return userRepository.findByUsername(username).orElseThrow();
    }

}
