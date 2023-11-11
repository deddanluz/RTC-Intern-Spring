/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ded_technologies.rtc.intern.spring.interfaces;

import java.util.List;
import ded_technologies.rtc.intern.spring.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Даниил
 */
@Repository
public interface StudentCrudRepository extends JpaRepository<Student, Integer>{
    
    @Query("SELECT students.id, students.family, students.name, students.age, groups.id, groups.number, subjects.id, subjects.subject, performance.id, performance.grade " +
                        "FROM intern.students " +
                        "INNER JOIN intern.groups ON students.group_id = groups.id " +
                        "LEFT JOIN intern.performance ON students.id = performance.student_id " +
                        "LEFT JOIN intern.subjects ON performance.subject_id = subjects.id " +
                        "WHERE groups.number = number")
    List<Student> findStudentsByGroupNumber(@Param("number") int number);
    
    @Query("SELECT students.id, students.family, students.name, students.age, groups.id, groups.number, subjects.id, subjects.subject, performance.id, performance.grade " +
                        "FROM intern.students " +
                        "INNER JOIN intern.groups ON students.group_id = groups.id " +
                        "LEFT JOIN intern.performance ON students.id = performance.student_id " +
                        "LEFT JOIN intern.subjects ON performance.subject_id = subjects.id " +
                        "WHERE students.age = age")
    List<Student> findStudentsByAge(@Param("age") int age);
    
    @Query("SELECT students.id, students.family, students.name, students.age, groups.id, groups.number, subjects.id, subjects.subject, performance.id, performance.grade " +
                        "FROM intern.students " +
                        "INNER JOIN intern.groups ON students.group_id = groups.id " +
                        "LEFT JOIN intern.performance ON students.id = performance.student_id " +
                        "LEFT JOIN intern.subjects ON performance.subject_id = subjects.id " +
                        "WHERE students.family = family")
    List<Student> findStudentsByFamily(@Param("family") String family);
}
