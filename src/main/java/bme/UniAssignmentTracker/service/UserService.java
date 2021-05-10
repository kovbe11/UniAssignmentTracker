package bme.UniAssignmentTracker.service;

import bme.UniAssignmentTracker.domain.User;
import bme.UniAssignmentTracker.repository.UserRepository;
import bme.UniAssignmentTracker.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    User saveUser(User user){
        return userRepository.save(user);
    }

    User getCurrentUser() {
        //TODO: custom exception
        String username = SecurityUtils.getCurrentUserLogin().orElseThrow();
        return userRepository.findByUsername(username).orElseThrow();
    }

    User getCurrentUserOrNull(){
        var username = SecurityUtils.getCurrentUserLogin();
        if(username.isEmpty()){
            return null;
        }
        return userRepository.findByUsername(username.get()).orElse(null);
    }

}
