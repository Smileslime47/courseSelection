package com.teamwork.courseselection.Model;

public class Teacher extends Entity {
    private String teacherName;
    private String teacherType;

    public Teacher(String id,String teacherName, String teacherType) {
        setID(id);
        this.teacherName = teacherName;
        this.teacherType = teacherType;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(String teacherType) {
        this.teacherType = teacherType;
    }
}
