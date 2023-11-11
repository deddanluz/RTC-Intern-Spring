/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ded_technologies.rtc.intern.spring.services;

import ded_technologies.rtc.intern.spring.models.Group;
import ded_technologies.rtc.intern.spring.models.Performance;
import jakarta.transaction.Transactional;
import java.util.List;
import ded_technologies.rtc.intern.spring.models.Student;
import ded_technologies.rtc.intern.spring.models.Subject;
import ded_technologies.rtc.intern.spring.objects.StudentWithAverageGrade;
import ded_technologies.rtc.intern.spring.objects.StudentsAverageGradeResponse;
import ded_technologies.rtc.intern.spring.repositories.GroupRepository;
import ded_technologies.rtc.intern.spring.repositories.PerformanceRepository;
import ded_technologies.rtc.intern.spring.repositories.StudentRepository;
import ded_technologies.rtc.intern.spring.repositories.SubjectRepository;
import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author Даниил
 */
@Service
public class StudentService {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final PerformanceRepository performanceRepository;

    public StudentService(GroupRepository groupRepository, StudentRepository studentRepository,
            SubjectRepository subjectRepository, PerformanceRepository performanceRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.performanceRepository = performanceRepository;
    }
    
    @Transactional
    public List<StudentWithAverageGrade> getStudentsByGrupNumber(int groupNumber) {
	List<StudentWithAverageGrade> response = new ArrayList<>();
        List<Student> students = studentRepository.findByGroup_Number(groupNumber);
        Iterator<Performance> grades = performanceRepository.findByStudent_Group_Number(groupNumber).iterator();
        for (Student student : students){
            double sum=0, loop=0;
            while(grades.hasNext()){
                Performance grade = grades.next();
                if (grade.getStudent().equals(student)){
                    sum=sum+grade.getGrade();
                    loop++;
                }
            }
            StudentWithAverageGrade swag = new StudentWithAverageGrade();
            BeanUtils.copyProperties(student,swag);
            swag.setAverageGrade(sum/loop);
            response.add(swag);
        }
        return response;
    }
    
    @Transactional
    public List<Student> putGradeByStudentAndGroupAndSubject(String family, String name, int groupNumber, String subject, int grade){
        List<Student> students = new ArrayList<>();
        Iterator<Group> groups = groupRepository.findByNumber(groupNumber).iterator();
        List<Subject> subjects = subjectRepository.findBySubject(subject);
        while(groups.hasNext()){
            //получаем учеников
            students.addAll(studentRepository.findByFamilyAndNameAndGroup(family, name, groups.next()));
            //получаем оценки
            Iterator<Performance> grades = performanceRepository.findByStudentInAndSubjectIn(students, subjects).iterator();
            while(grades.hasNext()){
                //изменяем оценки
                grades.next().setGrade(grade);
            }
        }
        return students;
    }
}
