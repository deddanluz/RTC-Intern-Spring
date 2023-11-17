/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ded_technologies.rtc.intern.spring.repositories;

import ded_technologies.rtc.intern.spring.models.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author Даниил
 */
@Component
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByGroup_Number(Integer groupNumber);
    
    List<Student> findByFamilyAndNameAndGroup_Number(String family, String name, Integer groupNumber);
}
