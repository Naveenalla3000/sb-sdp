package com.naveen.be.srv;

import com.naveen.be.model.Course;
import com.naveen.be.model.ErpUser;
import com.naveen.be.model.Role;
import com.naveen.be.repo.CourseRepo;
import com.naveen.be.repo.ErpUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminSrv {
    private final CourseRepo courseRepo;
    private final ErpUserRepo erpUserRepo;
    private final StudentSrv studentSrv;
    public Course saveCourse(Course course) {
        return courseRepo.save(course);
    }
    public void assignCourseToFaculty(Serializable facultyId, Serializable courseId){
       Course course = courseRepo.findById(courseId).get();
       ErpUser faculty = erpUserRepo.findById(facultyId).get();
       course.setFaculty(faculty);
       List<Course> facultyCourse = faculty.getCourses();
       facultyCourse.add(course);
       erpUserRepo.save(faculty);
       courseRepo.save(course);
    }
    public void assignCourseToStudent(Serializable studentId, Serializable courseId) {
        studentSrv.registerCourse(courseId,studentId);

    }
    public void deleteCourse(Serializable courseId) {
        Course course = courseRepo.findById(courseId).get();
        if (course.getFaculty() != null) {
            ErpUser faculty = course.getFaculty();
            faculty.getCourses().remove(course);
            erpUserRepo.save(faculty);
        }
        for (ErpUser student : course.getStudents()) {
            student.getCourses().remove(course);
            erpUserRepo.save(student);
        }
        courseRepo.delete(course);
    }
    public void deRegisterCourseFromStudent(Serializable courseId, Serializable studentId){
        Course course = courseRepo.findById(courseId).get();
        ErpUser student = erpUserRepo.findById(studentId).get();
        List<ErpUser> courseStudents = course.getStudents();
        courseStudents.remove(student);
        List<Course> studentCourses = student.getCourses();
        studentCourses.remove(course);
        courseRepo.save(course);
        erpUserRepo.save(student);
    }
    public void deRegisterCourseFromFaculty(Serializable courseId, Serializable facultyId){
        Course course = courseRepo.findById(courseId).get();
        ErpUser faculty = erpUserRepo.findById(facultyId).get();
        course.setFaculty(null);
        List<Course> facultyCourse = faculty.getCourses();
        facultyCourse.remove(course);
        courseRepo.save(course);
        erpUserRepo.save(faculty);
    }
    public List<ErpUser> viewAllStudents() {
        return erpUserRepo.findAll().stream()
                .filter(user -> user.getRole() == Role.STUDENT)
                .collect(Collectors.toList());
    }
    public List<ErpUser> viewAllFaculty() {
        return erpUserRepo.findAll().stream()
                .filter(user -> user.getRole() == Role.FACULTY)
                .collect(Collectors.toList());
    }
    public void changeRole(Serializable userId) {
        ErpUser user = erpUserRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(user.getRole()==Role.STUDENT?Role.FACULTY:Role.STUDENT);
        erpUserRepo.save(user);
    }
    public List<ErpUser> viewAllUsers() {
        return erpUserRepo.findAll();
    }
    public List<Course> viewAllCourses(){
        return courseRepo.findAll();
    }
}