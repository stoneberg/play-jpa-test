package me.stone.play.jpa.jojo.repository.case1;


import me.stone.play.jpa.common.querydsl.Querydsl4RepositorySupport;
import me.stone.play.jpa.jojo.domain.case1.Academy;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.stone.play.jpa.jojo.domain.case1.QAcademy.academy;
import static me.stone.play.jpa.jojo.domain.case1.QSubject.subject;
import static me.stone.play.jpa.jojo.domain.case1.QTeacher.teacher;

@Repository
public class AcademyQuerydslRepository extends Querydsl4RepositorySupport {

    public AcademyQuerydslRepository() {
        super(Academy.class);
    }

    /**
     * collection 조회일 경우 카타시안 곱을 방지하기 위해 selectDistinct 사용
     * @return
     */
    public List<Academy> selectAcademyList() {
        return select(academy)
                .distinct()
                .from(academy)
                .leftJoin(academy.subjects, subject)
                .fetchJoin()
                .leftJoin(subject.teacher, teacher)
                .fetchJoin()
                .fetch();
    }

}
