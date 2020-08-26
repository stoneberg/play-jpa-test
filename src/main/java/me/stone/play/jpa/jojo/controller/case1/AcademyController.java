package me.stone.play.jpa.jojo.controller.case1;

import lombok.RequiredArgsConstructor;
import me.stone.play.jpa.jojo.service.case1.AcademyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/jojo")
@RestController
public class AcademyController {

    private final AcademyService academyService;

    @GetMapping("academies")
    public ResponseEntity<?> getAcademyList() {
        return new ResponseEntity<>(academyService.findAcademyList(), HttpStatus.OK);
    }

    /**
     * JoinFetch는 Inner Join, Entity Graph는 Outer Join이라는 차이점
     */


    @GetMapping("academies/fetch")
    public ResponseEntity<?> getAcademyByFetch() {
        return new ResponseEntity<>(academyService.findAcademyListByFetch(), HttpStatus.OK);
    }

    @GetMapping("academies/graph")
    public ResponseEntity<?> getAcademyByGraph() {
        return new ResponseEntity<>(academyService.findAcademyListByGraph(), HttpStatus.OK);
    }

    @GetMapping("academies/graph/teacher")
    public ResponseEntity<?> getAcademyWithTeacherByGraph() {
        return new ResponseEntity<>(academyService.findAllWithTeacherByGraph(), HttpStatus.OK);
    }

    @GetMapping("teachers")
    public ResponseEntity<?> getTeacherList() {
        return new ResponseEntity<>(academyService.findTeacherList(), HttpStatus.OK);
    }

    @GetMapping("subjects")
    public ResponseEntity<?> getSubjectList() {
        return new ResponseEntity<>(academyService.findSubjectList(), HttpStatus.OK);
    }

    @GetMapping("subjects/add/{count}")
    public ResponseEntity<?> addSubjects(@PathVariable int count) {
        academyService.addSubjects(count);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("academies/graph/distinct")
    public ResponseEntity<?> getAcademyByGraphDistinct() {
        return new ResponseEntity<>(academyService.findAcademyListByGraphDistinct(), HttpStatus.OK);
    }

}
