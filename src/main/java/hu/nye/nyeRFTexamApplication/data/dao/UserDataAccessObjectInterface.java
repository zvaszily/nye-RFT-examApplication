package hu.nye.nyeRFTexamApplication.data.dao;

import hu.nye.nyeRFTexamApplication.data.domain.UserEntity;

import java.util.Collection;

/**
 * DAO interface to access to {@link UserEntity}.
 */
public interface UserDataAccessObjectInterface {

    UserEntity getUserById(Long userId);

    UserEntity getUserByEmailAddress(String emailAddress);

    Collection<UserEntity> getAllUser();

    void addUser(UserEntity entity);

    void deleteUserById(Long id);
}
