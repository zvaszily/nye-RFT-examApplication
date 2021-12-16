package hu.nye.nyeRFTexamApplication.data.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * * Represents a exam in the database. Contains the exams' id, name, date.
 */
@Entity
@Table( name = "exams" )
@NoArgsConstructor
@Data
public class ExamEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "subject", nullable = false )
    private String subject;

    private String title;

    @Column(name = "exam_date", nullable = false )
    private String examDate;

    @Column(name = "exam_location", nullable = false )
    private String examLocation;
    @ManyToMany(mappedBy = "exams")
   // private Set<UserEntity> user = new HashSet<UserEntity>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getExamLocation() {
        return examLocation;
    }

    public void setExamLocation(String examLocation) {
        this.examLocation = examLocation;
    }
/*
    public Set<UserEntity> getUser() {
        return user;
    }

    public void setUser(Set<UserEntity> user) {
        this.user = user;
    }
*/

}
