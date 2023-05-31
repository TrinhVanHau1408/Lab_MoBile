package com.example.lab2_06;

public class Employee {
    private String iD;
    private String fullName;
    private Boolean isManager;

    Employee () {}

    public void setiD(String iD) {
        this.iD = iD;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }

    public String getiD() {
        return iD;
    }

    public String getFullName() {
        return fullName;
    }

    public Boolean getManager() {
        return isManager;
    }
}
