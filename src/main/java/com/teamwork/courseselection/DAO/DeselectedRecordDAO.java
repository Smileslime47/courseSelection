package com.teamwork.courseselection.DAO;

import com.teamwork.courseselection.Model.DeselectedRecord;

import java.util.List;

public interface DeselectedRecordDAO {
    void add(DeselectedRecord record);
    void edit(DeselectedRecord record);
    void delete(String id);
    DeselectedRecord find(String id);
    List<DeselectedRecord> findByStudent(String id);
}
