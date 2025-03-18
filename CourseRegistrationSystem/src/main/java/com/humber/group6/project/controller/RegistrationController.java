package com.humber.group6.project.controller;

import com.humber.group6.project.entity.Course;
import com.humber.group6.project.entity.Registration;
import com.humber.group6.project.entity.Student;
import com.humber.group6.project.service.CourseService;
import com.humber.group6.project.service.RegistrationService;
import com.humber.group6.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/registrations")
    public String listRegistrations(Model model) {
        // ✅ Fetch the first student from the database
        Student student = studentService.getFirstStudent();

        if (student == null) {
            throw new IllegalStateException("No students found in the database.");
        }

        model.addAttribute("student", student);
        model.addAttribute("registrations", registrationService.getRegistrationsByStudent(student.getId()));
        return "registration";
    }


    @PostMapping("/register-course")
    public String registerCourse(@RequestParam Long courseId, Principal principal) {
        Student student = studentService.getFirstStudent();
        if (student == null) {
            return "redirect:/login";
        }

        registrationService.registerStudentForCourse(student.getId(), courseId);

        return "redirect:/registrations";
    }
}
