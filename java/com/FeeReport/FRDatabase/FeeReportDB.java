package com.FeeReport.FRDatabase;

import com.FeeReport.FRDatabase.IFRDatabase.IFeeReportDB;
import com.FeeReport.FRDatabase.IFRDatabase.IDerbyDB;


public class FeeReportDB implements IFeeReportDB {
    private final IDerbyDB derbyDB;
    public FeeReportDB(IDerbyDB derbyDB) {
        this.derbyDB = derbyDB;
    }

    public void createAccountantsTable() throws Exception {
        String query = "CREATE TABLE Accountants (" +
                "Id int not null generated always as identity, " +
                "Name varchar(15), Password varchar(10), " +
                "Email varchar(30), Number varchar(10))";
        derbyDB.createTableAsync(query);
    }

    public void createStudentsTable() throws Exception {
        String query = "CREATE TABLE Students (Id int not null generated always as identity, Name varchar(50)," +
                " Email varchar(50), Course varchar(50), " + "Fee varchar(50), Paid varchar(50), Due varchar(50)," +
                " Address varchar(200), City varchar(50), State varchar(50), " +
                "Country varchar(50), Number varchar(10))";
        derbyDB.createTableAsync(query);
    }

    @Override
    public void closeAndShutdown() throws Exception {
        derbyDB.closeConnectionsAsync();
        derbyDB.shutdownDerbySystemAsync();
    }

}
