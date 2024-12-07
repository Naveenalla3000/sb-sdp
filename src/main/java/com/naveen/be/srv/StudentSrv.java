package com.naveen.be.srv;

import com.naveen.be.model.Course;
import com.naveen.be.model.ErpUser;
import com.naveen.be.repo.CourseRepo;
import com.naveen.be.repo.ErpUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentSrv {
    private final CourseRepo courseRepo;
    private final ErpUserRepo erpUserRepo;
    public void registerCourse(Serializable courseId, Serializable studentId){
        Course course = courseRepo.findById(courseId).get();
        ErpUser student = erpUserRepo.findById(studentId).get();
        List<ErpUser> courseStudents = course.getStudents();
        courseStudents.add(student);
        List<Course> studentCourses = student.getCourses();
        studentCourses.add(course);
        courseRepo.save(course);
        erpUserRepo.save(student);
    }
    public List<Course> getMyCourses(Serializable studentId){
        return erpUserRepo.findById(studentId).get().getCourses();
    }
    public List<ErpUser> getMyFaculty(Serializable studentId) {
        ErpUser student = erpUserRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return student.getCourses().stream()
                .map(Course::getFaculty) // Get the faculty for each course
                .distinct() // Remove duplicates
                .collect(Collectors.toList());
    }
}
