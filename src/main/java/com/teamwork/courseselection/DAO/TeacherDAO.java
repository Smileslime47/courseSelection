package com.teamwork.courseselection.DAO;
import com.teamwork.courseselection.Model.Teacher;
public interface TeacherDAO {
    void add(Teacher teacher);
    void edit(Teacher teacher);
    void delete(String id);
    Teacher find(String id);
}
