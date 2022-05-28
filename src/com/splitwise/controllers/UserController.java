package com.splitwise.controllers;

import com.splitwise.models.User;
import com.splitwise.repositories.IUserRepo;

public class UserController {

    private IUserRepo userRepo;

    public UserController(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void registerUser(String userId, String name, String phoneNo, String password) throws Exception {
        // first check if the user exists or not
        User user = this.userRepo.findUserById(userId);
        // if user exists throw exception
        if (user != null) {
            throw new Exception("User already exists");
        }
        user = new User(userId, name, phoneNo, password);
        this.userRepo.addUser(user);
        System.out.println(userId + " has registered with the username " + name + ", phone " + phoneNo +
                " and password " + password);
    }

    public void updateProfile(String name) {

    }

}
