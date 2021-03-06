package me.stone.play.jpa.jojo.domain.case1;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, exclude = { "academy", "teacher" })
@ToString(exclude = { "academy", "teacher" })
@Getter
@Entity
@Table(name = "jojo_subject", indexes = { @Index(name = "IDX_SUBJECT_NAME", unique = true, columnList = "name") })
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id", foreignKey = @ForeignKey(name = "FK_SUBJECT_ACADEMY"))
    private Academy academy;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", foreignKey = @ForeignKey(name = "FK_SUBJECT_TEACHER"))
    private Teacher teacher;

    @Builder
    public Subject(String name, Academy academy, Teacher teacher) {
        super();
        this.name = name;
        this.academy = academy;
        this.teacher = teacher;
    }

    // utility method
    public void setAcademy(Academy academy) {
        this.academy = academy;
        if (!academy.getSubjects().contains(this)) {
            academy.getSubjects().add(this);
        }
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

}
