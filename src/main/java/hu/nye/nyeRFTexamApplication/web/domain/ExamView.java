package hu.nye.nyeRFTexamApplication.web.domain;

import lombok.Builder;
import lombok.Data;

/**
 * Web layer representation of a exam.
 */
@Data
@Builder
public class ExamView implements  Comparable<ExamView>{

    private Long id;
    private String subject;
    private String title;
    private String examDate;
    private String examLocation;

    @Override
    public int compareTo(ExamView o) {
        return subject.compareTo(o.subject);
    }
}
