/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ded_technologies.rtc.intern.spring.services;

import ded_technologies.rtc.intern.spring.mappers.ModelWithAverageGradeToObjectWithAverageGrade;
import ded_technologies.rtc.intern.spring.models.Performance;
import jakarta.transaction.Transactional;
import java.util.List;
import ded_technologies.rtc.intern.spring.repositories.PerformanceRepository;
import java.util.ArrayList;
import ded_technologies.rtc.intern.spring.mappers.StudentModelToObject;
import ded_technologies.rtc.intern.spring.objects.Student;
import ded_technologies.rtc.intern.spring.objects.StudentWithAverageGrade;
import org.springframework.stereotype.Service;

/**
 *
 * @author Даниил
 */
@Service
public class StudentService {
    private final PerformanceRepository performanceRepository;
    private final StudentModelToObject studentModelToObject;
    private final ModelWithAverageGradeToObjectWithAverageGrade modelWithAverageGradeToObjectWithAverageGrade;

    public StudentService(PerformanceRepository performanceRepository, StudentModelToObject studentModelToObject,
            ModelWithAverageGradeToObjectWithAverageGrade modelWithAverageGradeToObjectWithAverageGrade) {
        this.performanceRepository = performanceRepository;
        this.studentModelToObject = studentModelToObject;
        this.modelWithAverageGradeToObjectWithAverageGrade=modelWithAverageGradeToObjectWithAverageGrade;
    }
    
    @Transactional
    public List<StudentWithAverageGrade> getStudentsByGroupNumber(int groupNumber) {
        //ответ
        List<StudentWithAverageGrade> response = new ArrayList<>();
        //получаем средние оценки
        List<ded_technologies.rtc.intern.spring.models.StudentWithAverageGrade> averageGrades = performanceRepository.getAverageGradeByGroup_Number(groupNumber);
        for (int i=0; i<averageGrades.size(); i++){
            //перобразуем из модели в объекты
            response.add(modelWithAverageGradeToObjectWithAverageGrade.modelToObject(averageGrades.get(i)));
        }
        return response;
    }
    
    @Transactional
    public Student putGradeByStudentAndSubject(int student_id, int subject_id, int grade){
        Performance p = performanceRepository.getGradeByStudentIdAndSubjectId(student_id, subject_id);
        //изменяем оценку
        p.setGrade(grade);
        //отдаем объект ученика, у которго меняли оценку
        return studentModelToObject.modelToObject(p.getStudent());
    }
}
