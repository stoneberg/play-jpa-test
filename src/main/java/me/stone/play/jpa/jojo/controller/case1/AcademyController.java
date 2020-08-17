package me.stone.play.jpa.jojo.controller.case1;

import lombok.RequiredArgsConstructor;
import me.stone.play.jpa.jojo.service.case1.AcademyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("teachers")
    public ResponseEntity<?> getTeacherList() {
        return new ResponseEntity<>(academyService.findTeacherList(), HttpStatus.OK);
    }

    @GetMapping("subjects")
    public ResponseEntity<?> getSubjectList() {
        return new ResponseEntity<>(academyService.findSubjectList(), HttpStatus.OK);
    }
}
