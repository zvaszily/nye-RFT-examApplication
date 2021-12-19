package hu.nye.nyeRFTexamApplication.data.dao;

import static org.mockito.BDDMockito.*;
import static org.testng.Assert.assertEquals;

import hu.nye.nyeRFTexamApplication.data.domain.UserEntity;
import hu.nye.nyeRFTexamApplication.data.repository.UserRepository;
import hu.nye.nyeRFTexamApplication.error.EmailAlreadyUseException;
import hu.nye.nyeRFTexamApplication.error.UserNotFoundException;
import org.mockito.*;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public class DefaultUserDataAccessObjectTest {

    private static final Long USER_ID = 1420L;
    private static final String USER_NAME = "Test";
    private static final String USER_FULL_NAME = "Test User";
    private static final String USER_EMAIL = "Test@User";
    private static final String USER_PASSWORD = "pass";


    @Mock
    UserRepository userRepository;
    @InjectMocks
    DefaultUserDataAccessObject underTest;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserByIdShouldReturnUserWhenCalledWithExistingId() {
        //given
        UserEntity expected = createUser();
        given(userRepository.findById(USER_ID)).willReturn(createOptionalUser());

        //when
        UserEntity result = underTest.getUserById(USER_ID);

        //then
        assertEquals(result, expected);
        then(userRepository).should().findById(USER_ID);
    }

    @Test
    public void testGetUserByEmailAddress() {
        when(userRepository.findByEmailAddress(anyString())).thenReturn(new UserEntity());

        UserEntity result = underTest.getUserByEmailAddress("emailAddress");
        assertEquals(result, new UserEntity());
    }

    @Test(expectedExceptions = UserNotFoundException.class)
    public void testGetUserByIdShouldThrowUserNotFoundWhenCalledWithNonExistingId() {
        //given
        given(userRepository.findById(USER_ID)).willThrow(UserNotFoundException.class);

        //when
        underTest.getUserById(USER_ID);

        //then exception thrown
    }

    @Test
    public void testGetAllUserShouldReturnListOfUserWhenCalled() {
        //given
        Collection<UserEntity> expected = List.of(createUser());
        given(userRepository.findAll()).willReturn(List.of(createUser()));

        //when
        Collection<UserEntity> actual = underTest.getAllUser();

        //then
        assertEquals(actual, expected);
        then(userRepository).should().findAll();
    }

    @Test
    public void testAddUserShouldSaveTheUserWhenCalledWithNonExistingId() {
        //given
        UserEntity user = createUser();

        //when
        underTest.addUser(user);

        //then
        then(userRepository).should().save(user);
    }

    @Test(expectedExceptions = EmailAlreadyUseException.class)
    public void testAddUserShouldThrowUserAlreadyExistExceptionWhenCalledWithExistingEmail() {
        //given
        UserEntity user = createUser();
        willThrow(DataIntegrityViolationException.class).given(userRepository).save(user);

        //when
        underTest.addUser(user);

        //then exception thrown
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testAddUserShouldThrowRuntimeExceptionWhenUnhandledExceptionHappens() {
        //given
        UserEntity user = createUser();
        willThrow(Exception.class).given(userRepository).save(user);

        //when
        underTest.addUser(user);

        //then exception thrown
    }

    @Test
    public void testDeleteUserByIdShouldDelegateToRepository() {
        //given
        //when
        underTest.deleteUserById(USER_ID);

        //then
        then(userRepository).should().deleteById(USER_ID);
    }

    @Test(expectedExceptions = UserNotFoundException.class)
    public void testDeleteUserByIdShouldThrowUserNotFoundExceptionWhenCalledWithNonExistingId() {
        //given
        willThrow(EmptyResultDataAccessException.class).given(userRepository).deleteById(USER_ID);

        //when
        underTest.deleteUserById(USER_ID);

        //then exception thrown
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testDeleteUserByIdShouldThrowRuntimeExceptionWhenUnhandledExceptionHappens() {
        //given
        willThrow(Exception.class).given(userRepository).deleteById(USER_ID);

        //when
        underTest.deleteUserById(USER_ID);

        //then exception thrown
    }

    private Optional<UserEntity> createOptionalUser(){
        return Optional.of(createUser());
    }

    private UserEntity createUser() {
        UserEntity user = new UserEntity();
        user.setId(USER_ID);
        user.setUserName(USER_NAME);
        user.setFullName(USER_FULL_NAME);
        user.setEmailAddress(USER_EMAIL);
        user.setPassword(USER_PASSWORD);
        return user;
    }
}


