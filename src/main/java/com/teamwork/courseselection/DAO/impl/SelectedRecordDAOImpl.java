package com.teamwork.courseselection.DAO.impl;

import com.teamwork.courseselection.DAO.SelectedRecordDAO;
import com.teamwork.courseselection.Model.Model;
import com.teamwork.courseselection.Model.SelectedRecord;
import com.teamwork.courseselection.Model.Student;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectedRecordDAOImpl implements SelectedRecordDAO {
    static SelectedRecordDAOImpl selectedRecordDAO=new SelectedRecordDAOImpl();
    private SelectedRecordDAOImpl(){}
    public static SelectedRecordDAOImpl getSelectedRecordDAO() {return selectedRecordDAO;}

    @Override
    public void add(SelectedRecord record) throws SQLException {
        String sqlStatement= "INSERT INTO 选课记录(课程名,课程编号,学生姓名,学生学号,任课教师姓名) VALUES(?,?,?,?,?)";
        try(PreparedStatement statement = Model.getModel().getDBConnection().prepareStatement(sqlStatement)){
            statement.setObject(1,record.getCourseName());
            statement.setObject(2,record.getCourseID());
            statement.setObject(3,record.getStudentName());
            statement.setObject(4,record.getStudentID());
            statement.setObject(5,record.getTeacherName());
            statement.executeUpdate();
        }catch (Exception e){
            Alert err=new Alert(Alert.AlertType.ERROR);
            err.setHeight(100);
            err.setWidth(100);
            err.setTitle("错误");
            err.setContentText("添加失败");
            err.show();
        }
        sqlStatement= "SELECT 选课记录号 FROM 选课记录 WHERE 学生学号=? AND 课程编号=?";
        try(PreparedStatement statement = Model.getModel().getDBConnection().prepareStatement(sqlStatement)){
            statement.setObject(1,record.getStudentID());
            statement.setObject(2,record.getCourseID());
            ResultSet rs = statement.executeQuery();
            rs.next();
            System.out.println(rs.getInt("选课记录号"));
            record.setID(Integer.toString((Integer) rs.getObject("选课记录号")));
        }catch (Exception e){
            Alert err=new Alert(Alert.AlertType.ERROR);
            err.setHeight(100);
            err.setWidth(100);
            err.setTitle("错误");
            err.setContentText("返回失败");
            err.show();
        }
    }

    @Override
    public void edit(SelectedRecord record) {
        String sqlStatement= "UPDATE 选课记录 SET 课程名=?,课程编号=?,学生姓名=?,学生学号=?,任课教师姓名=? WHERE 选课记录号=?";
        try(PreparedStatement statement = Model.getModel().getDBConnection().prepareStatement(sqlStatement)){
            statement.setObject(1,record.getCourseName());
            statement.setObject(2,record.getCourseID());
            statement.setObject(3,record.getStudentName());
            statement.setObject(4,record.getStudentID());
            statement.setObject(5,record.getTeacherName());
            statement.setObject(6,record.getID());
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
        String sqlStatement= "DELETE FROM 选课记录 WHERE 选课记录号=?";
        try(PreparedStatement statement = Model.getModel().getDBConnection().prepareStatement(sqlStatement)){
            statement.setObject(1,Integer.parseInt(id));
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
    public SelectedRecord find(String id) {
        String sqlStatement= "SELECT * FROM 选课记录 WHERE 选课记录号=?";
        try(PreparedStatement statement = Model.getModel().getDBConnection().prepareStatement(sqlStatement)){
            statement.setObject(1,Integer.parseInt(id));
            ResultSet rs = statement.executeQuery();
            if(!rs.next()){
                return null;
            }else{
                return new SelectedRecord(Integer.toString((Integer) rs.getObject("选课记录号")),
                        (String) rs.getObject("课程名"),
                        (String) rs.getObject("课程编号"),
                        (String) rs.getObject("学生姓名"),
                        (String) rs.getObject("学生学号"),
                        (String) rs.getObject("任课教师姓名") );
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
    public List<SelectedRecord> findByStudent(String id) throws SQLException {
        List<SelectedRecord> result=new ArrayList<>();
        String sqlStatement= "SELECT * FROM 选课记录 WHERE 学生学号=?";
        try(PreparedStatement statement = Model.getModel().getDBConnection().prepareStatement(sqlStatement)){
            statement.setObject(1,id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result.add(new SelectedRecord(Integer.toString((Integer) rs.getObject("选课记录号")),(String) rs.getObject("课程名"),
                        (String) rs.getObject("课程编号"),(String) rs.getObject("学生姓名"),(String) rs.getObject("学生学号"),
                        (String) rs.getObject("任课教师姓名")));
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
}
