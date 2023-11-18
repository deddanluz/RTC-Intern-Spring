/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ded_technologies.rtc.intern.spring.objects;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Даниил
 */
@Setter
@Getter
public class Student {
    private Long id;

    private String family;

    private String name;

    private Integer age;

    private Group group;
}
