/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ded_technologies.rtc.intern.spring.services;

import ded_technologies.rtc.intern.spring.models.Performance;
import jakarta.transaction.Transactional;
import java.util.List;
import ded_technologies.rtc.intern.spring.models.Student;
import ded_technologies.rtc.intern.spring.models.Subject;
import ded_technologies.rtc.intern.spring.objects.StudentWithAverageGrade;
import ded_technologies.rtc.intern.spring.repositories.PerformanceRepository;
import ded_technologies.rtc.intern.spring.repositories.StudentRepository;
import ded_technologies.rtc.intern.spring.repositories.SubjectRepository;
import java.util.ArrayList;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author Даниил
 */
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final PerformanceRepository performanceRepository;

    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository,
            PerformanceRepository performanceRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.performanceRepository = performanceRepository;
    }
    
    @Transactional
    public List<StudentWithAverageGrade> getStudentsByGrupNumber(int groupNumber) {
        //ответ
	List<StudentWithAverageGrade> response = new ArrayList<>();
        //получаем всех студентов по номеру группы
        List<Student> students = studentRepository.findByGroup_Number(groupNumber);
        //получаем оценки
        List<Performance> grades = performanceRepository.findByStudent_Group_NumberOrderByStudent_Family(groupNumber);
        //по каждому студенту получаем массив оценок, среднюю оценку, создаем объект для листа ответа и записываем
        students.forEach(s->{
            double sum=0, loop=0;
            for (Performance grade : grades){
                if (grade.getStudent().equals(s)){
                    sum=sum+grade.getGrade();
                    loop++;
                }
            }
            StudentWithAverageGrade swag = new StudentWithAverageGrade();
            BeanUtils.copyProperties(s,swag);
            swag.setAverageGrade(sum/loop);
            response.add(swag);
        });
        return response;
    }
    
    @Transactional
    public List<Student> putGradeByStudentAndGroupAndSubject(String family, String name, int groupNumber, String subject, int grade){
        //ответ
        List<Student> students = new ArrayList<>();
        List<Subject> subjects = subjectRepository.findBySubject(subject);
        //получаем учеников
        students.addAll(studentRepository.findByFamilyAndNameAndGroup_Number(family, name, groupNumber));
        //получаем оценки
        List<Performance> grades = performanceRepository.findByStudentInAndSubjectInOrderByStudent_Family(students, subjects);
        grades.forEach(s->{
            //изменяем оценки
            s.setGrade(grade);
        });
        return students;
    }
}
