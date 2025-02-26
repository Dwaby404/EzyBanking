package com.example.ezybanking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class messagePage extends AppCompatActivity {

    static String accNum;

    static String cn;
    static String uNum;
    static String date;


    static String main;
    static String save;
    static String randB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_message_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });





                Intent in = getIntent();

        // ==============CARD INFO ==============================
        final String CARD_NUM = in.getStringExtra("cn") ;
        final String UNIQUE_NUM = in.getStringExtra("uNum");
        final String EXP_DATE = in.getStringExtra("date");

        cn = CARD_NUM;
        uNum = UNIQUE_NUM;
        date = EXP_DATE;



        //========================== BALANCE HANDLING =========================
        final String mb = in.getStringExtra("MAIN");
        final String sb = in.getStringExtra("SAVE");
        final String rb = in.getStringExtra("RAND");
        String AN = in.getStringExtra("accNum");

        accNum = AN;

        main = mb;
        save = sb;
        randB = rb;






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
        Toast.makeText(this, "Messages", Toast.LENGTH_SHORT).show();
    }

    public void moreBtn(View v) {

        /*Intent i = new Intent(this, morePage.class);
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