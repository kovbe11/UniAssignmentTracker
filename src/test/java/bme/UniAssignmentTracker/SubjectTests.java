package bme.UniAssignmentTracker;

import bme.UniAssignmentTracker.service.SubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SubjectTests {

    @Autowired
    SubjectService subjectService;


    //TODO: H2 database as test database with initial data (1-2 entities per tables)

    @Test
    public void testSubjectCanBeCreated(){

    }

    @Test
    public void testSubjectCanBeUpdated(){

    }

    @Test
    public void testSubjectCanBeDeleted(){

    }

    @Test
    public void testSubjectCanBeSubscribedTo(){

    }

    @Test
    public void testSubjectCanBeUnsubscribedFrom(){

    }

    //to check if i have cascades set up correctly
    @Test
    public void testDeletingSubjectDeletesAllItsRequirements(){

    }



}
