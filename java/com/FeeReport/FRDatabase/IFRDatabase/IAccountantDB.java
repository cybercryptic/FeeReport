package com.FeeReport.FRDatabase.IFRDatabase;

import java.sql.ResultSet;

public interface IAccountantDB {
    void addAccountant(String... values) throws Exception;
    ResultSet getAccountants() throws Exception;
    ResultSet getAccountant(String name) throws Exception;
    void updateAccountant(int id, String... values) throws Exception;
    void deleteAccountant(String name) throws Exception;
    int getAccountantID(String name) throws Exception;
}
