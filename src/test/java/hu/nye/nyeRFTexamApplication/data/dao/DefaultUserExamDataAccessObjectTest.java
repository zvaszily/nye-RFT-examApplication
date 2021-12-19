package hu.nye.nyeRFTexamApplication.data.dao;

import hu.nye.nyeRFTexamApplication.data.domain.UserEntity;
import hu.nye.nyeRFTexamApplication.data.repository.ExamRepository;
import hu.nye.nyeRFTexamApplication.data.repository.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.testng.Assert.assertEquals;

public class DefaultUserExamDataAccessObjectTest {

    private static final Long USER_ID = 1420L;
    private static final String USER_NAME = "Test";
    private static final String USER_FULL_NAME = "Test User";
    private static final String USER_EMAIL = "Test@User";
    private static final String USER_PASSWORD = "pass";

    @Mock
    UserRepository userRepository;
    @Mock
    ExamRepository examRepository;
    @InjectMocks
    DefaultUserExamDataAccessObject underTest;

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
    public void testAddExamForUser() {
        underTest.addExamForUser(new UserEntity());
    }

    @Test
    public void testDeleteExamFromUser() {
        underTest.deleteExamFromUser(Long.valueOf(1), Long.valueOf(1));
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
