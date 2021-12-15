package hu.nye.nyeRFTexamApplication.data.dao;

import hu.nye.nyeRFTexamApplication.data.domain.ExamEntity;

import java.util.Collection;

/**
 * DAO interface to access to {@link ExamEntity}.
 */
public interface ExamDataAccessObjectInterface {
    ExamEntity getExamById(Long examID);

    Collection<ExamEntity> getAllExam();

    void addExam(ExamEntity entity);

    void deleteExamById(Long id);

}
