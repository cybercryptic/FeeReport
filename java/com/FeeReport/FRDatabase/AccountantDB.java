package com.FeeReport.FRDatabase;

import com.FeeReport.FRDatabase.IFRDatabase.IAccountantDB;
import com.FeeReport.FRDatabase.IFRDatabase.IDerbyDB;

import java.sql.ResultSet;

public class AccountantDB implements IAccountantDB {

    private final IDerbyDB derbyDB;

    public AccountantDB(IDerbyDB derbyDB) {
        this.derbyDB = derbyDB;
    }

    @Override
    public void addAccountant(String... values) throws Exception {
        String query = "INSERT INTO Accountants (" +
                "Name, Password, Email, Number) " +
                "values ('"+ values[0] +"', '"+ values[1] +"'," +
                " '"+ values[2] +"', "+ "'" + values[3] +"')";
        derbyDB.executeAsync(query);
        System.out.println("Accountant added: " + values[0]);
    }

    @Override
    public ResultSet getAccountants() throws Exception {
        String query = "SELECT * FROM Accountants";
        ResultSet set = derbyDB.executeQueryAsync(query);
        System.out.println("Accountants are retrieved");
        return set;
    }

    @Override
    public ResultSet getAccountant(String name) throws Exception {
        String query = "SELECT * FROM Accountants WHERE Name = '" + name + "'";
        var set = derbyDB.executeQueryAsync(query);
        System.out.println("Accountant retrieved: " + name);
        return set;
    }

    @Override
    public void updateAccountant(int id, String... values) throws Exception {
        String query = "UPDATE Accountants SET Name = '"+values[0]+"', Password = '"+values[1]+"', " +
                "Email = '"+values[2]+"', Number = '"+values[3]+"' WHERE Id = "+ id +" ";
        derbyDB.executeUpdateAsync(query);
        System.out.println("Accountant updated: " + values[0]);
    }

    @Override
    public void deleteAccountant(String name) throws Exception {
        String query = "DELETE FROM Accountants WHERE Name = '" + name + "'";
        derbyDB.executeUpdateAsync(query);
        System.out.println("Accountant deleted: " + name);
    }

    @Override
    public int getAccountantID(String name) throws Exception {
        String query = "SELECT * FROM Accountants WHERE Name = '" + name + "'";
        var set = derbyDB.executeQueryAsync(query);
        var id = 0;
        if (set.next())
            id = set.getInt("Id");
        return id;
    }
}
