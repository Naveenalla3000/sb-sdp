package com.naveen.be.ctrl;

import com.naveen.be.model.Course;
import com.naveen.be.model.ErpUser;
import com.naveen.be.srv.StudentSrv;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
@CrossOrigin("*")
public class StudentCtrl {
    private final StudentSrv studentSrv;

    // Endpoint to register a course for a student
    @PostMapping("/{studentId}/register-course/{courseId}")
    public ResponseEntity<String> registerCourse(@PathVariable Serializable studentId,
                                                 @PathVariable Serializable courseId) {
        try {
            studentSrv.registerCourse(courseId, studentId);
            return ResponseEntity.ok("Course registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint to get courses for a student
    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<Course>> getMyCourses(@PathVariable Serializable studentId) {
        try {
            List<Course> courses = studentSrv.getMyCourses(studentId);
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }
    }

    // Endpoint to get faculty members for the student's courses
    @GetMapping("/{studentId}/faculty")
    public ResponseEntity<List<ErpUser>> getMyFaculty(@PathVariable Serializable studentId) {
        try {
            List<ErpUser> faculty = studentSrv.getMyFaculty(studentId);
            return ResponseEntity.ok(faculty);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }
    }
}

