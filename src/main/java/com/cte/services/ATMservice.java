package com.cte.services;

import com.cte.ATMrequest;
import com.cte.models.UserModel;

public class ATMservice {

    private BankService bankService;

    public ATMservice (BankService _bankService){
        this.bankService = _bankService;
    }

    public String getUser(ATMrequest _ATMrequest){
        UserModel user = bankService.getUser(_ATMrequest.getCard().getCardID());
        return user.getName();
    }

    //Return true if card PIN input is correct
    public boolean loginRequest(ATMrequest _ATMrequest) {
        return (_ATMrequest.getCard().getPIN().equals(_ATMrequest.getATMpinInput()));

        /*return bankService.loginRequest(
                _ATMrequest.getCard().getCardID(),
                _ATMrequest.getCard().getUserName(),
                _ATMrequest.getCard().getPIN());

         */
    }
}
