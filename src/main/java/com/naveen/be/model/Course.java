package com.naveen.be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private int creditHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    @JsonIgnore // Prevent circular serialization
    private ErpUser faculty;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    @JsonIgnore // Prevent circular serialization
    private List<ErpUser> students;
}
