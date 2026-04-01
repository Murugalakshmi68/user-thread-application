package com.project.service;

import com.project.dao.UserDao;
import com.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static javax.swing.UIManager.getInt;

@Service
public class UserService {
    @Autowired
    UserDao dao;

    public void addUser(User user) {
        dao.saveUser(user);
    }

    public User getUser(int id) {
        return dao.getUser(id);
    }

    public List<User> getAllUser() {
        return dao.getAllUsers();
    }

    public void updateUser(User user) {
        dao.updateUser(user);
    }

    public void deleteUser(int id) {
        dao.deleteUser(id);
    }
}
