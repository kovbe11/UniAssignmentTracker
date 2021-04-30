package bme.UniAssignmentTracker.web.rest;


import bme.UniAssignmentTracker.security.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class SubjectController {


    @GetMapping("/plainTest")
    String hello(){
        return "hello";
    }

    @GetMapping
    ResponseEntity<String> test() {
        return ResponseEntity.ok().body(SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccessDeniedException("User not logged in!!!!!!")));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    ResponseEntity<String> testAdmin(){
        return ResponseEntity.ok().body(SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccessDeniedException("User not logged in!!!!!!")));
    }
}
