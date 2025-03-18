package com.humber.group6.project.repository;



import com.humber.group6.project.entity.Course;
import com.humber.group6.project.entity.Registration;
import com.humber.group6.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByStudentAndCourse(Student student, Course course);

    List<Registration> findByStudentId(Long studentId);
}
