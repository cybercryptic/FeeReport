package com.FeeReport.FRGUI.Controller.Admin;

public class Accountant {
    private final String id;
    private final String name;
    private final String password;
    private final String email;
    private final String number;

    public Accountant(String id, String name, String password, String email, String number) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public String getId() {
        return id;
    }
}
