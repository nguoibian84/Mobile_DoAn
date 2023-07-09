package com.example.doan_oderfood;

public class Account{
    private String username;
    private String password;
    private String isadmin;

    public Account(String username, String password, String isadmin) {
        this.username = username;
        this.password = password;
        this.isadmin = isadmin;
    }

    public Account(){

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(String isadmin) {
        this.isadmin = isadmin;
    }
}
