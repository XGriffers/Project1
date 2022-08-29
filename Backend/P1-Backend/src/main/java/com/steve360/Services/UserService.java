package com.steve360.Services;

import com.steve360.DAOs.DAOUser;
import com.steve360.Objects.User;

import java.util.List;

public class UserService {
    private DAOUser dao;

    public UserService(){
        this.dao = new DAOUser();
    }


    public void save(User user){
        dao.create(user);
    }

    public User getUser(int id){
        return dao.read(id);
    }


    public List<User> getAllUsers(){
        return dao.readAll();
    }

    public void updateUser(User user, Integer userId){
        dao.update(user);
    }

    public void deleteUser(int id){
        dao.delete(id);
    }

    public User validate(String userName, String password) {
        return dao.validate(userName, password);
    }
}
