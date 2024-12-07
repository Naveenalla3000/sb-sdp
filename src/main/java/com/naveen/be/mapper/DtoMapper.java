package com.naveen.be.mapper;

import com.naveen.be.dto.CourseDto;
import com.naveen.be.dto.ErpUserDto;
import com.naveen.be.model.Course;
import com.naveen.be.model.ErpUser;

import java.util.stream.Collectors;

public class DtoMapper {
    public static CourseDto toCourseDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setFacultyId(course.getFaculty() != null ? course.getFaculty().getId() : null);
        dto.setStudentIds(course.getStudents().stream()
                .map(ErpUser::getId)
                .collect(Collectors.toList()));
        return dto;
    }

    public static ErpUserDto toErpUserDto(ErpUser user) {
        ErpUserDto dto = new ErpUserDto();
        dto.setId(user.getId());
        dto.setName(user.getUsername());
        dto.setRole(user.getRole().name());
        dto.setCourseIds(user.getCourses().stream()
                .map(Course::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
