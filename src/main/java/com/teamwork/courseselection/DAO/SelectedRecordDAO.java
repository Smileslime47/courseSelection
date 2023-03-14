package com.teamwork.courseselection.DAO;

import com.teamwork.courseselection.Model.SelectedRecord;

import java.sql.SQLException;
import java.util.List;

public interface SelectedRecordDAO {
    void add(SelectedRecord record) throws SQLException;
    void edit(SelectedRecord record);
    void delete(String id);
    SelectedRecord find(String id);
    List<SelectedRecord> findByStudent(String id) throws SQLException;
}