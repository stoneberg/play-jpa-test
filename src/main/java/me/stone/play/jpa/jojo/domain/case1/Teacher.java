package me.stone.play.jpa.jojo.domain.case1;

import javax.persistence.*;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
@Getter
// @Setter // mapstruct 사용 시 필요
@Entity
@Table(name = "jojo_teacher", indexes = { @Index(name = "IDX_TEACHER_NAME", unique = true, columnList = "name") })
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "teacher", fetch = FetchType.LAZY)
    private Subject subject;

    @Builder
    public Teacher(String name, Subject subject) {
        this.name = name;
        this.subject = subject;
    }
}
