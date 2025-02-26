package com.example.ezybanking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class accountBalances extends AppCompatActivity {
    static String accNum;

    static String cn;
    static String uNum;
    static String date;


    static String main;
    static String save;
    static String randB;



    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_balances);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Intent i = getIntent();

        String type = i.getStringExtra("TYPE");
        String mBalance = i.getStringExtra("MAIN");
        String sBalance = i.getStringExtra("SAVE");
        String rBalance = i.getStringExtra("RAND");
        String AN = i.getStringExtra("accNum");

        accNum = AN;


        switch (Objects.requireNonNull(type)) {
            case "2131296825":
                ((TextView) findViewById(R.id.acc_type)).setText("MAIN ACCOUNT");
                ((TextView) findViewById(R.id.acc_type_balance)).setText(mBalance);
                break;
            case "2131296828":
                ((TextView) findViewById(R.id.acc_type)).setText("SAVINGS ACCOUNT");
                ((TextView) findViewById(R.id.acc_type_balance)).setText(sBalance);
                break;
            case "2131296827":
                ((TextView) findViewById(R.id.acc_type)).setText("RANDOM ACCOUNT");
                ((TextView) findViewById(R.id.acc_type_balance)).setText(rBalance);
                break;
        }


        // ==============CARD INFO ==============================
        final String CARD_NUM = i.getStringExtra("cn") ;
        final String UNIQUE_NUM = i.getStringExtra("uNum");
        final String EXP_DATE = i.getStringExtra("date");

        cn = CARD_NUM;
        uNum = UNIQUE_NUM;
        date = EXP_DATE;



        //========================== BALANCE HANDLING =========================
        final String mb = i.getStringExtra("MAIN");
        final String sb = i.getStringExtra("SAVE");
        final String rb = i.getStringExtra("RAND");

        main = mb;
        save = sb;
        randB = rb;


    }





    //================ History Options =========================

    public void hOption(View v) {
        String choice = ((TextView) v).getText().toString();


        if (choice.equals("All")){
            Toast.makeText(this, choice, Toast.LENGTH_SHORT).show();
        }

        else if (choice.equals("Money In")){
            Toast.makeText(this, choice, Toast.LENGTH_SHORT).show();
        }

        else if (choice.equals("Money Out")){
            Toast.makeText(this, choice, Toast.LENGTH_SHORT).show();
        }



    }






















        // Bottom buttons

    public void homeBtn(View v) {
        Intent i = new Intent(this, accessAccount.class);
        i.putExtra("accNum", accNum);

        i.putExtra("cn", cn);
        i.putExtra("uNum", uNum);
        i.putExtra("date", date);


        i.putExtra("transferred","active");
        i.putExtra("new" , "true");
        i.putExtra("MAIN" , main);
        i.putExtra("SAVE" , save);
        i.putExtra("RAND" , randB);

        startActivity(i);


        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
    }


    public void cardBtn(View v) {
        Intent i = new Intent(this, displayCards.class);
        i.putExtra("accNum", accNum);

        i.putExtra("cn", cn);
        i.putExtra("uNum", uNum);
        i.putExtra("date", date);


        i.putExtra("transferred","active");
        i.putExtra("MAIN" , main);
        i.putExtra("SAVE" , save);
        i.putExtra("RAND" , randB);

        startActivity(i);
        Toast.makeText(this, "CARD", Toast.LENGTH_SHORT).show();
    }


    public void transactBtn(View v) {
        Intent i = new Intent(this, transactionPage.class);
        i.putExtra("accNum", accNum);

        i.putExtra("cn", cn);
        i.putExtra("uNum", uNum);
        i.putExtra("date", date);

        i.putExtra("transferred","active");
        i.putExtra("MAIN" , main);
        i.putExtra("SAVE" , save);
        i.putExtra("RAND" , randB);


        startActivity(i);


        Toast.makeText(this, "Transact", Toast.LENGTH_SHORT).show();
    }


    public void msgBtn(View v) {
        Intent i = new Intent(this, messagePage.class);
        i.putExtra("accNum", accNum);

        i.putExtra("cn", cn);
        i.putExtra("uNum", uNum);
        i.putExtra("date", date);


        i.putExtra("transferred","active");
        i.putExtra("MAIN" , main);
        i.putExtra("SAVE" , save);
        i.putExtra("RAND" , randB);

        startActivity(i);
        Toast.makeText(this, "Messages", Toast.LENGTH_SHORT).show();
    }

    public void moreBtn(View v) {

      /*  Intent i = new Intent(this, morePage.class);
        i.putExtra("accNum", accNum);

        i.putExtra("cn", cn);
        i.putExtra("uNum", uNum);
        i.putExtra("date", date);


        i.putExtra("transferred","active");
        i.putExtra("MAIN" , main);
        i.putExtra("SAVE" , save);
        i.putExtra("RAND" , randB);

        startActivity(i);*/

        Toast.makeText(this, "More", Toast.LENGTH_SHORT).show();
    }



}