package hu.nye.nyeRFTexamApplication.web.service;

import hu.nye.nyeRFTexamApplication.web.domain.UserExamView;
import hu.nye.nyeRFTexamApplication.web.domain.UserView;

import java.util.List;

public interface UserExamServiceInterface {

    UserExamView getUserById(Long id);

    List<UserExamView> getAllUser();

}
