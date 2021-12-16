package hu.nye.nyeRFTexamApplication.web.transformer;

import hu.nye.nyeRFTexamApplication.data.domain.UserEntity;
import hu.nye.nyeRFTexamApplication.web.domain.CreateUserExamRequest;
import hu.nye.nyeRFTexamApplication.web.domain.CreateUserRequest;
import hu.nye.nyeRFTexamApplication.web.domain.UserExamView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserExamTransformer {

    @Autowired
    public UserExamTransformer() {
    }

    public List<UserExamView> transform(Collection<UserEntity> collection){
        List<UserExamView> result = null;
        if(collection != null){
            result = collection.stream().map(this::transform).collect(Collectors.toList());
        }
        return result;
    }

    public UserExamView transform(UserEntity entity){
        UserExamView result = null;
        if(entity != null){
            result = UserExamView.builder()
                    .id(entity.getId())
                    .userName(entity.getUserName())
                    .emailAddress(entity.getEmailAddress())
                    .fullName(entity.getFullName())
                    .password(entity.getPassword())
                    .exams(entity.getExams())
                    .build();
        }
        return result;
    }

    public UserEntity transform(CreateUserExamRequest request){
        UserEntity result = null;
        if(request != null){
            result = new UserEntity();
            result.setEmailAddress(request.getEmailAddress());
            result.setUserName(request.getUserName());
            result.setFullName(request.getFullName());
            result.setPassword(request.getPassword());
            result.setExams(request.getExams());
        }
        return result;
    }

}
