package com.example.contractcontentprovider;

public class Contact {
    private String phone;
    private String displayName;

    public  Contact () {}
    public String getPhone() {
        return phone;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
