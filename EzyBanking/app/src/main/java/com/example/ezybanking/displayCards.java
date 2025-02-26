package com.example.ezybanking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class displayCards extends AppCompatActivity {

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
        setContentView(R.layout.activity_display_cards);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent i = getIntent();


        final String cardNum = i.getStringExtra("cn");
        final String uniqueNum = i.getStringExtra("uNum");
        final String expDate = i.getStringExtra("date");
        String AN = i.getStringExtra("accNum");

        accNum = AN;

        ((TextView) findViewById(R.id.accNum_digits)).setText(accNum);
        ((TextView) findViewById(R.id.cadNum_digits)).setText(cardNum);
        ((TextView) findViewById(R.id.uniqueNum)).setText(uniqueNum);
        ((TextView) findViewById(R.id.expDate)).setText(expDate);

        cn = cardNum;
        uNum = uniqueNum;
        date = expDate;




        //========================== BALANCE HANDLING =========================
        final String mb = i.getStringExtra("MAIN");
        final String sb = i.getStringExtra("SAVE");
        final String rb = i.getStringExtra("RAND");

        main = mb;
        save = sb;
        randB = rb;



    }





    public void homeBtn(View v) {
         Intent i = new Intent(this, accessAccount.class);
        i.putExtra("cn", cn);
        i.putExtra("uNum", uNum);
        i.putExtra("date", date);

        i.putExtra("accNum", accNum);

        i.putExtra("transferred","active");
        i.putExtra("new" , "true");
        i.putExtra("MAIN" , main);
        i.putExtra("SAVE" , save);
        i.putExtra("RAND" , randB);

        startActivity(i);


        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
    }


    public void cardBtn(View v) {
        Toast.makeText(this, "Card", Toast.LENGTH_SHORT).show();
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

        /*Intent i = new Intent(this, morePage.class);
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






