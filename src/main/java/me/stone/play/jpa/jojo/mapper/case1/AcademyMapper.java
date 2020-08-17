package me.stone.play.jpa.jojo.mapper.case1;

import me.stone.play.jpa.config.mapstruct.MapStructMapperConfig;
import me.stone.play.jpa.jojo.domain.case1.Academy;
import me.stone.play.jpa.jojo.domain.case1.Subject;
import me.stone.play.jpa.jojo.domain.case1.Teacher;
import me.stone.play.jpa.jojo.payload.case1.AcademyRes.AcademyDto;
import me.stone.play.jpa.jojo.payload.case1.AcademyRes.SubjectDto;
import me.stone.play.jpa.jojo.payload.case1.AcademyRes.TeacherDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(config = MapStructMapperConfig.class)
public interface AcademyMapper {

    AcademyDto toAcademyDto(Academy academy);
    List<AcademyDto> toAcademyDtoList(List<Academy> academies);

    TeacherDto toTeacherDto(Teacher tacher);
    List<TeacherDto> toTeacherDtoList(List<Teacher> teachers);

    SubjectDto toSubjectDto(Subject subject);
    List<SubjectDto> toSubjectDtoList(List<Subject> subjects);

}
