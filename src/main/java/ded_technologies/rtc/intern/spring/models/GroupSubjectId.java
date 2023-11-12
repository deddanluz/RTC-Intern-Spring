/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ded_technologies.rtc.intern.spring.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Даниил
 */
@Embeddable
@Setter
@Getter
public class GroupSubjectId implements Serializable {
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "subject_id")
    private Long subjectId;
}
