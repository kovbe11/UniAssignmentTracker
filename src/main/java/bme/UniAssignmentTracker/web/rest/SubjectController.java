package bme.UniAssignmentTracker.web.rest;


import bme.UniAssignmentTracker.domain.Subject;
import bme.UniAssignmentTracker.service.SubjectService;
import bme.UniAssignmentTracker.web.rest.dto.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<Subject>> getAll() {
        return ResponseEntity.ok()
                .body(subjectService.findAll());
    }

    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    @GetMapping("/subscribed")
    public ResponseEntity<List<Subject>> getSubscribedSubjects() {
        var subscribedSubjects = subjectService.findUsersSubjects();
        return ResponseEntity.ok()
                .body(subscribedSubjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable Long id) {
        var optSubject = subjectService.findSubject(id);
        //TODO: can i throw exceptions that are handled with 404 error?
        if (optSubject.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optSubject.get());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Subject> create(@RequestBody @Valid SubjectDTO subject) throws URISyntaxException {
        var savedSubject = subjectService.save(subject.toSubject());
        return ResponseEntity.created(new URI("/api/subjects/" + savedSubject.getId()))
                .body(savedSubject);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        subjectService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<Subject> update(@PathVariable Long id, @RequestBody @Valid SubjectDTO subjectDTO) throws URISyntaxException {
        var subject = subjectService.patchSubject(id, subjectDTO);
        return ResponseEntity.ok(subject);
    }


    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    @PostMapping("/subscribe/{id}")
    public ResponseEntity<Void> subscribe(@PathVariable Long id) {
        Optional<Subject> subject = subjectService.findSubject(id);
        if (subject.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        subjectService.subscribeToSubject(subject.get());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    @PostMapping("/unsubscribe/{id}")
    public ResponseEntity<Void> unsubscribe(@PathVariable Long id) {
        Optional<Subject> subject = subjectService.findSubject(id);
        if (subject.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        subjectService.unsubscribeFromSubject(subject.get());
        return ResponseEntity.noContent().build();
    }

}
