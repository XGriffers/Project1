package com.steve360.Objects;

import java.util.Objects;

public class User {

    private  Integer userId;
    private  String userName;
    private  String email;
    private String password;
    private String roleType;

    public User() {

    }

    public User(Integer userId, String userName, String email, String password, String roleType) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roleType = roleType;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public  Integer getUserId() {
        return userId;
    }
    public void setRoleType(String roleType){
        this.roleType = roleType;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPassword() {
        return password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
            if (o == null || getClass() != o.getClass()){
                return false;
            }
            User user = (User) o;
            return Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName) &&
                    Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(roleType, roleType);
    }
    @Override
    public int hashCode(){
        return Objects.hash(userId, userName, email, password, roleType);
    }
    @Override
    public String toString(){

        return "{" +
                "userId=" + userId +
                ", username='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleType='" + roleType + '\'' +
                '}';
    };
}
