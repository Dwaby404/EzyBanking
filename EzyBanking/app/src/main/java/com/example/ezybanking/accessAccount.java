package com.example.ezybanking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class accessAccount extends AppCompatActivity {

    static String accNum;

    static String mainAccBalance;
    static String savingsAccBalance;
    static String randomAccBalance;

    static boolean done;

    static String mainExtra;
    static String saveExtra;
    static String randExtra;

    balanceGenerator balanceGen = new balanceGenerator();
    cardDetails cd = new cardDetails();

    // Cards details

    String CARD_NUM;
    String UNIQUE_NUM;
    String EXP_DATE;

    String cn;
    String uNum;
    String date;




    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_access_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent i = getIntent();
        String indicator = i.getStringExtra("transferred");
        String AN = i.getStringExtra("accNum");
        String in = i.getStringExtra("new");

        accNum = AN;




       // if (in.equals("true")) {
            ((TextView) findViewById(R.id.header)).setText(accNum);
       /* }

        else if (in.equals("false")) {
            ((TextView) findViewById(R.id.header)).setText("no num " + in);
        }*/

        assert indicator != null;
        if (indicator.equals("true") || indicator.equals("active")){
            Intent intent = getIntent();

            mainExtra =  intent.getStringExtra("MAIN");
            ((TextView) findViewById(R.id.main_account_balance)).setText("R" + mainExtra);

            saveExtra = intent.getStringExtra("SAVE");
            ((TextView) findViewById(R.id.savings_account_balance)).setText("R" + saveExtra);

            randExtra = intent.getStringExtra("RAND");
            ((TextView) findViewById(R.id.random_account_balance)).setText("R" + randExtra);


            //============= CARD DETAILS =========================

            cn = i.getStringExtra("cn");
            uNum = i.getStringExtra("uNum");
            date = i.getStringExtra("date");



            done = true;
        }


        else {

        // Displaying the account number
            //((TextView) findViewById(R.id.header)).setText(num);



        // Adding balances

        //Main account balance
        mainAccBalance = String.valueOf(balanceGen.getMainBalance());
        ((TextView) findViewById(R.id.main_account_balance)).setText("R" + mainAccBalance);


        // Savings account balance
        savingsAccBalance = String.valueOf(balanceGen.getSavingsBalance());
        ((TextView) findViewById(R.id.savings_account_balance)).setText("R" + savingsAccBalance);


        // Random account balance
        randomAccBalance = String.valueOf(balanceGen.getRandBalance());
        ((TextView) findViewById(R.id.random_account_balance)).setText("R" + randomAccBalance);






        //============= CARD DETAILS =========================


        CARD_NUM = cd.getCardNum();
        UNIQUE_NUM = cd.getUniqueNum();
        EXP_DATE = cd.getExpDate();


        done = false;

        }



    }


    public void checkAccount(View v) {
        Intent i = new Intent(this, accountBalances.class);

        String type = String.valueOf(((ConstraintLayout)v).getId());
        i.putExtra("TYPE", type);
        i.putExtra("accNum", accNum);



        if (done) {
            i.putExtra("MAIN" , mainExtra);
            i.putExtra("SAVE" , saveExtra);
            i.putExtra("RAND" , randExtra);

            // ================CARD DETAILS =================
            i.putExtra("cn", cn);
            i.putExtra("uNum", uNum);
            i.putExtra("date", date);

        }

        else {

            i.putExtra("MAIN", mainAccBalance);
            i.putExtra("SAVE", savingsAccBalance);
            i.putExtra("RAND", randomAccBalance);

            // ================CARD DETAILS =================
            i.putExtra("cn", CARD_NUM);
            i.putExtra("uNum", UNIQUE_NUM);
            i.putExtra("date", EXP_DATE);
        }

        startActivity(i);

    }


    public void payBen(View v) {
        Toast.makeText(this, "Pay Beneficial", Toast.LENGTH_SHORT).show();
    }





    public void getTransferFundsActivity(View v) {
        Intent i = new Intent(this, transferFunds.class);
        i.putExtra("accNum", accNum);

        if (done) {
            i.putExtra("MAIN" , mainExtra);
            i.putExtra("SAVE" , saveExtra);
            i.putExtra("RAND" , randExtra);

            // ================CARD DETAILS =================
            i.putExtra("cn", cn);
            i.putExtra("uNum", uNum);
            i.putExtra("date", date);

        }

        else {

            i.putExtra("MAIN", mainAccBalance);
            i.putExtra("SAVE", savingsAccBalance);
            i.putExtra("RAND", randomAccBalance);

            // ================CARD DETAILS =================
            i.putExtra("cn", CARD_NUM);
            i.putExtra("uNum", UNIQUE_NUM);
            i.putExtra("date", EXP_DATE);
        }

        //Toast.makeText(this, "TRANSFER MONEY", Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Transfer Money", Toast.LENGTH_SHORT).show();

        startActivity(i);
    }


        public void buyPrepaid(View v) {
        Toast.makeText(this, "Buy prepaid", Toast.LENGTH_SHORT).show();
    }


        public void buyElectric(View v) {
        Toast.makeText(this, "Buy electricity", Toast.LENGTH_SHORT).show();
    }
















    // Bottom buttons

        public void homeBtn(View v) {
        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
    }


    public void cardBtn(View v) {
        Intent i = new Intent(this, displayCards.class);
        i.putExtra("accNum", accNum);

        if (done) {
            // =============== BALANCES ====================
            i.putExtra("MAIN" , mainExtra);
            i.putExtra("SAVE" , saveExtra);
            i.putExtra("RAND" , randExtra);


            // ================CARD DETAILS =================
            i.putExtra("cn", cn);
            i.putExtra("uNum", uNum);
            i.putExtra("date", date);
        }

        else {
            // =============== BALANCES ====================
            i.putExtra("MAIN", mainAccBalance);
            i.putExtra("SAVE", savingsAccBalance);
            i.putExtra("RAND", randomAccBalance);


            // ================CARD DETAILS =================
            i.putExtra("cn", CARD_NUM);
            i.putExtra("uNum", UNIQUE_NUM);
            i.putExtra("date", EXP_DATE);

        }


        startActivity(i);
        Toast.makeText(this, "CARD", Toast.LENGTH_SHORT).show();
    }


    public void transactBtn(View v) {
        Intent i = new Intent(this, transactionPage.class);
        i.putExtra("accNum", accNum);

        if (done) {
            // =============== BALANCES ====================
            i.putExtra("MAIN" , mainExtra);
            i.putExtra("SAVE" , saveExtra);
            i.putExtra("RAND" , randExtra);


            // ================CARD DETAILS =================
            i.putExtra("cn", cn);
            i.putExtra("uNum", uNum);
            i.putExtra("date", date);
        }

        else {
            // =============== BALANCES ====================
            i.putExtra("MAIN", mainAccBalance);
            i.putExtra("SAVE", savingsAccBalance);
            i.putExtra("RAND", randomAccBalance);


            // ================CARD DETAILS =================
            i.putExtra("cn", CARD_NUM);
            i.putExtra("uNum", UNIQUE_NUM);
            i.putExtra("date", EXP_DATE);

        }


        startActivity(i);
        Toast.makeText(this, "Transact", Toast.LENGTH_SHORT).show();
    }


    public void msgBtn(View v) {

        Intent i = new Intent(this, messagePage.class);
        i.putExtra("accNum", accNum);

        if (done) {
            // =============== BALANCES ====================
            i.putExtra("MAIN" , mainExtra);
            i.putExtra("SAVE" , saveExtra);
            i.putExtra("RAND" , randExtra);


            // ================CARD DETAILS =================
            i.putExtra("cn", cn);
            i.putExtra("uNum", uNum);
            i.putExtra("date", date);
        }

        else {
            // =============== BALANCES ====================
            i.putExtra("MAIN", mainAccBalance);
            i.putExtra("SAVE", savingsAccBalance);
            i.putExtra("RAND", randomAccBalance);


            // ================CARD DETAILS =================
            i.putExtra("cn", CARD_NUM);
            i.putExtra("uNum", UNIQUE_NUM);
            i.putExtra("date", EXP_DATE);

        }

        startActivity(i);
        Toast.makeText(this, "Messages", Toast.LENGTH_SHORT).show();
    }

    public void moreBtn(View v) {
      /*  Intent i = new Intent(this, morePage.class);
        i.putExtra("accNum", accNum);

        if (done) {
            // =============== BALANCES ====================
            i.putExtra("MAIN" , mainExtra);
            i.putExtra("SAVE" , saveExtra);
            i.putExtra("RAND" , randExtra);


            // ================CARD DETAILS =================
            i.putExtra("cn", cn);
            i.putExtra("uNum", uNum);
            i.putExtra("date", date);
        }

        else {
            // =============== BALANCES ====================
            i.putExtra("MAIN", mainAccBalance);
            i.putExtra("SAVE", savingsAccBalance);
            i.putExtra("RAND", randomAccBalance);


            // ================CARD DETAILS =================
            i.putExtra("cn", CARD_NUM);
            i.putExtra("uNum", UNIQUE_NUM);
            i.putExtra("date", EXP_DATE);

        }

        startActivity(i);*/


        Toast.makeText(this, "More", Toast.LENGTH_SHORT).show();
    }









}