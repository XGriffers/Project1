package com.steve360.Objects;

import java.util.Objects;

public class Reimbursements
 {

    private Integer reimbursementId;
    private Integer userId;
    private String reimbursementType;

    private String reimbursementStatus;
    private Float reimbursementCost;

     public Reimbursements() {
     }

     //All-Args constructor
    public Reimbursements(Integer reimbursementId, String reimbursementType, String reimbursementStatus, Float reimbursementCost, Integer userId){
         this.reimbursementId = reimbursementId;
        this.reimbursementType = reimbursementType;
        this.reimbursementStatus = reimbursementStatus;
        this.reimbursementCost = reimbursementCost;
        this.userId = userId;
    }

    //Getters and Setters
    public Integer getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(Integer reimbursementId) {
        this.reimbursementId = reimbursementId;

    }

    public String getReimbursementType() {
        return reimbursementType;
    }

    public void setReimbursementType(String reimbursementType){
        this.reimbursementType = reimbursementType;

    }

    public String getReimbursementStatus() {
        return reimbursementStatus;
    }

    public void setReimbursementStatus(String reimbursementStatus) {
        this.reimbursementStatus = reimbursementStatus;
    }

    public Float getReimbursementCost() {
        return reimbursementCost;
    }

    public void setReimbursementCost(Float reimbursementCost) {
        this.reimbursementCost = reimbursementCost;
    }

    public Integer getUserId(){
        return userId;
    }
    public void setUserId(Integer userId){
        this.userId = userId;
    }

     public boolean equals(Object o){
         if (this == o) {
             return true;
         }
         if (o == null || getClass() != o.getClass()){
             return false;
         }
         Reimbursements reimbursements = (Reimbursements) o;
         return Objects.equals(userId, reimbursements.userId) && Objects.equals(reimbursementCost, reimbursements.reimbursementCost) &&
                 Objects.equals(reimbursementType, reimbursements.reimbursementType) && Objects.equals(reimbursementStatus, reimbursements.reimbursementStatus);
     }
     @Override
     public int hashCode(){
         return Objects.hash(userId, reimbursementCost, reimbursementStatus, reimbursementType);
     }
     @Override
     public String toString(){

         return "{" +
                 "userId=" + userId +
                 ", reimbursementCost='" + reimbursementCost + '\'' +
                 ", reimbursementType='" + reimbursementType + '\'' +
                 ", reimbursementStatus'" + reimbursementStatus + '\'' +
                 '}';
     };
}
