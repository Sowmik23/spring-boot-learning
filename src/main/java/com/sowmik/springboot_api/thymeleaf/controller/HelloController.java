package com.sowmik.springboot_api.thymeleaf.controller;

import com.sowmik.springboot_api.data_jpa.entities.Student;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello"; //here hello is hello.html file(html file name)
    }

    @RequestMapping("/sendData")
    public ModelAndView sendData(){
        ModelAndView mav = new ModelAndView("data");
        mav.addObject("message", "Take up one idea and make it your life");
        return mav;
    }

    //sending object data
    @RequestMapping("/getStudent")
    public ModelAndView getStudent(){
        ModelAndView mav = new ModelAndView("student");
        Student student = new Student();
        student.setName("Sowmik Sarker");
        student.setScore(100);
        mav.addObject("student", student);
        return mav;
    }

    //sending an array of object data
    @RequestMapping("/getStudents")
    public ModelAndView getStudents(){
        ModelAndView mav = new ModelAndView("studentList");

        Student student1 = new Student();
        student1.setName("Sowmik Sarker");
        student1.setScore(100);

        Student student2 = new Student();
        student2.setName("John Doe");
        student2.setScore(101);


        Student student3 = new Student();
        student3.setName("Foo Bar");
        student3.setScore(100);

        Student student4 = new Student();
        student4.setName("Alfred Bomb");
        student4.setScore(1009);

        List<Student> students = Arrays.asList(student1, student2, student3, student4);

        mav.addObject("students", students);
        return mav;
    }

    //sending object data
    @RequestMapping("/studentForm")
    public ModelAndView displayStudentForm(){
        ModelAndView mav = new ModelAndView("studentForm");
        Student student = new Student();
        student.setName("Sowmik Sarker");
        student.setScore(100);
        mav.addObject("student", student);
        return mav;
    }

    //save data from form
    @RequestMapping("/saveStudent")
    public ModelAndView saveStudent(@ModelAttribute Student student){
        ModelAndView mav = new ModelAndView("resultPage");
        //mav.addObject("student", student);
        System.out.println(student.getName());
        System.out.println(student.getScore());
        return mav;
    }

}
