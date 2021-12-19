package hu.nye.nyeRFTexamApplication.data.dao;

import hu.nye.nyeRFTexamApplication.data.domain.ExamEntity;
import hu.nye.nyeRFTexamApplication.data.domain.UserEntity;
import hu.nye.nyeRFTexamApplication.data.repository.ExamRepository;
import hu.nye.nyeRFTexamApplication.error.ExamNotFoundException;
import hu.nye.nyeRFTexamApplication.error.UserNotFoundException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.testng.Assert.assertEquals;

public class DefaultExamDataAccessObjectTest {

    private static final Long EXAM_ID = 1420L;
    private static final String EXAM_SUBJECT = "Test";
    private static final String EXAM_TITLE = "Test";
    private static final String EXAM_DATE = "Test";
    private static final String EXAM_LOCATION = "Test";

    @Mock
    ExamRepository examRepository;
    @InjectMocks
    DefaultExamDataAccessObject underTest;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetExamByIdShouldReturnUserWhenCalledWithExistingId() {
        //given
        ExamEntity expected = createExam();
        given(examRepository.findById(EXAM_ID)).willReturn(createOptionalExam());

        //when
        ExamEntity result = underTest.getExamById(EXAM_ID);

        //then
        assertEquals(result, expected);
        then(examRepository).should().findById(EXAM_ID);
    }

    @Test(expectedExceptions = ExamNotFoundException.class)
    public void testGetExamByIdShouldThrowUserNotFoundWhenCalledWithNonExistingId() {
        //given
        given(examRepository.findById(EXAM_ID)).willThrow(ExamNotFoundException.class);

        //when
        underTest.getExamById(EXAM_ID);

        //then exception thrown
    }

    @Test
    public void testGetAllExamShouldReturnListOfUserWhenCalled() {
        //given
        Collection<ExamEntity> expected = List.of(createExam());
        given(examRepository.findAll()).willReturn(List.of(createExam()));

        //when
        Collection<ExamEntity> actual = underTest.getAllExam();

        //then
        assertEquals(actual, expected);
        then(examRepository).should().findAll();
    }

    @Test
    public void testAddExamShouldSaveTheUserWhenCalledWithNonExistingId() {
        //given
        ExamEntity exam = createExam();

        //when
        underTest.addExam(exam);

        //then
        then(examRepository).should().save(exam);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testAddExamShouldThrowRuntimeExceptionWhenUnhandledExceptionHappens() {
        //given
        ExamEntity exam = createExam();
        willThrow(Exception.class).given(examRepository).save(exam);

        //when
        underTest.addExam(exam);

        //then exception thrown
    }

    @Test
    public void testDeleteExamByIdShouldDelegateToRepository() {
        //given
        //when
        underTest.deleteExamById(EXAM_ID);

        //then
        then(examRepository).should().deleteById(EXAM_ID);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testDeleteExamByIdShouldThrowRuntimeExceptionWhenUnhandledExceptionHappens() {
        //given
        willThrow(Exception.class).given(examRepository).deleteById(EXAM_ID);

        //when
        underTest.deleteExamById(EXAM_ID);

        //then exception thrown
    }

    private Optional<ExamEntity> createOptionalExam(){
        return Optional.of(createExam());
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
}