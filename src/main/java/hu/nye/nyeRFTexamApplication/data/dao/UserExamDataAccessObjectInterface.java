package hu.nye.nyeRFTexamApplication.data.dao;

import hu.nye.nyeRFTexamApplication.data.domain.UserEntity;

import java.util.Collection;

public interface UserExamDataAccessObjectInterface {

    UserEntity getUserById(Long userId);

    Collection<UserEntity> getAllUser();

    void addExamForUser(UserEntity entity);

    void deleteExamFromUser(Long userId, Long examId);
}
