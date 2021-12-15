package hu.nye.nyeRFTexamApplication.web.service;

import hu.nye.nyeRFTexamApplication.data.dao.ExamDataAccessObjectInterface;
import hu.nye.nyeRFTexamApplication.data.domain.ExamEntity;
import hu.nye.nyeRFTexamApplication.web.domain.CreateExamRequest;
import hu.nye.nyeRFTexamApplication.web.domain.ExamView;
import hu.nye.nyeRFTexamApplication.web.transformer.ExamTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultExamService implements ExamServiceInterface{

    private ExamDataAccessObjectInterface examDataAccessObject;
    private ExamTransformer transformer;

    @Autowired
    public DefaultExamService(ExamDataAccessObjectInterface examDataAccessObject, ExamTransformer transformer) {
        this.examDataAccessObject = examDataAccessObject;
        this.transformer = transformer;
    }

    @Override
    public ExamView getExamById(Long id) {
        ExamEntity examEntity = examDataAccessObject.getExamById(id);
        return transformer.transform(examEntity);
    }

    @Override
    public List<ExamView> getAllExam() {
        Collection<ExamEntity> examEntities = examDataAccessObject.getAllExam();
        List<ExamView> exams = transformer.transform(examEntities);
        return exams.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public void addExam(CreateExamRequest request) {
        ExamEntity examEntity = transformer.transform(request);
        examDataAccessObject.addExam(examEntity);
    }

    @Override
    public void deleteUserById(Long id) {
        examDataAccessObject.deleteExamById(id);
    }
}
