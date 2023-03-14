package com.teamwork.courseselection.DAO.impl;

import com.teamwork.courseselection.DAO.CourseDAO;
import com.teamwork.courseselection.Model.Course;
import com.teamwork.courseselection.Model.Model;
import com.teamwork.courseselection.Model.SelectedRecord;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    static CourseDAOImpl courseDAO=new CourseDAOImpl();
    private CourseDAOImpl(){}
    public static CourseDAOImpl getCourseDAO() {return courseDAO;}

    @Override
    public void add(Course course) {

    }

    @Override
    public void edit(Course course) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Course find(String id) {
        String sqlStatement= "SELECT * FROM 课程 WHERE 课程编号=?";
        try(PreparedStatement statement = Model.getModel().getDBConnection().prepareStatement(sqlStatement)){
            statement.setObject(1,Integer.parseInt(id));
            ResultSet rs = statement.executeQuery();
            if(!rs.next()){
                return null;
            }else{
                return new Course((String) rs.getObject("课程编号"),
                        (String) rs.getObject("课程名"),
                        (String) rs.getObject("任课教师姓名"),
                        (String) rs.getObject("任课教师编号"),
                        (int) rs.getObject("学分数量"),
                        (String) rs.getObject("开课学院"),
                        (String) rs.getObject("课程性质"),
                        (int) rs.getObject("已选人数"),
                        (int) rs.getObject("课程容量"),
                        (String) rs.getObject("课程时间"),
                        (String) rs.getObject("授课语言"));
            }
        }catch (Exception e){
            Alert err=new Alert(Alert.AlertType.ERROR);
            err.setHeight(100);
            err.setWidth(100);
            err.setTitle("错误");
            err.setContentText("查询数据库失败");
            err.show();
        }
        return null;
    }
    @Override
    public List<Course> findAll() {
        List<Course> result=new ArrayList<>();
        String sqlStatement= "SELECT * FROM 课程";
        try(PreparedStatement statement = Model.getModel().getDBConnection().prepareStatement(sqlStatement)){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result.add(new Course((String) rs.getObject("课程编号"),
                        (String) rs.getObject("课程名"),
                        (String) rs.getObject("任课教师姓名"),
                        (String) rs.getObject("任课教师编号"),
                        (int) rs.getObject("学分数量"),
                        (String) rs.getObject("开课学院"),
                        (String) rs.getObject("课程性质"),
                        (int) rs.getObject("已选人数"),
                        (int) rs.getObject("课程容量"),
                        (String) rs.getObject("课程时间"),
                        (String) rs.getObject("授课语言")));
            }
            return result;
        }catch (Exception e){
            Alert err=new Alert(Alert.AlertType.ERROR);
            err.setHeight(100);
            err.setWidth(100);
            err.setTitle("错误");
            err.setContentText("查询数据库失败");
            err.show();
        }
        return null;
    }

    @Override
    public List<Course> findByTeacher(String id) {
        return null;
    }
}
