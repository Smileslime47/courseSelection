package com.teamwork.courseselection.Model;

public class Student extends Entity {
    private String studentName;
    private String password;
    private float selectedCredits;
    private float availableCredits;
    private float totalCredits;

    public Student(String id,String studentName, String password, float selectedCredits, float availableCredits,
                   float totalCredits) {
        setID(id);
        this.studentName = studentName;
        this.password = password;
        this.selectedCredits = selectedCredits;
        this.availableCredits = availableCredits;
        this.totalCredits = totalCredits;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getSelectedCredits() {
        return selectedCredits;
    }

    public void setSelectedCredits(float selectedCredits) {
        this.selectedCredits = selectedCredits;
    }

    public float getAvailableCredits() {
        return availableCredits;
    }

    public void setAvailableCredits(float availableCredits) {
        this.availableCredits = availableCredits;
    }

    public float getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(float totalCredits) {
        this.totalCredits = totalCredits;
    }
}
