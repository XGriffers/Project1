package com.steve360.Interfaces;

import com.steve360.Objects.Reimbursements;
import com.steve360.Objects.User;

import java.util.List;

public interface CRUDInterface <T> {


    void create(T t);

    T read(int id);

    T validate(String userName, String password);

    List<T> readAll();

    void update(T t);

    void update(User user);

    void updateReimbursements(Reimbursements reimbursements, Integer reimbursementId, Integer userId);

    void updateRole(T t);
    void delete(int id);


}
