package com.cte.services;

import com.cte.ATMrequest;
import com.cte.models.UserModel;

public class BankService {

    public UserModel getUser(String cardID) {
        return null;
    }

    public String loginRequest(String cardNumber, String userName, String PIN) {
        return null;
    }

    public int getNrLoginAttempts(String cardID) {
        return 0;
    }
    public void setNrLoginAttempts(int nrLoginAttempts) {
    }

    public boolean getCardBlockStatus(String s) {
        return false;
    }

    public int getBalance(String s) {
        return 0;
    }

    public void addToBalance(Integer addBalance) {

    }

    public void withdrawFromBalance(Integer withDrawAmount) {

    }
}
