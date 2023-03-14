package com.teamwork.courseselection.Model;

import com.teamwork.courseselection.DAO.impl.CourseDAOImpl;
import com.teamwork.courseselection.DAO.impl.SelectedRecordDAOImpl;
import com.teamwork.courseselection.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.control.Button;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Model {
    /*
    Model注册为单例模式
    * */
    private static final Model model=new Model();
    private Model(){}

    public static Model getModel() {
        return model;
    }

    /*
    * 身份信息:学生？教师？
    * */
    public enum Identity{Teacher,Student} ;
    private Identity ID=Identity.Student;
    public Identity getId(){return ID;}
    public void setId(Identity newID){
        ID=newID;
    }

    private Entity currentUser;
    public Entity getCurrentUser(){return currentUser;}
    public void setCurrentUser(Entity u){currentUser=u;}


    /*
     * 获取数据库链接供DAO层调用
     */
    private Connection DBConnection=null;
    public Connection getDBConnection(){
        return DBConnection;
    }
    public void setDBConnection(Connection conn){
        DBConnection=conn;
    }

    /*
    更新用户选课记录
     */
    private List<SelectedRecord> selectedList=null;
    private List<Course> selectedCourses=null;
    private ObservableList<DetailedCourseData> selectedData=null;

    public void setSelectedList(List<SelectedRecord> l){
        selectedList= l;

        selectedCourses=new ArrayList<>();
        for(SelectedRecord r:selectedList){
            selectedCourses.add(Service.getService().findCourseByRecord(r));
        }

        selectedData= FXCollections.observableArrayList();
        for(Course c:selectedCourses){
            selectedData.add(Service.getService().courseIDToData(c.getID()));
        }
    }
    public List<SelectedRecord> getSelectedList(){return selectedList;}
    public List<Course> getSelectedCourses(){return selectedCourses;}
    public ObservableList<DetailedCourseData> getSelectedData(){return selectedData;}

    /*
    更新选课列表
     */
    private List<Course> allCourses=null;
    private List<Course> courseList=null;
    private ObservableList<DetailedCourseData> courseData=null;

    public void setAllCourses(){
        boolean isSelected;
        allCourses=CourseDAOImpl.getCourseDAO().findAll();
        courseList=new ArrayList<>();
        courseData=FXCollections.observableArrayList();
        for(Course c:allCourses){
            isSelected=false;
            for(Course sc:selectedCourses){
                if(Objects.equals(sc.getID(), c.getID())){
                    isSelected=true;
                    break;
                }
            }
            if(!isSelected){
                courseList.add(c);
                courseData.add(Service.getService().courseIDToData(c.getID()));
            }
        }
    }

    public List<Course> getAllCourses(){return allCourses;}
    public List<Course> getCourseList(){return courseList;}
    public ObservableList<DetailedCourseData> getCourseData(){return courseData;}

}
