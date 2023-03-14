package com.teamwork.courseselection;

import com.teamwork.courseselection.DAO.impl.CourseDAOImpl;
import com.teamwork.courseselection.Model.Course;
import com.teamwork.courseselection.Model.DetailedCourseData;
import com.teamwork.courseselection.Model.SelectedRecord;

public class Service {
    static Service service=new Service();
    private Service(){}
    public static Service getService() {return service;}

    public Course findCourseByRecord(SelectedRecord record){
        String id=record.getCourseID();
        return CourseDAOImpl.getCourseDAO().find(id);
    }

    public DetailedCourseData courseIDToData(String id){
        Course course=CourseDAOImpl.getCourseDAO().find(id);
        return new DetailedCourseData(course);
    }
}
