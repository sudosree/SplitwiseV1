package com.splitwise.repositories;

import com.splitwise.models.User;

import java.util.List;
import java.util.Set;

public interface IUserRepo {

    User findUserById(String userId);

    User findUserByName(String username);

    Set<User> getUsersById(List<String> usersId) ;

    void addUser(User user);

    boolean findUsers(List<String> users);
}
