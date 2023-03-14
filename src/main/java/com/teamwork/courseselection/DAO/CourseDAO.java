package com.teamwork.courseselection.DAO;

import com.teamwork.courseselection.Model.Course;

import java.util.List;

public interface CourseDAO {
    void add(Course course);
    void edit(Course course);
    void delete(String id);
    Course find(String id);
    List<Course> findAll();
    List<Course> findByTeacher(String id);

}
