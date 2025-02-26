package com.example.ezybanking;

import java.text.DecimalFormat;
import java.util.Random;

public class cardDetails {

    private int cardNum1, cardNum2, cardNum3, cardNum4;
    private int num;
    private int expMonth, expYear;

    private String cardNum;
    private String uniqueNum;
    private String expDate;

    Random rand = new Random();
    DecimalFormat df = new DecimalFormat("0000");
    DecimalFormat uNum = new DecimalFormat("000");
    DecimalFormat date = new DecimalFormat("00");

    cardDetails(){
        cardNum1 = rand.nextInt(9999);
        cardNum2 = rand.nextInt(9999);
        cardNum3 = rand.nextInt(9999);
        cardNum4 = rand.nextInt(9999);

        num = rand.nextInt(999);
        expMonth = rand.nextInt(12);
        expYear = rand.nextInt(40);

        if (expYear > 1 && expYear <= 5) {
            expYear += 26;
        }

        else if (expYear > 5 && expYear <= 10) {
            expYear += 22;
        }

        else if (expYear > 10 && expYear <= 15) {
            expYear += 17;
        }

        else if (expYear > 15 && expYear <= 20) {
            expYear += 11;
        }

        if (expMonth == 0){
            expMonth = 1;
        }

    }



    String getCardNum(){
        return cardNum = df.format(cardNum1) + " - " + df.format(cardNum2) + " - " + df.format(cardNum3) + " - " + df.format(cardNum4);
    }

    String getUniqueNum(){
        return uniqueNum = uNum.format(num);
    }

    String getExpDate(){
        return expDate ="(" + date.format(expMonth) + "/" + expYear + ")";
    }








}










