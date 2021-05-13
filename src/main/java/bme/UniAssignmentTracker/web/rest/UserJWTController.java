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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "*")
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

    private static final String ERROR_KEY = "error";


    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@Valid @RequestBody LoginDTO loginDTO) {
        var authToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication auth;
        try {
            auth = authManager.authenticate(authToken);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(ERROR_KEY, "Wrong username or password!"));
        }
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtUtils.generateJwtToken(auth);
        return ResponseEntity.ok().body(Map.of("token", jwt));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registerAdmin")
    public ResponseEntity<Map<String,String>> registerAdmin(@Valid @RequestBody LoginDTO loginDTO) {
        if (userRepository.existsByUsername(loginDTO.getUsername())) {
            return ResponseEntity.badRequest().body(Map.of(ERROR_KEY, "User already exists!"));
        }

        var user = new User();
        var admin = new Authority();
        admin.setName(AuthoritiesConstants.ADMIN);

        user.setUsername(loginDTO.getUsername());
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));

        user.setAuthorities(Set.of(admin));
        userRepository.save(user);

        return login(loginDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUser(@Valid @RequestBody LoginDTO loginDTO) {
        if (userRepository.existsByUsername(loginDTO.getUsername())) {
            return ResponseEntity.badRequest().body(Map.of(ERROR_KEY, "User already exists!"));
        }

        var user = new User();
        var userRole = new Authority();
        userRole.setName(AuthoritiesConstants.USER);

        user.setUsername(loginDTO.getUsername());
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));

        user.setAuthorities(Set.of(userRole));
        userRepository.save(user);

        return login(loginDTO);
    }

}
