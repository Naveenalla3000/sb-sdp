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
public class FacultySrv {
    private final ErpUserRepo erpUser;
    private final CourseRepo courseRepo;
    public List<Course> getAssignedCourses(Serializable facultyId){
        return erpUser.findById(facultyId).get().getCourses();
    }
    public List<ErpUser> getAssignedStudents(Serializable facultyId){
        List<Course> assignedCourses = getAssignedCourses(facultyId);
        return assignedCourses.stream()
                .flatMap(course -> course.getStudents().stream())
                .distinct() // Ensure no duplicate students are returned
                .collect(Collectors.toList());
    }
}
