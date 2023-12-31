/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ded_technologies.rtc.intern.spring.repositories;

import ded_technologies.rtc.intern.spring.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author Даниил
 */
@Component
public interface GroupRepository extends JpaRepository<Group, Long> {
    
}

