package com.teamwork.courseselection.Model;

import com.teamwork.courseselection.DAO.impl.SelectedRecordDAOImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class DetailedCourseData {
    private SimpleStringProperty courseID;
    private  SimpleStringProperty courseName;
    private  SimpleStringProperty teacherName;
    private  SimpleStringProperty teacherID;
    private  SimpleIntegerProperty courseCredits;
    private  SimpleStringProperty courseDepartment;
    private  SimpleStringProperty courseType;
    private  SimpleIntegerProperty selectedAmount;
    private  SimpleIntegerProperty courseCapacity;
    private  SimpleStringProperty courseDate;
    private  SimpleStringProperty language;

    public DetailedCourseData(Course course){
        this.courseID=new SimpleStringProperty(course.getID());
        this.courseName=new SimpleStringProperty(course.getCourseName());
        this.teacherName=new SimpleStringProperty(course.getTeacherName());
        this.teacherID=new SimpleStringProperty(course.getTeacherID());
        this.courseCredits=new SimpleIntegerProperty(course.getCourseCredits());
        this.courseDepartment=new SimpleStringProperty(course.getCourseDepartment());
        this.courseType=new SimpleStringProperty(course.getCourseType());
        this.selectedAmount=new SimpleIntegerProperty(course.getSelectedAmount());
        this.courseCapacity=new SimpleIntegerProperty(course.getCourseCapacity());
        this.courseDate=new SimpleStringProperty(course.getCourseDate());
        this.language=new SimpleStringProperty(course.getLanguage());
    }
    public String getCourseName() {
        return courseName.get();
    }

    public SimpleStringProperty courseNameProperty() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }

    public String getTeacherName() {
        return teacherName.get();
    }

    public SimpleStringProperty teacherNameProperty() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName.set(teacherName);
    }

    public String getTeacherID() {
        return teacherID.get();
    }

    public SimpleStringProperty teacherIDProperty() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID.set(teacherID);
    }

    public int getCourseCredits() {
        return courseCredits.get();
    }

    public SimpleIntegerProperty courseCreditsProperty() {
        return courseCredits;
    }

    public void setCourseCredits(int courseCredits) {
        this.courseCredits.set(courseCredits);
    }

    public String getCourseDepartment() {
        return courseDepartment.get();
    }

    public SimpleStringProperty courseDepartmentProperty() {
        return courseDepartment;
    }

    public void setCourseDepartment(String courseDepartment) {
        this.courseDepartment.set(courseDepartment);
    }

    public String getCourseType() {
        return courseType.get();
    }

    public SimpleStringProperty courseTypeProperty() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType.set(courseType);
    }

    public int getSelectedAmount() {
        return selectedAmount.get();
    }

    public SimpleIntegerProperty selectedAmountProperty() {
        return selectedAmount;
    }

    public void setSelectedAmount(int selectedAmount) {
        this.selectedAmount.set(selectedAmount);
    }

    public int getCourseCapacity() {
        return courseCapacity.get();
    }

    public SimpleIntegerProperty courseCapacityProperty() {
        return courseCapacity;
    }

    public void setCourseCapacity(int courseCapacity) {
        this.courseCapacity.set(courseCapacity);
    }

    public String getCourseDate() {
        return courseDate.get();
    }

    public SimpleStringProperty courseDateProperty() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate.set(courseDate);
    }

    public String getLanguage() {
        return language.get();
    }

    public SimpleStringProperty languageProperty() {
        return language;
    }

    public void setLanguage(String language) {
        this.language.set(language);
    }

    public String getCourseID() {
        return courseID.get();
    }

    public SimpleStringProperty courseIDProperty() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID.set(courseID);
    }
}
