package hu.nye.nyeRFTexamApplication.data.dao;

import hu.nye.nyeRFTexamApplication.data.domain.ExamEntity;
import hu.nye.nyeRFTexamApplication.data.repository.ExamRepository;
import hu.nye.nyeRFTexamApplication.error.EmailAlreadyUseException;
import hu.nye.nyeRFTexamApplication.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class DefaultExamDataAccessObject implements ExamDataAccessObjectInterface {

    private ExamRepository examRepository;

    @Autowired
    public DefaultExamDataAccessObject(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public ExamEntity getExamById(Long examId) {
        return examRepository.findById(examId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + examId));
    }

    @Override
    public Collection<ExamEntity> getAllExam() {
        return examRepository.findAll();
    }

    @Override
    public void addExam(ExamEntity entity) {
        try{
            examRepository.save(entity);
        }catch (Exception e){
            throw new RuntimeException("Something went wrong.");
        }

    }

    @Override
    public void deleteExamById(Long id) {
        try{
            examRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException("Exam not found with id: " + id);
        }catch (Exception e){
            throw new RuntimeException("Something went wrong.");
        }

    }
}
