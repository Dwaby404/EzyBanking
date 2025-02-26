package com.example.ezybanking;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class nextSignUpPage extends AppCompatActivity {
    accNumGenerator accGen = new accNumGenerator();
    cardDetails cd = new cardDetails();

    private String name;
    private String surname;
    private String email;
    private String phone;
    private String accNum;
    private String CARD_NUM;
    private String UNIQUE_NUM;
    private String EXP_DATE;
    Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_next_sign_up_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent i = getIntent();

        name = i.getStringExtra("mame");
        surname = i.getStringExtra("surname");
        email = i.getStringExtra("email");
        phone = i.getStringExtra("phone");


    }



        public void displayAccNumber(View v) {

        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        String confPassword = ((EditText) findViewById(R.id.confirmedPassword)).getText().toString();


        if (password.isEmpty() && confPassword.isEmpty()) {
            Toast.makeText(this, "Fields Required", Toast.LENGTH_SHORT).show();
        }

         else if (password.isEmpty()) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
        }

        else if (confPassword.isEmpty()) {
            Toast.makeText(this, "Confirm password", Toast.LENGTH_SHORT).show();
        }

        else if (!password.equals(confPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }

        else {
            Intent i = new Intent(this, accessAccount.class);

            accNum = String.valueOf(accGen.getAccNum());
            CARD_NUM = cd.getCardNum();
            UNIQUE_NUM = cd.getUniqueNum();
            EXP_DATE = cd.getExpDate();



            i.putExtra("accNum" , accNum);
            i.putExtra("cn", CARD_NUM);
            i.putExtra("uNum", UNIQUE_NUM);
            i.putExtra("date", EXP_DATE);

            i.putExtra("transferred", "false");
            i.putExtra("new" , "true");


            startActivity(i);
            //Toast.makeText(this, "You successfully created an account", Toast.LENGTH_SHORT).show();
        }


    }







}