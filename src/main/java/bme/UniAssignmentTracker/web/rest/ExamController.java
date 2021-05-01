package bme.UniAssignmentTracker.web.rest;

import bme.UniAssignmentTracker.service.AssignmentService;
import bme.UniAssignmentTracker.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

}
