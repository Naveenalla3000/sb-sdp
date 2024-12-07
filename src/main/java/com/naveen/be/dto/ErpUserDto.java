package com.naveen.be.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ErpUserDto {
    private Serializable id;
    private String name;
    private String email;
    private String role; // Include role for filtering (e.g., STUDENT, FACULTY)
    private List<Serializable> courseIds; // Only include IDs to avoid circular serialization

}
