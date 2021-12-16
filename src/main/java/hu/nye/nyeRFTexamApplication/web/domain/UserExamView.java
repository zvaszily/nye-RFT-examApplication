package hu.nye.nyeRFTexamApplication.web.domain;

import hu.nye.nyeRFTexamApplication.data.domain.ExamEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * Web layer representation of a user exam.
 */
@Data
@Builder
public class UserExamView implements Comparable<UserExamView> {
    private Long id;
    private String userName;
    private String fullName;
    private String emailAddress;
    private String password;
    private Set<ExamEntity> exams;

    @Override
    public int compareTo(UserExamView o) {
        return userName.compareTo(o.userName);
    }
}
