package com.example.studentCourseDemo.dto;

//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//public class TransferRequest {
//    private Long fromAccountId;
//    private Long toAccountId;
//    private double amount;
//}


public class TransferRequest {

    private Long fromId;
    private Long toId;
    private double amount;

    public Long getFromId() {
        return fromId;
    }

    public Long getToId() {
        return toId;
    }

    public double getAmount() {
        return amount;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}


