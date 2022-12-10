package com.cte;

import com.cte.models.CardModel;

public class ATMrequest {
    private CardModel card;
    private String ATMpinInput;
    private String withDrawalAmount;

    public ATMrequest(CardModel _card){
        card = _card;
    }
    public ATMrequest(CardModel _card, String _ATMpinInput){
        card = _card;
        ATMpinInput = _ATMpinInput;
    }

    public String getATMpinInput() {
        return ATMpinInput;
    }
    public void setATMpinInput(String ATMpinInput) {
        this.ATMpinInput = ATMpinInput;
    }

    public CardModel getCard() {
        return card;
    }

    public void setCard(CardModel card) {
        this.card = card;
    }

    public void setWithDrawalAmount(String withDrawalAmount) {
        this.withDrawalAmount = withDrawalAmount;
    }

    public String getWithDrawalAmount() {
        return withDrawalAmount;
    }
}

