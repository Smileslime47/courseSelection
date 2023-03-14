package com.teamwork.courseselection.Utils;

import com.teamwork.courseselection.DAO.impl.StudentDAOImpl;
import com.teamwork.courseselection.DAO.impl.TeacherDAOImpl;

import java.sql.Connection;

public class DAOConnecter {
    public static void connect(Connection c){
        StudentDAOImpl.getStudentDAO().setConn(c);
        TeacherDAOImpl.getTeacherDAO().setConn(c);
    }
    public static void disconnect(){
        connect(null);
    }
}
