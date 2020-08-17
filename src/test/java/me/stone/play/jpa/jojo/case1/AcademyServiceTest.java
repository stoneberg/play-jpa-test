package me.stone.play.jpa.jojo.case1;

import me.stone.play.jpa.jojo.domain.case1.Academy;
import me.stone.play.jpa.jojo.domain.case1.Subject;
import me.stone.play.jpa.jojo.domain.case1.Teacher;
import me.stone.play.jpa.jojo.service.case1.AcademyService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AcademyServiceTest {

    private static final Logger log = LoggerFactory.getLogger(AcademyServiceTest.class);

    @Autowired
    private AcademyService academyService;

    @BeforeEach
    void setup() {
        log.info("@BeforeEach - executes before each test method in this class");
        List<Academy> academies = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            Academy academy = Academy.builder().name("자바 학원 ::: " + i).build();
            Teacher teacher = Teacher.builder().name("강사 ::: " + i).build();
            // Teacher teacher100 = Teacher.builder().name("강사 ::: 100").build();
            Subject subject = Subject.builder().name("자바 개발 ::: " + i).build();

            // subject.setTeacher(teacher);
            // 하위 set 유틸리티 함수로 참조를 지정할 경우 insert가 수행된다.
            // 루프속에서 같은 엔티티가 계속 참조 되므로 똑같은 데이터가 중복 삽입되는 오류가 발생한다.
            // subject.setTeacher(teacher100);

            // 상위 add 유틸리티 함수로 참조를 지정할 경우 insert가 수행되지만 한번에 수행된다.
            // 특별한 경우가 아니면 저장은 한방향으로만 진행하는 것이 좋다.
            //teacher.addSubject(subject);
            //academy.addSubject(subject); // 저장 순서가 academy -> teacher -> subject

            /**
             * 연관 관계 owner에서 모든 관계를 저장 처리
             */
            subject.setTeacher(teacher);
            subject.setAcademy(academy);

            academies.add(academy);
            log.info(">>after save=====>{}/{}", i, academies);
        }

        academyService.saveAcademies(academies); // academy 가 subject를 저장하면서 cascade가 작동해 teacher를 저장한다?
        log.info(">>after save=====>{}", academies);
    }

    @AfterEach
    void destroy() {
        log.info("@AfterEach - executes after each test method in this class");
        // academyService.deleteAll();
    }

    @Test
    @DisplayName("Academy여러개를_조회시_Subject가_N+1_쿼리가발생한다")
    void run_N_plus_1_test() throws Exception {
        //given
        List<String> subjectNames = academyService.findAllSubjectNames();

        //then
        assertThat(subjectNames.size()).isEqualTo(10);
    }


}
