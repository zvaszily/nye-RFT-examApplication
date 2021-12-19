package hu.nye.nyeRFTexamApplication.web.service;

import hu.nye.nyeRFTexamApplication.data.dao.UserDataAccessObjectInterface;
import hu.nye.nyeRFTexamApplication.data.domain.UserEntity;
import hu.nye.nyeRFTexamApplication.error.UserNotFoundException;
import hu.nye.nyeRFTexamApplication.web.domain.CreateUserRequest;
import hu.nye.nyeRFTexamApplication.web.domain.UserView;
import hu.nye.nyeRFTexamApplication.web.transformer.UserTransformer;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class DefaultUserServiceTest {

    private static final Long USER_ID = 1420L;
    private static final String USER_NAME = "Test";
    private static final String USER_FULL_NAME = "Test User";
    private static final String USER_EMAIL = "Test@User";
    private static final String USER_PASSWORD = "pass";

    @Mock
    UserDataAccessObjectInterface userDataAccessObject;
    @Mock
    UserTransformer transformer;
    @InjectMocks
    DefaultUserService underTest;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserByIdShouldTransformAndReturnUserWhenCalledWithValidId() {
        //given
        UserEntity entity = createUser();
        UserView expected = createUserView();
        given(userDataAccessObject.getUserById(USER_ID)).willReturn(entity);
        given(transformer.transform(entity)).willReturn(expected);

        //when
        UserView actual = underTest.getUserById(USER_ID);

        //then
        assertEquals(actual, expected);
        then(userDataAccessObject).should().getUserById(USER_ID);
        then(transformer).should().transform(entity);
    }

    @Test(expectedExceptions = UserNotFoundException.class)
    public void testGetUserByIdShouldThrowUserNotFoundExceptionWhenCalledWithInvalidId() {
        //given
        UserEntity entity = createUser();
        given(userDataAccessObject.getUserById(USER_ID)).willReturn(entity);
        given(transformer.transform(entity)).willThrow(UserNotFoundException.class);

        //when
        underTest.getUserById(USER_ID);

        //then exception thrown
    }

    @Test
    public void testGetAllUserShouldReturnAListOfUser() {
        //given
        List<UserView> unsortedUserList = List.of(
                createUserView(),
                createUserView()
        );
        List<UserEntity> userEntities = List.of(
                createUser(),
                createUser()
        );
        given(userDataAccessObject.getAllUser()).willReturn(userEntities);
        given(transformer.transform(userEntities)).willReturn(unsortedUserList);
        List<UserView> expected = List.of(
                createUserView(),
                createUserView()
        );

        //when
        List<UserView> actual = underTest.getAllUser();

        //then
        assertEquals(actual, expected);
        then(userDataAccessObject).should().getAllUser();
        then(transformer).should().transform(userEntities);
    }

    @Test
    public void testAddUserShouldDelegateToTransformerAndUserAccessObject() {
        //given
        CreateUserRequest request = createCreateUserRequest();
        UserEntity userEntity = createUser();
        given(transformer.transform(request)).willReturn(userEntity);

        //when
        underTest.addUser(request);

        //then
        then(userDataAccessObject).should().addUser(userEntity);
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

    private UserView createUserView() {
        return UserView.builder()
                .id(USER_ID)
                .userName(USER_NAME)
                .fullName(USER_FULL_NAME)
                .build();
    }

    private CreateUserRequest createCreateUserRequest(){
        return CreateUserRequest.builder()
                .userName(USER_NAME)
                .emailAddress(USER_EMAIL)
                .fullName(USER_FULL_NAME)
                .password(USER_PASSWORD)
                .build();
    }
}