package hu.nye.nyeRFTexamApplication.web.transformer;

import hu.nye.nyeRFTexamApplication.data.domain.UserEntity;
import hu.nye.nyeRFTexamApplication.web.domain.CreateUserRequest;
import hu.nye.nyeRFTexamApplication.web.domain.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserTransformer {

    @Autowired
    public UserTransformer() {
    }

    public List<UserView> transform(Collection<UserEntity> collection){
        List<UserView> result = null;
        if(collection != null){
            result = collection.stream().map(this::transform).collect(Collectors.toList());
        }
        return result;
    }

    public UserView transform(UserEntity entity){
        UserView result = null;
        if(entity != null){
            result = UserView.builder()
                    .id(entity.getId())
                    .userName(entity.getUserName())
                    .emailAddress(entity.getEmailAddress())
                    .fullName(entity.getFullName())
                    .password(entity.getPassword())
                    .build();
        }
        return result;
    }

    public UserEntity transform(CreateUserRequest request){
        UserEntity result = null;
        if(request != null){
            result = new UserEntity();
            result.setEmailAddress(request.getEmailAddress());
            result.setUserName(request.getUserName());
            result.setFullName(request.getFullName());
            result.setPassword(request.getPassword());
        }
        return result;
    }
}
