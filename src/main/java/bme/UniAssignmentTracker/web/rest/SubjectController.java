package bme.UniAssignmentTracker.web.rest;


import bme.UniAssignmentTracker.domain.Subject;
import bme.UniAssignmentTracker.service.SubjectService;
import bme.UniAssignmentTracker.web.rest.dto.SubjectDTO;
import bme.UniAssignmentTracker.web.rest.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<Subject>> getAll(Pageable pageable) {
        final Page<Subject> foundPage = subjectService.findAll(pageable);
        return ResponseEntity.ok()
                .headers(PaginationUtil.generatePaginationHttpHeaders(foundPage, "api/subjects"))
                .body(foundPage.getContent());
    }

    @PreAuthorize("hasRole('USER')")
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
        Subject savedSubject = subjectService.save(subject.toSubject());
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
    @PutMapping("/{id}")
    public ResponseEntity<Subject> update(@PathVariable Long id, @RequestBody @Valid Subject subject) throws URISyntaxException {
        boolean isCreate = subject.getId() == null || subject.getId() == 0;
        //id != subject.id -> bad request
        //if id is null, then it's just an upsert no problem
        if (!id.equals(subject.getId()) && !isCreate) {
            return ResponseEntity.badRequest().build();
        }
        Subject savedSubject = subjectService.save(subject);

        if (isCreate) {
            return ResponseEntity.created(new URI("/api/subjects/" + id)).body(savedSubject);
        }
        return ResponseEntity.ok(savedSubject);
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/subscribe/{id}")
    public ResponseEntity<Void> subscribe(@PathVariable Long id) {
        Optional<Subject> subject = subjectService.findSubject(id);
        if (subject.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        subjectService.subscribeToSubject(subject.get());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('USER')")
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
