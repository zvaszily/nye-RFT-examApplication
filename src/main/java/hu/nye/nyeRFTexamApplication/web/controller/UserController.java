package hu.nye.nyeRFTexamApplication.web.controller;

import hu.nye.nyeRFTexamApplication.web.domain.CreateUserRequest;
import hu.nye.nyeRFTexamApplication.web.domain.UserView;
import hu.nye.nyeRFTexamApplication.web.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class UserController {

    private static final String GET_USER_MAPPING = "/user/{id}";
    private static final String GET_USER_MAPPING_By_EMAIL = "/user/email/{emailAddress}";
    private static final String GET_ALL_USER_MAPPING = "/user/users";
    private static final String ADD_USER = "/user/add";
    private static final String DELETE_USER_MAPPING = "/user/delete/{id}";

    private UserServiceInterface userService;

    @Autowired
    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping(path = GET_USER_MAPPING)
    public UserView getUser(@PathVariable @NotNull Long id){
        return userService.getUserById(id);
    }

    @GetMapping(path = GET_USER_MAPPING_By_EMAIL)
    public UserView getUserByEmail(@PathVariable @NotNull String emailAddress){
        return userService.getUserByEmailAddress(emailAddress);
    }

    @GetMapping(path = GET_ALL_USER_MAPPING)
    public List<UserView> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping(path = ADD_USER)
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@Valid @RequestBody CreateUserRequest request){
        userService.addUser(request);
    }

    @DeleteMapping(DELETE_USER_MAPPING)
    public void deleteUser(@PathVariable @NotNull Long id){
        userService.deleteUserById(id);
    }
}
