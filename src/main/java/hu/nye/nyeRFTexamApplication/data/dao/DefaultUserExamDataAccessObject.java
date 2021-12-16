package hu.nye.nyeRFTexamApplication.data.dao;

import hu.nye.nyeRFTexamApplication.data.domain.UserEntity;
import hu.nye.nyeRFTexamApplication.data.repository.ExamRepository;
import hu.nye.nyeRFTexamApplication.data.repository.UserRepository;
import hu.nye.nyeRFTexamApplication.error.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class DefaultUserExamDataAccessObject implements  UserExamDataAccessObjectInterface {

    private UserRepository userRepository;
    private ExamRepository examRepository;

    public DefaultUserExamDataAccessObject(UserRepository userRepository, ExamRepository examRepository) {
        this.userRepository = userRepository;
        this.examRepository = examRepository;
    }

    @Override
    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    @Override
    public Collection<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void addExamForUser(UserEntity entity) {

    }

    @Override
    public void deleteExamFromUser(Long userId, Long examId) {

    }
}
