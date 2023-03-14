package com.teamwork.courseselection.DAO.impl;

import com.teamwork.courseselection.DAO.DeselectedRecordDAO;
import com.teamwork.courseselection.Model.DeselectedRecord;
import com.teamwork.courseselection.Model.Model;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.util.List;

public class DeselectedRecordDAOImpl implements DeselectedRecordDAO {
    static DeselectedRecordDAOImpl deselectedRecordDAO=new DeselectedRecordDAOImpl();
    private DeselectedRecordDAOImpl(){}
    public static DeselectedRecordDAOImpl getDeselectedRecordDAO() {return deselectedRecordDAO;}

    @Override
    public void add(DeselectedRecord record) {
        String sqlStatement= "INSERT INTO 退选记录(课程名,课程编号,学生姓名,学生学号,任课教师姓名,退选人) VALUES(?,?,?,?,?,?)";
        try(PreparedStatement statement = Model.getModel().getDBConnection().prepareStatement(sqlStatement)){
            statement.setObject(1,record.getCourseName());
            statement.setObject(2,record.getCourseID());
            statement.setObject(3,record.getStudentName());
            statement.setObject(4,record.getStudentID());
            statement.setObject(5,record.getTeacherName());
            statement.setObject(6,record.getOperator());
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
    public void edit(DeselectedRecord record) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public DeselectedRecord find(String id) {
        return null;
    }

    @Override
    public List<DeselectedRecord> findByStudent(String id) {
        return null;
    }
}
