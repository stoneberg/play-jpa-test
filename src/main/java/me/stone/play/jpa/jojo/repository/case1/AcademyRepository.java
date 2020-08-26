package me.stone.play.jpa.jojo.repository.case1;

import me.stone.play.jpa.jojo.domain.case1.Academy;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AcademyRepository extends JpaRepository<Academy, Long> {

    @EntityGraph(attributePaths = "subjects")
    @Query("select a from Academy a")
    List<Academy> findAllByGraph();

    @EntityGraph(attributePaths = {"subjects", "subjects.teacher"})
    @Query("select a from Academy a")
    List<Academy> findAllWithTeacherByGraph();

    @EntityGraph(attributePaths = {"subjects", "subjects.teacher"})
    @Query("select DISTINCT a from Academy a")
    List<Academy> findAllWithTeacherByGraphDistinct();
}
