package com.example.ezybanking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



        public void logIn(View v){
        // Code to handle user login

            EditText accNumText = (EditText) findViewById(R.id.accNumber);
            EditText passwordText = (EditText) findViewById(R.id.loginPassword);


            accNumText.setTooltipText("Enter  characters");
            passwordText.setTooltipText("Enter 6 characters");





        String accNum = ((EditText) findViewById(R.id.accNumber)).getText().toString();
        String password = ((EditText) findViewById(R.id.loginPassword)).getText().toString();

        if (accNum.isEmpty() && password.isEmpty()) {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }

        else if (accNum.isEmpty()){
            Toast.makeText(this, "Enter your account number", Toast.LENGTH_SHORT).show();
        }

        else if (password.isEmpty()){
            Toast.makeText(this, "Enter your password", Toast.LENGTH_SHORT).show();
        }

        else {

            if (accNum.length() != 9 && password.length() != 6) {
                Toast.makeText(this, "INVALID DETAILS", Toast.LENGTH_SHORT).show();
            }

            else if (accNum.length() != 9) {
                Toast.makeText(this, "INVALID ACCOUNT NUMBER", Toast.LENGTH_SHORT).show();
            }

            else if (password.length() != 6) {
                Toast.makeText(this, "INVALID PASSWORD", Toast.LENGTH_SHORT).show();
            }

            else {
                Intent i = new Intent(this, accessAccount.class);
                i.putExtra("transferred", "false");
                i.putExtra("new", "false");
                i.putExtra("accNum", accNum);
                Toast.makeText(this, "Logging In", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }




        }

    }


        public void signUp(View v){
        // Code to handle user sign up
        Intent i = new Intent(this, signUpPage.class);
        startActivity(i);
    }

    public void forgotPassword(View v){
        Toast.makeText(this, "You forgot your password", Toast.LENGTH_SHORT).show();

    }






























}
