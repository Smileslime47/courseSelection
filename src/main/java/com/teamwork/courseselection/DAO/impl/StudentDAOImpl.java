package com.teamwork.courseselection.DAO.impl;

import com.teamwork.courseselection.DAO.StudentDAO;
import com.teamwork.courseselection.Model.Model;
import com.teamwork.courseselection.Model.Student;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAOImpl implements StudentDAO {
    static StudentDAOImpl studentDAO=new StudentDAOImpl();
    private StudentDAOImpl(){}
    public static StudentDAOImpl getStudentDAO() {return studentDAO;}

    Connection conn = null;
    public void setConn(Connection c){conn=c;}

    @Override
    public void add(Student student) {
        String sqlStatement= "INSERT INTO 选课记录 VALUES(?,?,?,?,?,?)";
        try(PreparedStatement statement = Model.getModel().getDBConnection().prepareStatement(sqlStatement)){
            statement.setObject(1,student.getID());
            statement.setObject(2,student.getStudentName());
            statement.setObject(3,student.getPassword());
            statement.setObject(4,student.getSelectedCredits());
            statement.setObject(5,student.getAvailableCredits());
            statement.setObject(6,student.getTotalCredits());
            statement.executeUpdate();
        }catch (Exception e){
            Alert err=new Alert(Alert.AlertType.ERROR);
            err.setHeight(100);
            err.setWidth(100);
            err.setTitle("错误");
            err.setContentText("添加失败");
            err.show();
        }
    }

    @Override
    public void edit(Student student) {
        String sqlStatement= "UPDATE 学生 SET 学生姓名=?,学生密码=?,已选学分数量=?,可选学分数量=?,总学分数量=? WHERE 学生学号=?";
        try(PreparedStatement statement = Model.getModel().getDBConnection().prepareStatement(sqlStatement)){
            statement.setObject(1,student.getStudentName());
            statement.setObject(2,student.getPassword());
            statement.setObject(3,student.getSelectedCredits());
            statement.setObject(4,student.getAvailableCredits());
            statement.setObject(5,student.getTotalCredits());
            statement.setObject(6,student.getID());
            statement.executeUpdate();
        }catch (Exception e){
            Alert err=new Alert(Alert.AlertType.ERROR);
            err.setHeight(100);
            err.setWidth(100);
            err.setTitle("错误");
            err.setContentText("编辑失败");
            err.show();
        }
    }

    @Override
    public void delete(String id) {
        String sqlStatement= "DELETE FROM 学生 WHERE 学生学号=?";
        try(PreparedStatement statement = Model.getModel().getDBConnection().prepareStatement(sqlStatement)){
            statement.setObject(1,id);
            statement.executeUpdate();
        }catch (Exception e){
            Alert err=new Alert(Alert.AlertType.ERROR);
            err.setHeight(100);
            err.setWidth(100);
            err.setTitle("错误");
            err.setContentText("删除失败");
            err.show();
        }
    }

    @Override
    public Student find(String id) {
        String sqlStatement= "SELECT * FROM 学生 WHERE 学生学号=?";
        try(PreparedStatement statement = Model.getModel().getDBConnection().prepareStatement(sqlStatement)){
            statement.setObject(1,id);
            ResultSet rs = statement.executeQuery();
            if(!rs.next()){
                return null;
            }else{
                return new Student((String) rs.getObject("学生学号"),
                        (String) rs.getObject("学生姓名"),
                        (String) rs.getObject("学生密码"),
                        (float) rs.getObject("已选学分数量"),
                        (float) rs.getObject("可选学分数量"),
                        (float) rs.getObject("总学分数量"));
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
}
