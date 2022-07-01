package com.FeeReport.FRDatabase.IFRDatabase;

import java.sql.ResultSet;

public interface IStudentDB {
    void addStudent(String... values) throws Exception;
    ResultSet getStudents() throws Exception;
    ResultSet getStudent(String name) throws Exception;
    void updateStudent(int id, String... values) throws Exception;
    void deleteStudent(String name) throws Exception;
    int getStudentID(String name) throws Exception;
}
