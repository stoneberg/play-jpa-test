package me.stone.play.jpa.jojo.payload.case1;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * parent 또는 child에서 순환 참조가 발생하지 않도록
 * 연관관계를 끊어야 한다.
 */
public class AcademyRes {

    @NoArgsConstructor
    @Data
    public static class AcademyDto {
        private Long id;
        private String name;
        private List<SubjectDto> subjects;

        @NoArgsConstructor
        @Data
        public static class SubjectDto {
            private Long id;
            private String name;
        }
    }

    @NoArgsConstructor
    @Data
    public static class TeacherDto {
        private Long id;
        private String name;
        private SubjectDto subject;

        @NoArgsConstructor
        @Data
        public static class SubjectDto {
            private Long id;
            private String name;
        }
    }

    @NoArgsConstructor
    @Data
    public static class SubjectDto {
        private Long id;
        private String name;
        private AcademyDto academy;
        private TeacherDto teacher;

        @NoArgsConstructor
        @Data
        public static class AcademyDto {
            private Long id;
            private String name;
        }

        @NoArgsConstructor
        @Data
        public static class TeacherDto {
            private Long id;
            private String name;
        }
    }

}
