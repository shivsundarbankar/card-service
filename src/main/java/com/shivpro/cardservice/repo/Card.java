package com.shivpro.cardservice.repo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Card {
    @Id
    private String cardNumber;
    private String bankName;

    public Card(){

    }

    public Card(String cardNumber, String bankName){
        this.cardNumber=cardNumber;
        this.bankName=bankName;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
