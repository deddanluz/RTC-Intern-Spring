/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ded_technologies.rtc.intern.spring.models;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Даниил
 */
@Setter
@Getter
public class StudentWithAverageGrade {
    private ded_technologies.rtc.intern.spring.models.Student student;
    private double averageGrade;
    
    public StudentWithAverageGrade(ded_technologies.rtc.intern.spring.models.Student student, Double averageGrade){
        this.student=student;
        this.averageGrade=averageGrade;
    }
}
