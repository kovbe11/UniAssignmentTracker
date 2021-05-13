package bme.UniAssignmentTracker.service;


import bme.UniAssignmentTracker.domain.requirements.Exam;
import bme.UniAssignmentTracker.repository.ExamRepository;
import bme.UniAssignmentTracker.web.rest.dto.ExamDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Transactional
public class ExamService {

    private final Logger log = LoggerFactory.getLogger(ExamService.class);

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private SubjectService subjectService;

    public Exam findExamOrThrow(Long id) {
        return findExam(id).orElseThrow();
    }

    public Optional<Exam> findExam(Long id) {
        log.debug("Find exam by id requested");
        return examRepository.findById(id);
    }

    public Exam saveExam(Exam exam) {
        log.debug("Save exam requested");
        return examRepository.save(exam);
    }

    public void deleteExam(Long id) {
        log.debug("Delete exam by id requested");
        examRepository.deleteById(id);
    }

    public void deleteExam(Exam exam) {
        log.debug("Delete exam by exam requested");
        examRepository.delete(exam);
    }


    public Exam createExam(@NotNull Long subjectId, @NotNull ExamDTO examDTO) {
        log.debug("Create exam requested");
        var subject = subjectService.findSubjectOrThrow(subjectId);
        var exam = new Exam();

        exam.setSubject(subject);
        exam.setExperiences(examDTO.getExperiences());
        exam.setScoring(examDTO.getScoring());
        exam.setDueDate(examDTO.getDueDate());

        return examRepository.save(exam);
    }

    public Exam patchExam(Long id, @NotNull ExamDTO examDTO) {
        log.debug("Patch exam requested");
        var exam = findExamOrThrow(id);

        //TODO: maybe this logic should be put in either ExamDTO class, or in Exam, bc I will forget to update one some time

        if (examDTO.getDueDate() != null) {
            exam.setDueDate(examDTO.getDueDate());
        }

        if (examDTO.getExperiences() != null) {
            exam.setExperiences(examDTO.getExperiences());
        }

        if (examDTO.getScoring() != null) {
            exam.setScoring(examDTO.getScoring());
        }

        return examRepository.save(exam);
    }
}
