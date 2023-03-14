package com.teamwork.courseselection.Model;

public class Course extends Entity {
    private String courseName;
    private String teacherName;
    private String teacherID;
    private int courseCredits;
    private String courseDepartment;
    private String courseType;
    private int selectedAmount;
    private int courseCapacity;
    private String courseDate;
    private String language;

    public Course(String id,String courseName, String teacherName, String teacherID, int courseCredits,
                  String courseDepartment, String courseType, int selectedAmount, int courseCapacity, String courseDate, String language) {
        setID(id);
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.teacherID = teacherID;
        this.courseCredits = courseCredits;
        this.courseDepartment = courseDepartment;
        this.courseType = courseType;
        this.selectedAmount = selectedAmount;
        this.courseCapacity = courseCapacity;
        this.courseDate = courseDate;
        this.language = language;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public int getCourseCredits() {
        return courseCredits;
    }

    public void setCourseCredits(int courseCredits) {
        this.courseCredits = courseCredits;
    }

    public String getCourseDepartment() {
        return courseDepartment;
    }

    public void setCourseDepartment(String courseDepartment) {
        this.courseDepartment = courseDepartment;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public int getSelectedAmount() {
        return selectedAmount;
    }

    public void setSelectedAmount(int selectedAmount) {
        this.selectedAmount = selectedAmount;
    }

    public int getCourseCapacity() {
        return courseCapacity;
    }

    public void setCourseCapacity(int courseCapacity) {
        this.courseCapacity = courseCapacity;
    }

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
