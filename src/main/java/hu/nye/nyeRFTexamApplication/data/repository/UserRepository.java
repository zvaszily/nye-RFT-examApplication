package hu.nye.nyeRFTexamApplication.data.repository;

import hu.nye.nyeRFTexamApplication.data.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmailAddress(String emailAddress);
}
