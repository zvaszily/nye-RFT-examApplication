package hu.nye.nyeRFTexamApplication.web.transformer;

import hu.nye.nyeRFTexamApplication.data.domain.ExamEntity;
import hu.nye.nyeRFTexamApplication.data.domain.UserEntity;
import hu.nye.nyeRFTexamApplication.web.domain.CreateExamRequest;
import hu.nye.nyeRFTexamApplication.web.domain.CreateUserRequest;
import hu.nye.nyeRFTexamApplication.web.domain.ExamView;
import hu.nye.nyeRFTexamApplication.web.domain.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExamTransformer {

    @Autowired
    public ExamTransformer() {

    }

    public List<ExamView> transform(Collection<ExamEntity> collection){
        List<ExamView> result = null;
        if(collection != null){
            result = collection.stream().map(this::transform).collect(Collectors.toList());
        }
        return result;
    }

    public ExamView transform(ExamEntity entity){
        ExamView result = null;
        if(entity != null){
            result = ExamView.builder()
                    .id(entity.getId())
                    .subject(entity.getSubject())
                    .title(entity.getTitle())
                    .examDate(entity.getExamDate())
                    .examLocation(entity.getExamLocation())
                    .build();
        }
        return result;
    }

    public ExamEntity transform(CreateExamRequest request){
        ExamEntity result = null;
        if(request != null){
            result = new ExamEntity();
            result.setSubject(request.getSubject());
            result.setTitle(request.getTitle());
            result.setExamDate(request.getExamDate());
            result.setExamLocation(request.getExamLocation());
        }
        return result;
    }
}
