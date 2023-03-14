package com.teamwork.courseselection.DAO;
import com.teamwork.courseselection.Model.Student;
public interface StudentDAO {
    void add(Student student);

    void edit(Student student);
    void delete(String id);
    Student find(String id);
}
