package hu.nye.nyeRFTexamApplication.web.service;

import hu.nye.nyeRFTexamApplication.data.dao.UserExamDataAccessObjectInterface;
import hu.nye.nyeRFTexamApplication.data.domain.UserEntity;
import hu.nye.nyeRFTexamApplication.web.domain.UserExamView;
import hu.nye.nyeRFTexamApplication.web.transformer.UserExamTransformer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultUserExamService implements UserExamServiceInterface {

    private UserExamDataAccessObjectInterface userExamDataAccessObject;
    private UserExamTransformer transformer;

    public DefaultUserExamService(UserExamDataAccessObjectInterface userExamDataAccessObject, UserExamTransformer transformer) {
        this.userExamDataAccessObject = userExamDataAccessObject;
        this.transformer = transformer;
    }

    @Override
    public UserExamView getUserById(Long id) {
        UserEntity userEntity = userExamDataAccessObject.getUserById(id);
        return transformer.transform(userEntity);
    }

    @Override
    public List<UserExamView> getAllUser() {
        Collection<UserEntity> userEntities = userExamDataAccessObject.getAllUser();
        List<UserExamView> users = transformer.transform(userEntities);
        return users.stream().sorted().collect(Collectors.toList());
    }


}
