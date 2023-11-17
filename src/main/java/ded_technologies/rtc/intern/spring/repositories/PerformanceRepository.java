/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ded_technologies.rtc.intern.spring.repositories;

import ded_technologies.rtc.intern.spring.models.Performance;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author Даниил
 */
@Component
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    List<Performance> findByStudent_Group_NumberOrderByStudent_Family(Integer groupNumber);
    List<Performance> findByStudentInAndSubjectInOrderByStudent_Family(Iterable students, Iterable subjects);
}
