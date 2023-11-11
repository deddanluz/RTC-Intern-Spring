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
import ded_technologies.rtc.intern.spring.objects.StudentsAverageGradeResponse;
import ded_technologies.rtc.intern.spring.repositories.GroupRepository;
import ded_technologies.rtc.intern.spring.repositories.PerformanceRepository;
import ded_technologies.rtc.intern.spring.repositories.StudentRepository;
import ded_technologies.rtc.intern.spring.repositories.SubjectRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
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
    public List<StudentsAverageGradeResponse> getStudentsByGrupNumber(int groupNumber) {
	List<StudentsAverageGradeResponse> response = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        Iterator<Group> groups = groupRepository.findByNumber(groupNumber).iterator();
        while(groups.hasNext()){
            students.addAll(studentRepository.findByGroup(groups.next()));
            List<Performance> grades = performanceRepository.findByStudentIn(students);
            for (Student student : students){
                Iterator<Performance> iterator = grades.iterator();
                double sum=0, loop=0;
                while(iterator.hasNext()){
                    Performance grade = iterator.next();
                    if (grade.getStudent().equals(student)){
                        sum=sum+grade.getGrade();
                        loop++;
                    }
                }
                StudentsAverageGradeResponse sag = new StudentsAverageGradeResponse();
                sag.setStudent(student);
                sag.setAverageGrade(sum/loop);
                response.add(sag);
            }
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
