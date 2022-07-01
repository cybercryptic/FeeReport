package com.FeeReport.FRDatabase;

import com.FeeReport.FRDatabase.IFRDatabase.IDerbyDB;
import com.FeeReport.FRDatabase.IFRDatabase.IStudentDB;

import java.sql.ResultSet;

public class StudentDB implements IStudentDB {

    private final IDerbyDB derbyDB;

    public StudentDB(IDerbyDB derbyDB) {
        this.derbyDB = derbyDB;
    }

    @Override
    public void addStudent(String... values) throws Exception {
        String query = "INSERT INTO Students (Name, Email, Course, Fee, Paid, Due, Address, " +
                "City, State, Country, Number) values ('" + values[0] + "', '" + values[1] + "', " +
                "'" + values[2] + "', '" + values[3] + "', '" + values[4] + "', '" + values[5] + "', " +
                "'" + values[6] + "', '" + values[7] + "', '" + values[8] + "', '" + values[9] + "', " +
                "'" + values[10] + "')";
        derbyDB.executeAsync(query);
        System.out.println("Student added: " + values[0]);
    }

    @Override
    public ResultSet getStudents() throws Exception {
        String query = "SELECT * FROM Students";
        ResultSet set = derbyDB.executeQueryAsync(query);
        System.out.println("Students are retrieved");
        return set;
    }

    @Override
    public ResultSet getStudent(String name) throws Exception {
        String query = "SELECT * FROM Students WHERE Name = '" + name + "'";
        ResultSet set = derbyDB.executeQueryAsync(query);
        System.out.println("Student retrieved: " + name);
        return set;
    }

    @Override
    public void updateStudent(int id, String... values) throws Exception {
        String query = "UPDATE Students SET Name = '"+values[0]+"', Email = '"+values[1]+"', Course = '"+values[2]+"', " +
                "Fee = '"+values[3]+"', Paid = '"+values[4]+"', Due = '"+values[5]+"', Address = '"+values[6]+"', " +
                "City = '"+values[7]+"', State = '"+values[8]+"', Country = '"+values[9]+"', Number = '"+values[10]+"' " +
                "Where Id = "+id+"";
        derbyDB.executeUpdateAsync(query);
        System.out.println("Student updated: " + values[0]);
    }

    @Override
    public void deleteStudent(String name) throws Exception {
        String query = "DELETE FROM Students WHERE Name = '" + name + "'";
        derbyDB.executeUpdateAsync(query);
        System.out.println("Student deleted: " + name);
    }

    @Override
    public int getStudentID(String name) throws Exception {
        String query = "SELECT * FROM Students WHERE Name = '" + name + "'";
        ResultSet set = derbyDB.executeQueryAsync(query);
        var id = 0;
        if (set.next())
            id = set.getInt("Id");
        return id;
    }
}
