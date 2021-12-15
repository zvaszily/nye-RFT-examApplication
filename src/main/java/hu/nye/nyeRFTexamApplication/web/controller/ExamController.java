package hu.nye.nyeRFTexamApplication.web.controller;

import hu.nye.nyeRFTexamApplication.web.domain.CreateExamRequest;
import hu.nye.nyeRFTexamApplication.web.domain.ExamView;
import hu.nye.nyeRFTexamApplication.web.service.ExamServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class ExamController {

    private static final String GET_EXAM_MAPPING = "/exam/{id}";
    private static final String GET_ALL_EXAM_MAPPING = "/exam/exams";
    private static final String ADD_EXAM = "/exam/add";
    private static final String DELETE_EXAM_MAPPING = "/exam/delete/{id}";

    private ExamServiceInterface examService;

    @Autowired
    public ExamController(ExamServiceInterface examService) {
        this.examService = examService;
    }

    @GetMapping(path = GET_EXAM_MAPPING)
    public ExamView getExam(@PathVariable @NotNull Long id) {
        return examService.getExamById(id);
    }

    @GetMapping(path = GET_ALL_EXAM_MAPPING)
    public List<ExamView> getAllExam() {
        return examService.getAllExam();
    }

    @PostMapping(path = ADD_EXAM)
    @ResponseStatus(HttpStatus.CREATED)
    public void addExam(@Valid @RequestBody CreateExamRequest request) {
        examService.addExam(request);
    }

    @DeleteMapping(DELETE_EXAM_MAPPING)
    public void deleteUser(@PathVariable @NotNull Long id) {
        examService.deleteUserById(id);

    }
}
