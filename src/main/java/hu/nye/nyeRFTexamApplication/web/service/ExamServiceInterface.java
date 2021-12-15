package hu.nye.nyeRFTexamApplication.web.service;

import hu.nye.nyeRFTexamApplication.web.domain.CreateExamRequest;
import hu.nye.nyeRFTexamApplication.web.domain.ExamView;

import java.util.List;

public interface ExamServiceInterface {

    ExamView getExamById(Long id);

    List<ExamView> getAllExam();

    void addExam(CreateExamRequest request);

    void deleteUserById(Long id);
}
