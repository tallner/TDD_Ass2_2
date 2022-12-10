package com.cte.services;

import com.cte.ATMrequest;
import com.cte.models.CardModel;
import com.cte.models.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.configuration.IMockitoConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ATMserviceTest {

    private BankService bankService;
    private ATMservice myATMservice;

    @BeforeEach
    public void setUp() {
        bankService = mock(BankService.class);
        myATMservice = new ATMservice(bankService);
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
        String cardNumberATMinput = PIN;
        CardModel myCard = new CardModel(PIN,cardNumber,userName);
        ATMrequest _ATMrequest = new ATMrequest(myCard,cardNumberATMinput);

        assertTrue(myATMservice.loginRequest(_ATMrequest));
    }
}