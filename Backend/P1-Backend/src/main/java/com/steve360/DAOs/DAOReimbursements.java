package com.steve360.DAOs;

import com.steve360.Interfaces.CRUDInterface;
import com.steve360.Objects.Reimbursements;
import com.steve360.Objects.User;
import com.steve360.Services.ManagerService;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DAOReimbursements implements CRUDInterface<Reimbursements> {
        Connection connection;

        public DAOReimbursements(){
            this.connection = ManagerService.getConnection();
        }

        @Override
        public void create(Reimbursements reimbursements){
            try{
                String sql = "INSERT INTO reimbursements (userId,reimbursementtype, reimbursementcost, reimbursementstatus) VALUES ((SELECT userid FROM credentials WHERE userid = ?), ? ,?, ?)";
                PreparedStatement psmt = connection.prepareStatement(sql);

                psmt.setInt(1,reimbursements.getUserId());
                psmt.setString(2, reimbursements.getReimbursementType());
                psmt.setFloat(3, reimbursements.getReimbursementCost());
                psmt.setString(4, reimbursements.getReimbursementStatus());



                psmt.executeUpdate();

                /*
                ResultSet keys = psmt.getGeneratedKeys();
                if (keys.next()){
                    Integer key = keys.getInt("reimbursementId");
                    reimbursements.setReimbursementId(key);
                }
                */

            }

            catch (SQLException e) {
                e.printStackTrace();
            }
        }

    @Override
        public Reimbursements read(int id) {
            Reimbursements reimbursements = new Reimbursements();

            try{
                String sql = "Select * From reimbursements Where userid = ?";
                PreparedStatement psmt = connection.prepareStatement(sql);
                psmt.setInt(1, id);

                ResultSet resultSet = psmt.executeQuery();
                if(resultSet.next()){
                    reimbursements.setReimbursementId(resultSet.getInt("reimbursementid"));
                    reimbursements.setUserId(resultSet.getInt("userid"));
                    reimbursements.setReimbursementType(resultSet.getString("reimbursementtype"));
                    reimbursements.setReimbursementCost(resultSet.getFloat("reimbursementcost"));
                    reimbursements.setReimbursementStatus(resultSet.getString("reimbursementstatus"));
                }

            }

            catch (SQLException e) {
                e.printStackTrace();
            }
            return reimbursements;
        }



    @Override
    public Reimbursements validate(String userName, String password) {
        return null;
    }


    @Override
        public List<Reimbursements> readAll() {
            List<Reimbursements> reimbursementsList = new LinkedList<>();

            try{
                String sql = "Select * from reimbursements";
                PreparedStatement psmt = connection.prepareStatement(sql);

                ResultSet results = psmt.executeQuery();

                while (results.next()){
                    Reimbursements reimbursements = new Reimbursements();
                    reimbursements.setReimbursementId(results.getInt("reimbursementid"));
                    reimbursements.setUserId(results.getInt("userid"));
                    reimbursements.setReimbursementType((results.getString("reimbursementType")));
                    reimbursements.setReimbursementStatus((results.getString("reimbursementStatus")));
                    reimbursements.setReimbursementCost((results.getFloat("reimbursementCost")));

                    reimbursementsList.add(reimbursements);


                }
            }

            catch (SQLException e) {
                e.printStackTrace();
            }
            return reimbursementsList;
        }

    @Override
    public void update(Reimbursements reimbursements) {
        System.out.println("Update has Gone Wonky");
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void updateReimbursements(Reimbursements reimbursements, Integer reimbursementId, Integer userId) {
        try{
            String sql = "UPDATE reimbursements set reimbursementtype = ?, reimbursementcost = ?, reimbursementstatus = ? WHERE reimbursementid = ? AND userid = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, reimbursements.getReimbursementType());
            pstmt.setFloat(2, reimbursements.getReimbursementCost());
            pstmt.setString(3, reimbursements.getReimbursementStatus());
            pstmt.setInt(4, reimbursementId);
            pstmt.setInt(5, userId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateRole(Reimbursements reimbursements) {

    }


    @Override
        public void delete(int id) {
            try{
                String sql = "Delete From Reimbursements where userId = ?";
                PreparedStatement psmt = connection.prepareStatement(sql);
                psmt.setInt(1, id);
                psmt.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public void updateStatus(Reimbursements reimbursements, Integer reimbursementId, String reimbursementStatus) {
            try {
                String sql = "Update reimbursements Set reimbursementType = ?, reimbursementCost = ?, reimbursementStatus = ? Where reimbursementId = ?";
                PreparedStatement psmt = connection.prepareStatement(sql);
                psmt.setString(1, reimbursements.getReimbursementType());
                psmt.setFloat(2, reimbursements.getReimbursementCost());
                psmt.setString(3, reimbursementStatus);
                psmt.setInt(4, reimbursementId);

                psmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
