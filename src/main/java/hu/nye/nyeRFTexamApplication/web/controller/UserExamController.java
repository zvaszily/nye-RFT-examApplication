package hu.nye.nyeRFTexamApplication.web.controller;

import hu.nye.nyeRFTexamApplication.web.domain.UserExamView;
import hu.nye.nyeRFTexamApplication.web.service.UserExamServiceInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class UserExamController {

    private static final String GET_USER_EXAM_MAPPING = "/user_exam/{id}";
    private static final String GET_ALL_USER_EXAM_MAPPING = "/user_exam/users";
    private static final String ADD_EXAM_FOR_USER = "/user_exam/add";
    private static final String DELETE_EXAM_FROM_USER_MAPPING = "/user_exam/delete/{id}";

    private UserExamServiceInterface userService;

    public UserExamController(UserExamServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping(path = GET_USER_EXAM_MAPPING)
    public UserExamView getUserExam(@PathVariable @NotNull Long id) {
        return userService.getUserById(id);
    }

    @GetMapping(path = GET_ALL_USER_EXAM_MAPPING)
    public List<UserExamView> getAllUserExam() {
        return userService.getAllUser();
    }
}
