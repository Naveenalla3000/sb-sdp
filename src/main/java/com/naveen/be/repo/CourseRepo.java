package com.naveen.be.repo;

import com.naveen.be.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CourseRepo extends JpaRepository<Course, Serializable> {
}
