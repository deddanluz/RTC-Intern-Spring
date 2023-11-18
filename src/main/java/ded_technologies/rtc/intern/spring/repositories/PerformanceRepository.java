/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ded_technologies.rtc.intern.spring.repositories;

import ded_technologies.rtc.intern.spring.models.Performance;
import ded_technologies.rtc.intern.spring.models.StudentWithAverageGrade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

/**
 *
 * @author Даниил
 */
@Component
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    @Query("SELECT new ded_technologies.rtc.intern.spring.models.StudentWithAverageGrade(p.student, AVG(p.grade)) FROM Performance p WHERE p.student.group.number = :groupNumber GROUP BY p.student")
    List<StudentWithAverageGrade> getAverageGradeByGroup_Number(@Param("groupNumber") Integer groupNumber);
    
    @Query("SELECT p FROM Performance p WHERE p.student.id = :studentId AND p.subject.id = :subjectId")
    Performance getGradeByStudentIdAndSubjectId(@Param("studentId") Integer studentId, @Param("subjectId") Integer subjectId);
}
