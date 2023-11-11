/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ded_technologies.rtc.intern.spring.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Даниил
 */
@Entity
@Table(name = "groups_subjects", schema = "intern")
@Setter
@Getter
public class GroupSubject {
    @EmbeddedId
    private GroupSubjectId id;

    @ManyToOne
    @MapsId("group_id")
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @MapsId("subject_id")
    @JoinColumn(name = "subject_id")
    private Subject subject;
}