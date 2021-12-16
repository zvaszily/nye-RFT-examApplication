package hu.nye.nyeRFTexamApplication.web.domain;

import hu.nye.nyeRFTexamApplication.data.domain.ExamEntity;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * Represents a CREATE request and contains necessary data about the user exam.
 */
@Builder
@Data
public class CreateUserExamRequest {

    @NotBlank(message = "Name is mandatory")
    private String userName;
    @NotBlank(message = "Name is mandatory")
    private String fullName;
    @NotBlank(message = "Email is mandatory")
    private String emailAddress;
    @NotBlank(message = "Password is mandatory")
    private String password;
    private Set<ExamEntity> exams;
}
