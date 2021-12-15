package hu.nye.nyeRFTexamApplication.web.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Represents a CREATE request and contains necessary data about the exam.
 */
@Builder
@Data
public class CreateExamRequest {
    @NotBlank(message = "Subject is mandatory")
    private String subject;
    private String title;
    @NotBlank(message = "Exam date is mandatory")
    private String examDate;
    @NotBlank(message = "Exam location is mandatory")
    private String examLocation;
}
