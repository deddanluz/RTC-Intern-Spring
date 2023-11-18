/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ded_technologies.rtc.intern.spring.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


/**
 *
 * @author Даниил
 */
@Mapper(componentModel = "spring")
public interface StudentModelToObject {
    public ded_technologies.rtc.intern.spring.objects.Student modelToObject(ded_technologies.rtc.intern.spring.models.Student model);
        
    @Mapping(source = "model", target = "student")
    @Mapping(source = "averageGrade", target = "averageGrade")
    public ded_technologies.rtc.intern.spring.objects.StudentWithAverageGrade modelToObjectWithAverageGrade(ded_technologies.rtc.intern.spring.models.Student model, Double averageGrade);
}
