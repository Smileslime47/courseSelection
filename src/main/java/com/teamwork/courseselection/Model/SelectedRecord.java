package com.teamwork.courseselection.Model;

public class SelectedRecord extends Entity{

    private String courseName;
    private String courseID;
    private String studentName;
    private String studentID;
    private String teacherName;

    public SelectedRecord(String id,String courseName, String courseID, String studentName, String studentID,
                          String teacherName) {
        setID(id);
        this.courseName = courseName;
        this.courseID = courseID;
        this.studentName = studentName;
        this.studentID = studentID;
        this.teacherName = teacherName;
    }

    public SelectedRecord(String courseName, String courseID, String studentName, String studentID,
                          String teacherName) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.studentName = studentName;
        this.studentID = studentID;
        this.teacherName = teacherName;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
