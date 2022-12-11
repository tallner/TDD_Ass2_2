package com.cte;

import com.cte.models.CardModel;

public class ATMrequest {
    private CardModel card;
    private String ATMpinInput;
    private int withDrawalAmount;
    private int addToBalanceAmount;



    public ATMrequest(CardModel _card){
        card = _card;
    }

    public ATMrequest(CardModel _card, String _ATMpinInput){
        card = _card;
        ATMpinInput = _ATMpinInput;
    }

    public ATMrequest(CardModel _card, String _ATMpinInput, int _addToBalanceAmount, int _withDrawalAmount){
        card = _card;
        ATMpinInput = _ATMpinInput;
        addToBalanceAmount = _addToBalanceAmount;
        withDrawalAmount = _withDrawalAmount;
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

    public void setWithDrawalAmount(int withDrawalAmount) {
        this.withDrawalAmount = withDrawalAmount;
    }

    public int getWithDrawalAmount() {
        return withDrawalAmount;
    }
}

