package bme.UniAssignmentTracker.web.rest;

import bme.UniAssignmentTracker.domain.requirements.Exam;
import bme.UniAssignmentTracker.service.ExamService;
import bme.UniAssignmentTracker.web.rest.dto.ExamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping("/exams/{id}")
    public ResponseEntity<Exam> getExam(@PathVariable Long id) {
        var optExam = examService.findExam(id);
        //TODO: can i throw exceptions that are handled with 404 error?
        if (optExam.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optExam.get());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/subjects/{subjectId}/exams")
    public ResponseEntity<Exam> create(@PathVariable Long subjectId, @RequestBody @Valid ExamDTO examDTO) throws URISyntaxException {
        var savedExam = examService.createExam(subjectId, examDTO);
        return ResponseEntity.created(new URI("/api/exams/" + savedExam.getId()))
                .body(savedExam);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/exams/{id}")
    public ResponseEntity<Exam> patch(@PathVariable Long id, @RequestBody @Valid ExamDTO examDTO) {
        var savedExam = examService.patchExam(id, examDTO);
        return ResponseEntity.ok(savedExam);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/exams/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.ok().build();
    }


}
