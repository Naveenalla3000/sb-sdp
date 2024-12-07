package com.naveen.be.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CourseDto {
    private Serializable id;
    private String name;
    private Serializable facultyId; // Only include the ID to avoid full faculty serialization
    private List<Serializable> studentIds;
}
