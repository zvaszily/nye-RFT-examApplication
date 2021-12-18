package hu.nye.nyeRFTexamApplication.web.service;

import hu.nye.nyeRFTexamApplication.data.domain.UserEntity;
import hu.nye.nyeRFTexamApplication.web.domain.CreateUserRequest;
import hu.nye.nyeRFTexamApplication.web.domain.UserView;

import java.util.List;

public interface UserServiceInterface {

    UserView getUserById(Long id);

    UserView getUserByEmailAddress(String emailAddress);

    List<UserView> getAllUser();

    void addUser(CreateUserRequest request);

    void deleteUserById(Long id);
}