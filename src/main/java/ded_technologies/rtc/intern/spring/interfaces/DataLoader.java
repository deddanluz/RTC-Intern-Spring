/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ded_technologies.rtc.intern.spring.interfaces;

import java.io.IOException;
import ded_technologies.rtc.intern.spring.models.Student;

/**
 *
 * @author Даниил
 */
public interface DataLoader {
    public String[] getHeaders() throws IOException;
    public Student[] getStudents() throws IOException;
}
