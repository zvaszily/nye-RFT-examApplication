package hu.nye.nyeRFTexamApplication.web.domain;

import hu.nye.nyeRFTexamApplication.data.domain.ExamEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * Web layer representation of a user.
 */
@Data
@Builder
public class UserView implements Comparable<UserView>{

    private Long id;
    private String userName;
    private String fullName;
    private String emailAddress;
    private String password;
    private Set<ExamEntity> exams;

    @Override
    public int compareTo(UserView o) {
        return userName.compareTo(o.userName);
    }
}
