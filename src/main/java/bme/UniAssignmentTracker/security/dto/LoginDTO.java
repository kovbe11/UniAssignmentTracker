package bme.UniAssignmentTracker.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
public class LoginDTO {
    @Size(min=4, max = 20)
    private String username;
    @Size(min = 4, max = 50)
    private String password;
}
