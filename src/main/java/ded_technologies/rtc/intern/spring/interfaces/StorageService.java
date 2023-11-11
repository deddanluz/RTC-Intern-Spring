/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ded_technologies.rtc.intern.spring.interfaces;

import java.util.List;
import ded_technologies.rtc.intern.spring.models.Student;

/**
 *
 * @author Даниил
 * @param <T>
 */
public interface StorageService <T> {
    void add(DataLoader dl);
    
    List<Student> list(T t);
}
