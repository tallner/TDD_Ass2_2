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

    //Return Login ok if card PIN input is correct, otherwise get number of
    //login attempts and return correspondingly
    public String loginRequest(ATMrequest _ATMrequest) {
        if (_ATMrequest.getCard().getPIN().equals(_ATMrequest.getATMpinInput()))
            return "Login OK";
        return getNrLoginAttempts(_ATMrequest);
    }

    private String getNrLoginAttempts(ATMrequest _ATMrequest){
        int nrOfLoginAttempts = bankService.getNrLoginAttempts(_ATMrequest.getCard().getCardID());
        bankService.setNrLoginAttempts(nrOfLoginAttempts+1);
        if (nrOfLoginAttempts==3) _ATMrequest.getCard().setBlockStatus(true);

        return switch (nrOfLoginAttempts){
            case 1 -> "Wrong password, you have 2 more tries";
            case 2 -> "Wrong password, you have 1 more try";
            case 3 -> "Wrong password 3 times, card is blocked";
            default -> "something went wrong";
        };


    }
}
