package bme.UniAssignmentTracker.web.rest;


import bme.UniAssignmentTracker.domain.Authority;
import bme.UniAssignmentTracker.domain.User;
import bme.UniAssignmentTracker.repository.UserRepository;
import bme.UniAssignmentTracker.security.AuthoritiesConstants;
import bme.UniAssignmentTracker.security.JwtUtils;
import bme.UniAssignmentTracker.security.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/")
public class UserJWTController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) {
        var authToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication auth = authManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtUtils.generateJwtToken(auth);
        return ResponseEntity.ok().body(jwt);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registerAdmin")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody LoginDTO loginDTO) {
        if (userRepository.existsByUsername(loginDTO.getUsername())) {
            return ResponseEntity.badRequest().body("User already exists!");
        }

        User user = new User();
        Authority admin = new Authority();
        admin.setName(AuthoritiesConstants.ADMIN);

        user.setUsername(loginDTO.getUsername());
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));

        user.setAuthorities(Set.of(admin));
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User is created with username: " + loginDTO.getUsername());
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody LoginDTO loginDTO) {
        if (userRepository.existsByUsername(loginDTO.getUsername())) {
            return ResponseEntity.badRequest().body("User already exists!");
        }

        User user = new User();
        Authority userRole = new Authority();
        userRole.setName(AuthoritiesConstants.USER);

        user.setUsername(loginDTO.getUsername());
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));

        user.setAuthorities(Set.of(userRole));
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User is created with username: " + loginDTO.getUsername());
    }

}
