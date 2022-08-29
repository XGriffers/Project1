package com.steve360.DAOs;

import com.steve360.Interfaces.CRUDInterface;
import com.steve360.Objects.Reimbursements;
import com.steve360.Objects.User;
import com.steve360.Services.ManagerService;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DAOUser implements CRUDInterface<User> {
    Connection connection;

    public DAOUser(){
        this.connection = ManagerService.getConnection();
    }


    @Override
    public void create(User user) {

        try{
            String sql = "Insert Into credentials (userName, password, email, roleType) Values(?, ?, ?, ?)";
            PreparedStatement psmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, user.getUserName());
            psmt.setString(2, user.getPassword());
            psmt.setString(3, user.getEmail());
            psmt.setString(4,user.getRoleType());

            psmt.executeUpdate();

            ResultSet keys = psmt.getGeneratedKeys();
            if(keys.next()){
                Integer key = keys.getInt("userId");
                user.setUserId(key);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public User read(int id) {
        User user = new User();
        try {

            String sql = "Select * From credentials Where userid = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, id);

            ResultSet resultSet = psmt.executeQuery();

            if (resultSet.next()){
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setUserId(resultSet.getInt("userid"));
                user.setRoleType(resultSet.getString("roleType"));

            }

        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User validate(String userName, String password) {
        User user = new User();
        try {
            String sql = "SELECT * FROM credentials WHERE username = ? and password = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);

            psmt.setString(1, userName);
            psmt.setString(2, password);

            ResultSet resultSet = psmt.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt("userid"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRoleType(resultSet.getString("roleType"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public List<User> readAll() {
        List<User> userList = new LinkedList<>();

        try {
            String sql = "Select * From credentials";
            PreparedStatement psmt = connection.prepareStatement(sql);
            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRoleType(resultSet.getString("roleType"));

                userList.add(user);
            }

        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void update(User user) {
        try{
            String sql = "update credentials update credentials Set roletype = ? where userid = ?";

            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setString(1, user.getUserName());
            psmt.setString(2, user.getEmail());
            psmt.setString(3, user.getPassword());
            psmt.setInt(4, user.getUserId());
            psmt.executeUpdate();

        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateReimbursements(Reimbursements reimbursements, Integer reimbursementId, Integer userId) {

    }

    @Override
    public void updateRole(User user) {
        try{
            String sql = "Update credentials Set roleType = ?" +
                    " Where userID = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);

            psmt.setString(4,"roleType");
            psmt.executeUpdate();

        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM credentials WHERE userId = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, id);
            psmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
