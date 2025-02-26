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

public class signUpPage extends AppCompatActivity {

    accNumGenerator accNUm = new accNumGenerator();
    //DataBase db = new DataBase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }




    public void getNextSignUpPage(View v){
        // Code to handle user to next sign up page
        Intent i = new Intent(this, nextSignUpPage.class);

        String name = ((EditText) findViewById(R.id.name_input)).getText().toString();
        String surname = ((EditText) findViewById(R.id.surname_input)).getText().toString();
        String phone =  ((EditText)findViewById(R.id.phone_input)).getText().toString();
        String email =  ((EditText)findViewById(R.id.email_input)).getText().toString();


        if (name.isEmpty() && surname.isEmpty() && phone.isEmpty() && email.isEmpty()){
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        }

        else if (name.isEmpty()) {
            Toast.makeText(this, "Name field is empty", Toast.LENGTH_SHORT).show();
        }

        else if(surname.isEmpty()) {
            Toast.makeText(this, "Surname field is empty", Toast.LENGTH_SHORT).show();
        }

        else if(phone.isEmpty()) {
            Toast.makeText(this, "Phone field is empty", Toast.LENGTH_SHORT).show();
        }

        else if(email.isEmpty()) {
            Toast.makeText(this, "Email field is empty", Toast.LENGTH_SHORT).show();
        }

        else{
           // accNUm.sendAccNum(phone);

            //int num = accNUm.getAccNum();
           // db.addUser(name, surname, email, phone, String.valueOf(num));


            //i.putExtra("accNum", num);
            i.putExtra("mame", name);
            i.putExtra("surname", surname);
            i.putExtra("email", email);
            i.putExtra("phone", phone);


            startActivity(i);
            Toast.makeText(this, phone, Toast.LENGTH_SHORT).show();
        }

    }


}