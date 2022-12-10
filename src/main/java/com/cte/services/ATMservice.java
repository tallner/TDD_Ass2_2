package com.cte.services;

import com.cte.ATMrequest;
import com.cte.models.UserModel;

public class ATMservice {

    private BankService bankService;

    public ATMservice (BankService _bankService){
        this.bankService = _bankService;
    }

    public String getUser(ATMrequest _ATMrequest){
        UserModel user = bankService.getUser(_ATMrequest.getCardID());
        return user.getName();
    }
}
