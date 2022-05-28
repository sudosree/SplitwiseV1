package com.splitwise.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Expense {
    private String name;
    private User owner;
    private Set<User> users;
    private double amountPaid;
    private Map<User, Double> usersOweAmount;

    public Expense(String name, User owner, Set<User> users, double amountPaid) {
        this.name = name;
        this.owner = owner;
        this.users = users;
        this.amountPaid = amountPaid;
        this.usersOweAmount = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Map<User, Double> getUsersOweAmount() {
        return usersOweAmount;
    }

    public void addOweAmount(User user, Double oweAmount) {
        this.usersOweAmount.put(user, oweAmount);
    }
}
