package com.FeeReport.FRDatabase.IFRDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public interface IDerbyDB {
    void createTable(String query) throws SQLException;
    void deleteTable(String tableName) throws SQLException;
    void emptyTable(String tableName) throws SQLException;
    void execute(String query) throws SQLException;
    ResultSet executeQuery(String query) throws SQLException;
    void executeUpdate(String query) throws SQLException;
    void closeConnections() throws SQLException;
    void shutdownDerbyDB() throws SQLException;
    void shutdownDerbyDB(String dbLocation) throws SQLException;
    void shutdownDerbySystem();

    void createTableAsync(String query) throws Exception;
    void deleteTableAsync(String tableName) throws Exception;
    void emptyTableAsync(String tableName) throws Exception;
    void executeAsync(String query) throws Exception;
    ResultSet executeQueryAsync(String query) throws Exception;
    void executeUpdateAsync(String query) throws Exception;
    void closeConnectionsAsync() throws Exception;
    void shutdownDerbyDBAsync() throws Exception;
    void shutdownDerbyDBAsync(String dbLocation) throws Exception;
    void shutdownDerbySystemAsync() throws Exception;
}
