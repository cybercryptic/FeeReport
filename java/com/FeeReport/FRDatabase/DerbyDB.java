package com.FeeReport.FRDatabase;

import com.FeeReport.FRDatabase.IFRDatabase.IDerbyDB;

import java.sql.*;
import java.util.concurrent.CompletableFuture;

public class DerbyDB implements IDerbyDB {
    private Connection conn;
    private Statement stmt;
    private final String dbLocation;

    public DerbyDB(String dbLocation) throws Exception {
        this.dbLocation = dbLocation;
        connectToDB();
    }

    private void connectToDB() throws Exception {
        var future = CompletableFuture.runAsync(() -> {
            try {
                conn = getConnection();
                stmt = getStatement();
                System.out.println("Derby system booted");
                System.out.println("Connected to database");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        throwErrorsIfExistedIn(future);
    }

    private Statement getStatement() throws SQLException {
        return conn.createStatement();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby:" + dbLocation + ";create=true;");
    }

    @Override
    public void createTable(String query) throws SQLException {
        stmt.execute(query);
        System.out.println("Table created");
    }

    @Override
    public void createTableAsync(String query) throws Exception {
        var future = CompletableFuture.runAsync(() -> {
            try {
                createTable(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        throwErrorsIfExistedIn(future);
    }

    @Override
    public void deleteTable(String tableName) throws SQLException {
        var query = "DROP TABLE " + tableName;
        stmt.execute(query);
        System.out.println("Table deleted");
    }

    @Override
    public void deleteTableAsync(String tableName) throws Exception {
        var future = CompletableFuture.runAsync(() -> {
            try {
                deleteTable(tableName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        throwErrorsIfExistedIn(future);
    }

    @Override
    public void emptyTable(String tableName) throws SQLException {
        var query = "DELETE FROM " + tableName;
        stmt.execute(query);
        System.out.println("Table is empty now");
    }

    @Override
    public void emptyTableAsync(String tableName) throws Exception {
        var future = CompletableFuture.runAsync(() -> {
            try {
                emptyTable(tableName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        throwErrorsIfExistedIn(future);
    }

    @Override
    public void execute(String query) throws SQLException {
        stmt.execute(query);
    }

    @Override
    public void executeAsync(String query) throws Exception {
         var future = CompletableFuture.runAsync(() -> {
            try {
                stmt.execute(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
         throwErrorsIfExistedIn(future);
    }

    @Override
    public ResultSet executeQuery(String query) throws SQLException {
        return stmt.executeQuery(query);
    }

    public ResultSet executeQueryAsync(String query) throws Exception {
        var future = CompletableFuture.supplyAsync(() -> {
            try {
                return stmt.executeQuery(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        throwErrorsIfExistedIn(future);
        return future.get();
    }

    @Override
    public void executeUpdate(String query) throws SQLException {
        stmt.executeUpdate(query);
    }

    @Override
    public void executeUpdateAsync(String query) throws Exception {
        var future = CompletableFuture.runAsync(() -> {
            try {
                executeUpdate(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        throwErrorsIfExistedIn(future);
    }
    @Override
    public void closeConnections() throws SQLException {
        stmt.close();
        conn.close();
        System.out.println("Connections are closed");
    }

    @Override
    public void closeConnectionsAsync() throws Exception {
        var future = CompletableFuture.runAsync(() -> {
            try {
                closeConnections();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        throwErrorsIfExistedIn(future);
    }

    @Override
    public void shutdownDerbyDB() throws SQLException {
        DriverManager.getConnection("jdbc:derby:"+ dbLocation +";" + "shutdown=true");
        System.out.println("Database is shutdown");
    }

    @Override
    public void shutdownDerbyDBAsync() throws Exception {
        var future = CompletableFuture.runAsync(() -> {
            try {
                shutdownDerbyDB();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        throwErrorsIfExistedIn(future);
    }

    @Override
    public void shutdownDerbyDB(String dbLocation) throws SQLException {
        DriverManager.getConnection("jdbc:derby:"+ dbLocation +";" + "shutdown=true");
        System.out.println("Database is shutdown");
    }

    @Override
    public void shutdownDerbyDBAsync(String dbLocation) throws Exception {
        var future = CompletableFuture.runAsync(() -> {
            try {
                shutdownDerbyDB(dbLocation);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        throwErrorsIfExistedIn(future);
    }
    @Override
    public void shutdownDerbySystem() {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true;");
        } catch (SQLException e) {
            System.out.println("Derby system is shutdown");
        }
    }

    @Override
    public void shutdownDerbySystemAsync() throws Exception {
        var future = CompletableFuture.runAsync(this::shutdownDerbySystem);
        throwErrorsIfExistedIn(future);
    }

    private void throwErrorsIfExistedIn(CompletableFuture<?> future) throws Exception {
        if (!future.isDone())
            future.get();
    }
}
