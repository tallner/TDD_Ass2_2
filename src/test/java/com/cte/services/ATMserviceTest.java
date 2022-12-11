package com.cte.services;

import com.cte.ATMrequest;
import com.cte.models.CardModel;
import com.cte.models.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ATMserviceTest {

    private BankService bankService;
    private ATMservice myATMservice;
    private ArgumentCaptor<Integer> addToBalanceArgumentCaptor,withdrawBalanceArgumentCaptor;

    @BeforeEach
    public void setUp() {
        bankService = mock(BankService.class);
        myATMservice = new ATMservice(bankService);

        addToBalanceArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        withdrawBalanceArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
    }

    private static List<Arguments> testUsers(){
        return Arrays.asList(
//                Arguments.of(card number,user name,card pin code),
                Arguments.of("1234 5678 9012 3456","Johan Nilsson","1234"),
                Arguments.of("9012 3456 1234 5678","Sven Lundin", "2345"),
                Arguments.of("5678 9012 3456 1234","Sara Bengtsson", "4534"),
                Arguments.of("9012 1234 5678 3456","Bodil Bundin", "5667")
        );
    }

    @ParameterizedTest
    @MethodSource("testUsers")
    public void getUserFromBankByCheckingCardNumber(String cardNumber, String userName){
        ATMrequest _ATMrequest = new ATMrequest(new CardModel("",cardNumber,""));
        when(bankService.getUser(cardNumber)).thenReturn(new UserModel(userName));

        String expected = userName;
        String actual = myATMservice.getUser(_ATMrequest);

        assertEquals(expected,actual);
    }

    @ParameterizedTest
    @MethodSource("testUsers")
    public void loginUser(String cardNumber, String userName, String PIN){
        String cardPINATMinput = PIN;
        CardModel myCard = new CardModel(PIN,cardNumber,userName);
        ATMrequest _ATMrequest = new ATMrequest(myCard,cardPINATMinput);

        assertTrue(myATMservice.loginRequest(_ATMrequest).equals("Login OK"));
    }

    @Test
    public void loginWithWrongPasswordShouldReturnFalse(){
        CardModel myCard = new CardModel("1515","15151515","Tallner");
        String wrongCardPINATMinput = "1010";
        ATMrequest _ATMrequest = new ATMrequest(myCard,wrongCardPINATMinput);

        assertFalse(myATMservice.loginRequest(_ATMrequest).equals("Login OK"));
    }

    @Test
    public void loginOnceWithWrongPasswordShouldIncreaseFaultCounter(){
        CardModel myCard = new CardModel("1515","15151515","Tallner");
        String wrongCardPINATMinput = "1010";
        ATMrequest _ATMrequest = new ATMrequest(myCard,wrongCardPINATMinput);
        when(bankService.getNrLoginAttempts("15151515")).thenReturn(1);

        String expected = "Wrong password, you have 2 more tries";
        String actual = myATMservice.loginRequest(_ATMrequest);

        assertEquals(expected,actual);
    }

    @Test
    public void loginTwiceWithWrongPasswordShouldIncreaseFaultCounter(){
        CardModel myCard = new CardModel("1515","15151515","Tallner");
        String wrongCardPINATMinput = "1010";
        ATMrequest _ATMrequest = new ATMrequest(myCard,wrongCardPINATMinput);
        when(bankService.getNrLoginAttempts("15151515")).thenReturn(2);

        String expected = "Wrong password, you have 1 more try";
        String actual = myATMservice.loginRequest(_ATMrequest);

        assertEquals(expected,actual);
    }

    @Test
    public void loginThriceWithWrongPasswordShouldIncreaseFaultCounter(){
        CardModel myCard = new CardModel("1515","15151515","Tallner");
        String wrongCardPINATMinput = "1010";
        ATMrequest _ATMrequest = new ATMrequest(myCard,wrongCardPINATMinput);
        when(bankService.getNrLoginAttempts("15151515")).thenReturn(3);

        String expected = "Wrong password 3 times, card is blocked";
        String actual = myATMservice.loginRequest(_ATMrequest);

        assertEquals(expected,actual);
    }

    @Test
    public void checkIfCardIsBlockedWhenInserted(){
        CardModel myCard = new CardModel("15151515");

        ATMrequest _ATMrequest = new ATMrequest(myCard);
        when(bankService.getCardBlockStatus("15151515")).thenReturn(true);

        String expected = "Card is blocked, you cannot login";
        String actual = myATMservice.getCardStatus(_ATMrequest);

        assertEquals(expected,actual);
    }

    @Test
    public void checkIfCardIsNotBlockedWhenInserted(){
        CardModel myCard = new CardModel("15151515");

        ATMrequest _ATMrequest = new ATMrequest(myCard);
        when(bankService.getCardBlockStatus("15151515")).thenReturn(false);

        String expected = "Card is not blocked, login allowed";
        String actual = myATMservice.getCardStatus(_ATMrequest);

        assertEquals(expected,actual);
    }

    @Test
    public void getBalanceWhenCardIsLoggedIn(){
        CardModel myCard = new CardModel("1515","15151515","Tallner");
        myCard.setLoginStatus(true);
        ATMrequest _ATMrequest = new ATMrequest(myCard,"1515");

        int expected = 3000;
        when(bankService.getBalance("15151515")).thenReturn(expected);

        int actual = myATMservice.getBalance(_ATMrequest);

        assertEquals(expected,actual);
    }

    @Test
    public void addMoneyToAccountAndVerifyMethodIsRun(){
        CardModel myCard = new CardModel("1515","15151515","Tallner");
        int amountToAddToBalance = 500;
        ATMrequest _ATMrequest = new ATMrequest(myCard,"1515",amountToAddToBalance,0);

        myATMservice.addToBalance(_ATMrequest);

        verify(bankService,times(1)).addToBalance(addToBalanceArgumentCaptor.capture());

        assertEquals(amountToAddToBalance,addToBalanceArgumentCaptor.getValue());
    }

    @Test
    public void withdrawMoneyFromAccountAndVerifyMethodIsRun(){
        CardModel myCard = new CardModel("1515","15151515","Tallner");
        myCard.setLoginStatus(true);

        int amountToWithdrawFromBalance = 500;
        ATMrequest _ATMrequest = new ATMrequest(myCard,"1515",0,amountToWithdrawFromBalance);

        when(bankService.getBalance("15151515")).thenReturn(3000);
        myATMservice.withdrawFromBalance(_ATMrequest);

        verify(bankService,times(1)).withdrawFromBalance(withdrawBalanceArgumentCaptor.capture());

        assertEquals(amountToWithdrawFromBalance,withdrawBalanceArgumentCaptor.getValue());
    }

}