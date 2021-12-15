package hu.nye.nyeRFTexamApplication.data.repository;

import hu.nye.nyeRFTexamApplication.data.domain.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {
}
