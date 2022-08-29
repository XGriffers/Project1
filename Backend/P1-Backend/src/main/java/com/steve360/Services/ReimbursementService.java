package com.steve360.Services;

import com.steve360.DAOs.DAOReimbursements
;
import com.steve360.Objects.Reimbursements;

import java.util.List;

public class ReimbursementService {

    private DAOReimbursements dao;

    public ReimbursementService(){
        this.dao = new DAOReimbursements();
    }

    public void save(Reimbursements reimbursement){
        dao.create(reimbursement);
    }

    public List<Reimbursements> getReimbursement(Integer userId){
        List<Reimbursements> reimbursementList = dao.readAll();


//Intellisense changed this thing for me, and it fixed my concurrent comodification exception, but I'll have to look into what it's actually doing.
        //intellisense mentioned something about Collection.Removelf.
        reimbursementList.removeIf(reimbursement -> !reimbursement.getUserId().equals(userId));
        return reimbursementList;
    }

    public List<Reimbursements> getAllReimbursements(){
        return dao.readAll();
    }



    public void updateReimbursements(Reimbursements reimbursement, Integer reimbursementId, Integer userId){
        dao.updateReimbursements(reimbursement, reimbursementId, userId);
    }

    public void updateByStatus(Reimbursements reimbursements, Integer reimbursementId, String reimbursementStatus){
        dao.updateStatus(reimbursements, reimbursementId, reimbursementStatus);
    }

    public void deleteReimbursement(int id){
        dao.delete(id);
    }

}
