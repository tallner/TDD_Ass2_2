package com.cte.models;

public class CardModel {
    String PIN;
    String cardID;
    String userName;

    public CardModel(String PIN, String cardID, String userName) {
        this.PIN = PIN;
        this.cardID = cardID;
        this.userName = userName;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
