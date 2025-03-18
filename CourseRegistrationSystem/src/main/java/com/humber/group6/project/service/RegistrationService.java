package com.humber.group6.project.service;

import com.humber.group6.project.entity.Registration;
import com.humber.group6.project.entity.Student;
import com.humber.group6.project.entity.Course;
import com.humber.group6.project.repository.RegistrationRepository;
import com.humber.group6.project.repository.StudentRepository;
import com.humber.group6.project.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    // ✅ Get all registrations
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    
    // ✅ Register a student for a course
    public void registerStudentForCourse(Long studentId, Long courseId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (studentOpt.isPresent() && courseOpt.isPresent()) {
            Student student = studentOpt.get();
            Course course = courseOpt.get();

            // Prevent duplicate registrations
            if (registrationRepository.existsByStudentAndCourse(student, course)) {
                throw new IllegalStateException("Student is already registered for this course.");
            }

            // Ensure the course has available seats
            if (course.getAvailableSeats() <= 0) {
                throw new IllegalStateException("No available seats in this course.");
            }

            // Register the student
            Registration registration = new Registration();
            registration.setStudent(student);
            registration.setCourse(course);
            registrationRepository.save(registration);

            // Decrease available seats
            course.setAvailableSeats(course.getAvailableSeats() - 1);
            courseRepository.save(course);
        }
    }

    public List<Registration> getRegistrationsByStudent(Long studentId) {
        return registrationRepository.findByStudentId(studentId);
    }

}
