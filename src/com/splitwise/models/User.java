package com.splitwise.models;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String userId;
    private String name;
    private String phoneNo;
    private String password;
    private Set<Expense> expenses;
    private Set<Group> groups;

    public User(String userId, String name, String phoneNo, String password) {
        this.userId = userId;
        this.name = name;
        this.phoneNo = phoneNo;
        this.password = password;
        this.expenses = new HashSet<>();
        this.groups = new HashSet<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public Set<Group> getGroups() {
        return groups;
    }
}
