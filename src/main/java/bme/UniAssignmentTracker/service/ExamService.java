package bme.UniAssignmentTracker.service;


import bme.UniAssignmentTracker.domain.requirements.Exam;
import bme.UniAssignmentTracker.repository.ExamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ExamService {

    private final Logger log = LoggerFactory.getLogger(ExamService.class);

    @Autowired
    private ExamRepository examRepository;

    public Exam findExamOrThrow(Long id){
        return findExam(id).orElseThrow();
    }

    public Optional<Exam> findExam(Long id){
        log.debug("Find exam by id requested");
        return examRepository.findById(id);
    }

    public Exam saveExam(Exam exam){
        log.debug("Save exam requested");
        return examRepository.save(exam);
    }

    public void deleteExam(Long id){
        log.debug("Delete exam by id requested");
        examRepository.deleteById(id);
    }

    public void deleteExam(Exam exam){
        log.debug("Delete exam by exam requested");
        examRepository.delete(exam);
    }


}
