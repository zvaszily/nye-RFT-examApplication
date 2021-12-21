package hu.nye.nyeRFTexamApplication.web.transformer;

import hu.nye.nyeRFTexamApplication.data.domain.UserEntity;
import hu.nye.nyeRFTexamApplication.web.domain.CreateUserRequest;
import hu.nye.nyeRFTexamApplication.web.domain.UserView;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class UserTransformerTest {

    private static final String USER_NAME = "Test";
    private static final String USER_FULL_NAME = "Test User";
    private static final String USER_EMAIL = "Test@User";

    @InjectMocks
    private UserTransformer underTest;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testTransformToViewShouldReturnAListOfEntryViewsWhenCalledWithACollectionOfEntryEntities() {
        //given
        List<UserView> expected = List.of(createUserView());
        List<UserEntity> entityList = List.of(createUser());

        //when
        List<UserView> actual = underTest.transform(entityList);

        //then
        assertEquals(actual, expected);
    }

    @Test
    public void testTransformToViewShouldReturnEntityWhenCalledWithAUserRequest() {
        //given
        CreateUserRequest request = createCreateUserRequest();
        UserEntity expected = createUser();

        //when
        UserEntity actual = underTest.transform(request);

        //then
        assertEquals(actual, expected);
    }

    @Test
    public void testTransformToViewShouldReturnAViewWhenCalledWithAEntity() {
        //given
        UserView expected = createUserView();
        UserEntity userEntity = createUser();

        //when
        UserView actual = underTest.transform(userEntity);

        //then
        assertEquals(actual, expected);
    }

    private UserEntity createUser() {
        UserEntity user = new UserEntity();
        user.setUserName(USER_NAME);
        user.setFullName(USER_FULL_NAME);
        user.setEmailAddress(USER_EMAIL);
        return user;
    }

    private UserView createUserView() {
        return UserView.builder()
                .userName(USER_NAME)
                .fullName(USER_FULL_NAME)
                .emailAddress(USER_EMAIL)
                .build();
    }

    private CreateUserRequest createCreateUserRequest(){
        return CreateUserRequest.builder()
                .userName(USER_NAME)
                .emailAddress(USER_EMAIL)
                .fullName(USER_FULL_NAME)
                .build();
    }
}