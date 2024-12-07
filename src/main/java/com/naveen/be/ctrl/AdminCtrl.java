package com.naveen.be.ctrl;

import com.naveen.be.model.Course;
import com.naveen.be.model.ErpUser;
import com.naveen.be.srv.AdminSrv;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminCtrl {
    private final AdminSrv adminSrv;

    @GetMapping("/allUsers")
    public ResponseEntity<List<ErpUser>> getAllUsers() {
        return ResponseEntity.of(Optional.ofNullable(adminSrv.viewAllUsers()));
    }

    @GetMapping("/allFaculty")
    public ResponseEntity<List<ErpUser>> getAllFaculty() {
        return ResponseEntity.of(Optional.ofNullable(adminSrv.viewAllFaculty()));
    }

    @GetMapping("/allStudents")
    public ResponseEntity<List<ErpUser>> getAllStudents() {
        return ResponseEntity.of(Optional.ofNullable(adminSrv.viewAllStudents()));
    }

    @PostMapping("/createCourse")
    public ResponseEntity<Course> registerCourse(@RequestBody Course course) {
        return ResponseEntity.of(Optional.ofNullable(adminSrv.saveCourse(course)));
    }

    @PostMapping("/assignCourseToFaculty")
    public ResponseEntity<String> assignCourseToFaculty(
            @RequestParam Serializable facultyId,
            @RequestParam Serializable courseId) {
        adminSrv.assignCourseToFaculty(facultyId, courseId);
        return ResponseEntity.ok("Course assigned to faculty successfully.");
    }

    @PostMapping("/assignCourseToStudent")
    public ResponseEntity<String> assignCourseToStudent(
            @RequestParam Serializable studentId,
            @RequestParam Serializable courseId) {
        adminSrv.assignCourseToStudent(studentId, courseId);
        return ResponseEntity.ok("Course assigned to student successfully.");
    }

    @DeleteMapping("/deleteCourse/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Serializable courseId) {
        adminSrv.deleteCourse(courseId);
        return ResponseEntity.ok("Course deleted successfully.");
    }

    @PostMapping("/deregisterCourseFromStudent")
    public ResponseEntity<String> deRegisterCourseFromStudent(
            @RequestParam Serializable courseId,
            @RequestParam Serializable studentId) {
        adminSrv.deRegisterCourseFromStudent(courseId, studentId);
        return ResponseEntity.ok("Course deregistered from student successfully.");
    }

    @PostMapping("/deregisterCourseFromFaculty")
    public ResponseEntity<String> deRegisterCourseFromFaculty(
            @RequestParam Serializable courseId,
            @RequestParam Serializable facultyId) {
        adminSrv.deRegisterCourseFromFaculty(courseId, facultyId);
        return ResponseEntity.ok("Course deregistered from faculty successfully.");
    }

    @PutMapping("/changeRole/{userId}")
    public ResponseEntity<String> changeRole(@PathVariable Serializable userId) {
        adminSrv.changeRole(userId);
        return ResponseEntity.ok("User role changed successfully.");
    }
    @GetMapping("/allCourses")
    public ResponseEntity<List<Course>> getAllCourse(){
        return ResponseEntity.ofNullable(adminSrv.viewAllCourses());
    }
}
