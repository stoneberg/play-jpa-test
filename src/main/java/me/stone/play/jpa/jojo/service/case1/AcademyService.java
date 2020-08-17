package me.stone.play.jpa.jojo.service.case1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.stone.play.jpa.jojo.domain.case1.Academy;
import me.stone.play.jpa.jojo.mapper.case1.AcademyMapper;
import me.stone.play.jpa.jojo.payload.case1.AcademyRes;
import me.stone.play.jpa.jojo.payload.case1.AcademyRes.SubjectDto;
import me.stone.play.jpa.jojo.repository.case1.AcademyRepository;
import me.stone.play.jpa.jojo.repository.case1.SubjectRepository;
import me.stone.play.jpa.jojo.repository.case1.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public List<SubjectDto> findSubjectList() {
        return academyMapper.toSubjectDtoList(subjectRepository.findAll());
    }

    public List<AcademyDto> findAcademyList() {
        return academyMapper.toAcademyDtoList(academyRepository.findAll());
    }

    public List<TeacherDto> findTeacherList() {
        return academyMapper.toTeacherDtoList(teacherRepository.findAll());
    }
}
