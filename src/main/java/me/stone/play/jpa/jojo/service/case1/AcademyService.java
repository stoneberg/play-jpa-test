package me.stone.play.jpa.jojo.service.case1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.stone.play.jpa.jojo.domain.case1.Academy;
import me.stone.play.jpa.jojo.domain.case1.Subject;
import me.stone.play.jpa.jojo.mapper.case1.AcademyMapper;
import me.stone.play.jpa.jojo.payload.case1.AcademyRes;
import me.stone.play.jpa.jojo.payload.case1.AcademyRes.SubjectDto;
import me.stone.play.jpa.jojo.repository.case1.AcademyQuerydslRepository;
import me.stone.play.jpa.jojo.repository.case1.AcademyRepository;
import me.stone.play.jpa.jojo.repository.case1.SubjectRepository;
import me.stone.play.jpa.jojo.repository.case1.TeacherRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static me.stone.play.jpa.jojo.payload.case1.AcademyRes.*;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AcademyService {

    private final AcademyRepository academyRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final AcademyQuerydslRepository academyQuerydslRepository;
    private final AcademyMapper academyMapper;

    @Transactional
    public void saveAcademies(List<Academy> academies) {
        log.info("saveAll.academies================>{}", academies);
        academyRepository.saveAll(academies);
    }

    public List<String> findAllSubjectNames() {
        return extractSubjectNames(academyRepository.findAll());
    }

    private List<String> extractSubjectNames(List<Academy> academies) {
        log.info(">>>>>>>>[모든 과목을 추출한다]<<<<<<<<<");
        log.info("Academy academies : {}", academies);
        log.info("Academy Size : {}", academies.size());
        return academies.stream()
                .map(a -> a.getSubjects().get(0).getName())
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAll() {
        academyRepository.deleteAll();
    }

    /**
     * test to controller
     * mapstruct
     */
    public List<AcademyDto> findAcademyList() {
        List<Academy> academies = academyRepository.findAll();
        log.info("◆◆◆academies.size========>{}", academies.size());
        return academyMapper.toAcademyDtoList(academies);
    }

    public List<AcademyDto> findAcademyListByFetch() {
        List<Academy> academies = academyQuerydslRepository.selectAcademyList();
        log.info("◆◆◆academies.size========>{}", academies.size());
        return academyMapper.toAcademyDtoList(academies);
    }

    /**
     * Enttity Graph 조회
     */
    public List<AcademyDto> findAcademyListByGraph() {
        List<Academy> academies = academyRepository.findAllByGraph();
        log.info("◆◆◆academies.size========>{}", academies.size());
        return academyMapper.toAcademyDtoList(academies);
    }

    public List<AcademyDto> findAllWithTeacherByGraph() {
        List<Academy> academies = academyRepository.findAllWithTeacherByGraph();
        log.info("◆◆◆academies.size========>{}", academies.size());
        return academyMapper.toAcademyDtoList(academies);
    }

    @Transactional
    public void addSubjects(int count) {
        List<Academy> academies = academyRepository.findAll();
        List<Subject> subjects = new ArrayList<>();
        for (Academy academy : academies) {
            for (int i = 0; i < count; i++) {
                // count가 2일 경우 Subject 객체의 name이 0, 1이 반복되는 객체가 academy list 사이즈 만큼 반복된다.
                // name에 유닉크 제약 조건이 걸려 있으므로 에러 발생
                Subject subject = Subject.builder()
                        .name("King God Academy " + RandomStringUtils.randomAlphabetic(5))
                        .academy(academy)
                        .build();
                subjects.add(subject);
            }
        }
        subjectRepository.saveAll(subjects);
    }

    public List<SubjectDto> findSubjectList() {
        return academyMapper.toSubjectDtoList(subjectRepository.findAll());
    }

    public List<TeacherDto> findTeacherList() {
        return academyMapper.toTeacherDtoList(teacherRepository.findAll());
    }

    public List<AcademyDto> findAcademyListByGraphDistinct() {
        List<Academy> academies = academyRepository.findAllWithTeacherByGraphDistinct();
        log.info("◆◆◆academies.size========>{}", academies.size());
        return academyMapper.toAcademyDtoList(academies);
    }
}
