package com.cte;

import com.cte.models.CardModel;

public class ATMrequest {
    private CardModel card;
    private String ATMpinInput;
    private String withDrawalAmount;
    private int addToBalanceAmount;

    public ATMrequest(CardModel _card){
        card = _card;
    }

    public ATMrequest(CardModel _card, String _ATMpinInput){
        card = _card;
        ATMpinInput = _ATMpinInput;
    }

    public ATMrequest(CardModel _card, String _ATMpinInput, int _addToBalanceAmount){
        card = _card;
        ATMpinInput = _ATMpinInput;
        addToBalanceAmount = _addToBalanceAmount;
    }

    public void setAddToBalanceAmount(int _addToBalanceAmount) {
        addToBalanceAmount = _addToBalanceAmount;
    }

    public int getAddToBalanceAmount() {
        return addToBalanceAmount;
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

