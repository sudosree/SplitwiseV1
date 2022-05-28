package com.splitwise.repositories;

import com.splitwise.models.User;

import java.util.*;

public class UserInMemoryRepo implements IUserRepo {

    private Map<String, User> userIdMap;
    private Map<String, User> userNameMap;

    public UserInMemoryRepo() {
        this.userIdMap = new HashMap<>();
        this.userNameMap = new HashMap<>();
    }

    @Override
    public User findUserById(String userId) {
        if (!this.userIdMap.containsKey(userId)) {
            return null;
        }
        return this.userIdMap.get(userId);
    }

    @Override
    public User findUserByName(String username) {
        if (!this.userNameMap.containsKey(username)) {
            return null;
        }
        return this.userNameMap.get(username);
    }

    @Override
    public Set<User> getUsersById(List<String> usersId) {
        Set<User> users = new LinkedHashSet<>();
        for (String userId : usersId) {
            if (this.userIdMap.containsKey(userId)) {
                users.add(userIdMap.get(userId));
            }
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        this.userIdMap.put(user.getUserId(), user);
        this.userNameMap.put(user.getName(), user);
    }

    @Override
    public boolean findUsers(List<String> users) {
        for (String user : users) {
            if (!this.userIdMap.containsKey(user)) {
                return false;
            }
        }
        return true;
    }
}
