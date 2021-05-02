package bme.UniAssignmentTracker;


import bme.UniAssignmentTracker.service.AssignmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AssignmentTests {

    @Autowired
    AssignmentService assignmentService;


    @Test
    public void testAssignmentCanBeCreated() {

    }

    @Test
    public void testAssignmentCanBeUpdated() {

    }

    @Test
    public void testAssignmentCanBeDeleted() {

    }

    //not sure if this is necessary, but finding this bug would take ages if it happened
    @Test
    public void testDeletingAssignmentDeletesProjectAssignment() {

    }

}
