package hu.nye.nyeRFTexamApplication.web.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Represents a CREATE request and contains necessary data about the user.
 */
@Builder
@Data
public class CreateUserRequest {

    @NotBlank(message = "Name is mandatory")
    private String userName;
    @NotBlank(message = "Name is mandatory")
    private String fullName;
    @NotBlank(message = "Email is mandatory")
    private String emailAddress;
    @NotBlank(message = "Password is mandatory")
    private String password;
}
