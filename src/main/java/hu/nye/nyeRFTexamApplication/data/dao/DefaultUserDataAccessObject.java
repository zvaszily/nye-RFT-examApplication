package hu.nye.nyeRFTexamApplication.data.dao;

import hu.nye.nyeRFTexamApplication.data.domain.UserEntity;
import hu.nye.nyeRFTexamApplication.data.repository.UserRepository;
import hu.nye.nyeRFTexamApplication.error.EmailAlreadyUseException;
import hu.nye.nyeRFTexamApplication.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class DefaultUserDataAccessObject implements UserDataAccessObjectInterface{

    private UserRepository userRepository;

    @Autowired
    public DefaultUserDataAccessObject(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public void addUser(UserEntity entity) {
        try{
            userRepository.save(entity);
        }catch (DataIntegrityViolationException e){
            throw new EmailAlreadyUseException("Email address: "+ entity.getEmailAddress() + " already in use.");
        }catch (Exception e){
            throw new RuntimeException("Something went wrong.");
        }
    }

    @Override
    public void deleteUserById(Long id) {
        try{
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException("User not found with id: " + id);
        }catch (Exception e){
            throw new RuntimeException("Something went wrong.");
        }
    }
}
