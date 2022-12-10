package com.cte;

public class ATMrequest {
    private String cardID;
    private String withDrawalAmount;
    private String cardPIN;

    public ATMrequest(String _cardID){
        cardID = _cardID;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardPIN(String cardPIN) {
        this.cardPIN = cardPIN;
    }

    public void setWithDrawalAmount(String withDrawalAmount) {
        this.withDrawalAmount = withDrawalAmount;
    }

    public String getWithDrawalAmount() {
        return withDrawalAmount;
    }
}

