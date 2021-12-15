package hu.nye.nyeRFTexamApplication.web.service;

import hu.nye.nyeRFTexamApplication.data.dao.UserDataAccessObjectInterface;
import hu.nye.nyeRFTexamApplication.data.domain.UserEntity;
import hu.nye.nyeRFTexamApplication.web.domain.CreateUserRequest;
import hu.nye.nyeRFTexamApplication.web.domain.UserView;
import hu.nye.nyeRFTexamApplication.web.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultUserService implements UserServiceInterface{

    private UserDataAccessObjectInterface userDataAccessObject;
    private UserTransformer transformer;

    @Autowired
    public DefaultUserService(UserDataAccessObjectInterface userDataAccessObject, UserTransformer transformer) {
        this.userDataAccessObject = userDataAccessObject;
        this.transformer = transformer;
    }

    @Override
    public UserView getUserById(Long id) {
        UserEntity userEntity = userDataAccessObject.getUserById(id);
        return transformer.transform(userEntity);
    }

    @Override
    public List<UserView> getAllUser() {
        Collection<UserEntity> userEntities  = userDataAccessObject.getAllUser();
        List<UserView> users = transformer.transform(userEntities);
        return users.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public void addUser(CreateUserRequest request) {
        UserEntity userEntity = transformer.transform(request);
        userDataAccessObject.addUser(userEntity);
    }

    @Override
    public void deleteUserById(Long id) {
        userDataAccessObject.deleteUserById(id);
    }
}
