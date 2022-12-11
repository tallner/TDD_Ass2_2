package com.cte.models;

public class CardModel {
    String PIN;
    String cardID;
    String userName;
    int nrLoginAttempts;
    boolean blockStatus;

    public CardModel(String _cardID){
        cardID = _cardID;
    }

    public CardModel(String _PIN, String _cardID, String _userName) {
        PIN = _PIN;
        cardID = _cardID;
        userName = _userName;
        nrLoginAttempts = 0;
        blockStatus = false;
    }

    public int getNrLoginAttempts() {
        return nrLoginAttempts;
    }

    public void setNrLoginAttempts(int nrLoginAttempts) {
        this.nrLoginAttempts = nrLoginAttempts;
    }

    public boolean getBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(boolean blockStatus) {
        this.blockStatus = blockStatus;
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
