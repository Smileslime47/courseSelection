package com.teamwork.courseselection.DAO.impl;

import com.teamwork.courseselection.DAO.TeacherDAO;
import com.teamwork.courseselection.Model.Model;
import com.teamwork.courseselection.Model.Teacher;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherDAOImpl implements TeacherDAO {
    static TeacherDAOImpl teacherDAO = new TeacherDAOImpl();
    Connection conn = null;

    private TeacherDAOImpl() {
    }

    public static TeacherDAOImpl getTeacherDAO() {
        return teacherDAO;
    }

    public void setConn(Connection c) {
        conn = c;
    }

    @Override
    public void add(Teacher teacher) {
        String sqlStatement = "INSERT INTO 教师 VALUES(?,?,?)";
        try (PreparedStatement statement = Model.getModel().getDBConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, teacher.getID());
            statement.setObject(2, teacher.getTeacherName());
            statement.setObject(3, teacher.getTeacherType());
            statement.executeUpdate();
        } catch (Exception e) {
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setHeight(100);
            err.setWidth(100);
            err.setTitle("错误");
            err.setContentText("添加失败");
            err.show();
        }
    }

    @Override
    public void edit(Teacher teacher) {
        String sqlStatement = "UPDATE 教师 SET 教师姓名=?,教师类型=? WHERE 教师编号=?";
        try (PreparedStatement statement = Model.getModel().getDBConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, teacher.getTeacherName());
            statement.setObject(2, teacher.getTeacherType());
            statement.setObject(3, teacher.getID());
            statement.executeUpdate();
        } catch (Exception e) {
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setHeight(100);
            err.setWidth(100);
            err.setTitle("错误");
            err.setContentText("编辑失败");
            err.show();
        }
    }

    @Override
    public void delete(String id) {
        String sqlStatement = "DELETE FROM 教师 WHERE 教师编号=?";
        try (PreparedStatement statement = Model.getModel().getDBConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setHeight(100);
            err.setWidth(100);
            err.setTitle("错误");
            err.setContentText("删除失败");
            err.show();
        }
    }

    @Override
    public Teacher find(String id) {
        String sqlStatement = "SELECT * FROM 教师 WHERE 教师编号=?";
        try (PreparedStatement statement = Model.getModel().getDBConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return null;
            } else {
                return new Teacher((String) rs.getObject("教师编号"),
                        (String) rs.getObject("教师姓名"),
                        (String) rs.getObject("教师类型"));
            }
        } catch (Exception e) {
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setHeight(100);
            err.setWidth(100);
            err.setTitle("错误");
            err.setContentText("查询数据库失败");
            err.show();
        }
        return null;
    }
}
