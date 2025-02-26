package com.example.ezybanking;


import android.telephony.SmsManager;

import java.util.Random;

public class accNumGenerator {

    private int accNum;
    Random random = new Random();

    accNumGenerator(){
        accNum = 110000000 + random.nextInt(900000);
    }


    int getAccNum(){
        return accNum;
    }


    // Sending the generated account number

/*    public void sendAccNum(String phone){
        // Code to send the generated account number to the recipient email


        String message = "This is a test SMS sent via Java! Your account number is " + accNum;
        String phoneNumber = phone;

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            System.out.println("SMS sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("SMS failed to send.");
        }
    }*/

}
