/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ded_technologies.rtc.intern.spring.mappers;

import org.mapstruct.Mapper;

/**
 *
 * @author Даниил
 */
@Mapper(componentModel = "spring")
public interface ModelWithAverageGradeToObjectWithAverageGrade {
    public ded_technologies.rtc.intern.spring.objects.StudentWithAverageGrade modelToObject(ded_technologies.rtc.intern.spring.models.StudentWithAverageGrade model);
}
