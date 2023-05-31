package com.example.lab2_04;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class Employee {

    private String iD;
    private String fullName;
    private Boolean isManager;

    public Employee () {};
    public void setiD(String iD) {
        this.iD = iD;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setIsManager(Boolean manager) {
        this.isManager = manager;
    }

    public String getID() {
        return this.iD;
    }
    public String getFullName() {
        return this.fullName;
    }

    public boolean isManager() {
        return this.isManager;
    }
}
