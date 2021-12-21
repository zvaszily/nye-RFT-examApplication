package hu.nye.nyeRFTexamApplication.web.service;

import hu.nye.nyeRFTexamApplication.data.dao.ExamDataAccessObjectInterface;
import hu.nye.nyeRFTexamApplication.data.domain.ExamEntity;
import hu.nye.nyeRFTexamApplication.data.domain.UserEntity;
import hu.nye.nyeRFTexamApplication.error.ExamNotFoundException;
import hu.nye.nyeRFTexamApplication.error.UserNotFoundException;
import hu.nye.nyeRFTexamApplication.web.domain.CreateExamRequest;
import hu.nye.nyeRFTexamApplication.web.domain.CreateUserRequest;
import hu.nye.nyeRFTexamApplication.web.domain.ExamView;
import hu.nye.nyeRFTexamApplication.web.domain.UserView;
import hu.nye.nyeRFTexamApplication.web.transformer.ExamTransformer;
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

public class DefaultExamServiceTest {

    private static final Long EXAM_ID = 1420L;
    private static final String EXAM_SUBJECT = "RFT";
    private static final String EXAM_TITLE = "Gyak";
    private static final String EXAM_DATE = "2021-01-06";
    private static final String EXAM_LOCATION = "Nye";

    @Mock
    ExamDataAccessObjectInterface examDataAccessObject;
    @Mock
    ExamTransformer transformer;
    @InjectMocks
    DefaultExamService underTest;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetExamByIdShouldTransformAndReturnUserWhenCalledWithValidId() {
        //given
        ExamEntity entity = createExam();
        ExamView expected = createExamView();
        given(examDataAccessObject.getExamById(EXAM_ID)).willReturn(entity);
        given(transformer.transform(entity)).willReturn(expected);

        //when
        ExamView actual = underTest.getExamById(EXAM_ID);

        //then
        assertEquals(actual, expected);
        then(examDataAccessObject).should().getExamById(EXAM_ID);
        then(transformer).should().transform(entity);
    }

    @Test(expectedExceptions = ExamNotFoundException.class)
    public void testGetExamByIdShouldThrowUserNotFoundExceptionWhenCalledWithInvalidId() {
        //given
        ExamEntity entity = createExam();
        given(examDataAccessObject.getExamById(EXAM_ID)).willReturn(entity);
        given(transformer.transform(entity)).willThrow(ExamNotFoundException.class);

        //when
        underTest.getExamById(EXAM_ID);

        //then exception thrown
    }

    @Test
    public void testGetAllExamShouldReturnAListOfUser() {
        //given
        List<ExamView> unsortedExamList = List.of(
                createExamView(),
                createExamView()
        );
        List<ExamEntity> examEntities = List.of(
                createExam(),
                createExam()
        );
        given(examDataAccessObject.getAllExam()).willReturn(examEntities);
        given(transformer.transform(examEntities)).willReturn(unsortedExamList);
        List<ExamView> expected = List.of(
                createExamView(),
                createExamView()
        );

        //when
        List<ExamView> actual = underTest.getAllExam();

        //then
        assertEquals(actual, expected);
        then(examDataAccessObject).should().getAllExam();
        then(transformer).should().transform(examEntities);
    }

    @Test
    public void testAddExamShouldDelegateToTransformerAndUserAccessObject() {
        //given
        CreateExamRequest request = createCreateExamRequest();
        ExamEntity examEntity = createExam();
        given(transformer.transform(request)).willReturn(examEntity);

        //when
        underTest.addExam(request);

        //then
        then(examDataAccessObject).should().addExam(examEntity);
    }

    private ExamEntity createExam() {
        ExamEntity exam = new ExamEntity();
        exam.setId(EXAM_ID);
        exam.setSubject(EXAM_SUBJECT);
        exam.setTitle(EXAM_TITLE);
        exam.setExamDate(EXAM_DATE);
        exam.setExamLocation(EXAM_LOCATION);
        return exam;
    }

    private ExamView createExamView() {
        return ExamView.builder()
                .id(EXAM_ID)
                .subject(EXAM_SUBJECT)
                .title(EXAM_TITLE)
                .examDate(EXAM_DATE)
                .examLocation(EXAM_LOCATION)
                .build();
    }

    private CreateExamRequest createCreateExamRequest(){
        return CreateExamRequest.builder()
                .subject(EXAM_SUBJECT)
                .title(EXAM_TITLE)
                .examDate(EXAM_DATE)
                .examLocation(EXAM_LOCATION)
                .build();
    }

}