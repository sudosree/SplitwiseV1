package com.splitwise.models;

import java.util.HashSet;
import java.util.Set;

public class Group {
    private String groupId;
    private String name;
    private String description;
    private User owner;
    private Set<User> users;
    private Set<Expense> expenses;

    public Group(String groupId, String name, String description, User owner) {
        this.groupId = groupId;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.users = new HashSet<>();
        this.expenses = new HashSet<>();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

}
