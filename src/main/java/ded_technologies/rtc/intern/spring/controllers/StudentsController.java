/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ded_technologies.rtc.intern.spring.controllers;

import ded_technologies.rtc.intern.spring.objects.StudentWithAverageGrade;
import ded_technologies.rtc.intern.spring.objects.UpdateGradeRequest;
import ded_technologies.rtc.intern.spring.services.StudentService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Даниил
 */
@RestController
@RequestMapping("/students")
public class StudentsController {
    private final StudentService studentService;

    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("groups/{groupNumber}/average_grades")
    public List<StudentWithAverageGrade> getStudentsByGroupNumber(@PathVariable Integer groupNumber) {
        return studentService.getStudentsByGroupNumber(groupNumber);
    }
    
    @PutMapping("{studentId}/subject/{subjectId}")
    public ded_technologies.rtc.intern.spring.objects.Student putGradeByStudentAndSubject(@PathVariable Integer studentId,
            @PathVariable Integer subjectId, @RequestBody UpdateGradeRequest request) {
        return studentService.putGradeByStudentAndSubject(studentId, subjectId, request.getGrade());
    }
}
