
package com.example.ezybanking;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class transferFunds extends AppCompatActivity {

    balanceGenerator bg = new balanceGenerator();

    static String accNum;

    private String selectedFromAccounts, selectedToAccounts;
    private TextView fromAccountError, toAccountError;
    private Spinner fromAccountSpinner, toAccountSpinner;
    private ArrayAdapter<CharSequence> fromAccAdapter, toAccAdapter;


    static double mainExtra;
    static double saveExtra;
    static double randExtra;

    static double mainValue;
    static double saveValue;
    static double randValue;

    static double passMain;
    static double passSave;
    static double passRand;

    static String homeIndicator = "none";
    static String transferIndicator;


    static String cn;
    static String uNum;
    static String date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_funds);



        fromAccountSpinner = findViewById(R.id.from_acc_dropdown_menu);
        toAccountSpinner = findViewById(R.id.to_acc_dropdown_menu);
//        fromAccountError = findViewById(R.id.from_account_error_text); // Dedicated error TextView
//        toAccountError = findViewById(R.id.to_account_error_text);

        // Set up adapter
        fromAccAdapter = ArrayAdapter.createFromResource(this, R.array.from_acc_dropdown_menu, R.layout.accounts_list);
        fromAccAdapter.setDropDownViewResource(R.layout.accounts_list);
        fromAccountSpinner.setAdapter(fromAccAdapter);


        fromAccountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedFromAccounts = fromAccountSpinner.getSelectedItem().toString();
                switch (selectedFromAccounts) {

                    case "Select Account":
                        toAccAdapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.default_options, R.layout.accounts_list);
                        break;

                    case "Main Account":
                        toAccAdapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.main_options, R.layout.accounts_list);
                        break;

                    case "Savings Account":
                        toAccAdapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.savings_options, R.layout.accounts_list);
                        break;

                    case "Random Account":
                        toAccAdapter = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.random_options, R.layout.accounts_list);
                        break;

                    default:
                        break;
                }

                toAccAdapter.setDropDownViewResource(R.layout.accounts_list);
                toAccountSpinner.setAdapter(toAccAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });




        Button transferBtn = findViewById(R.id.transfer_btn);
        transferBtn.setOnClickListener(v -> {
            selectedToAccounts = toAccountSpinner.getSelectedItem().toString();


            // ===========================CHECKING IF THEY SELECTED ANY ACCOUNT==========================

            if (selectedFromAccounts.equalsIgnoreCase("Select Account") || selectedToAccounts.equalsIgnoreCase("Select Account")) {
                Toast.makeText(this, "Please account.", Toast.LENGTH_SHORT).show();
            }


            // ====================THEY SELECTED THE ACCOUNT========================

            else {

                String value = ((EditText)findViewById(R.id.amountInput)).getText().toString();


                // ====================CHECKING IF THEY ENTERED THE AMOUNT========================

                if (value.isEmpty()){
                Toast.makeText(this, "ENTER AMOUNT.", Toast.LENGTH_SHORT).show();
                }


                // =============================THEY ENTERED THE AMOUNT=================================

                else{

                    bg.setValues(mainExtra, saveExtra, randExtra);


                    double amount = Double.parseDouble(value);



                    // ============================= CHOOSING THE ACCOUNT TO SEND MONEY FROM =================================

                    if (selectedFromAccounts.equalsIgnoreCase("Main Account")) {


                        // ============================= TRANSFER MONEY BASED ON THE SELECTED ACCOUNT============================

                        double mainB = mainExtra;
                        switch (selectedToAccounts){

                            //========================= MONEY FROM MAIN ACCOUNT TO ACCOUNT SELECTED ===============================

                            case "Savings Account":
                                //========================= MONEY FROM MAIN ACCOUNT TO SAVINGS ACCOUNT ===============================

                                 if (amount <= 0) {
                                    Toast.makeText(this,"ENTER A VALID AMOUNT!!!", Toast.LENGTH_SHORT).show();
                                }

                                else if (amount > mainB) {
                                    Toast.makeText(this,"INSUFFICIENT FUNDS", Toast.LENGTH_SHORT).show();
                                }

                                else if (amount <= mainB) {

                                    saveValue = bg.fromMainToSave(amount);
                                    mainValue = bg.getNewMainBalanceAfterSave();

                                    passMain = mainValue;
                                    passSave = saveValue;
                                    passRand = randExtra;

                                    homeIndicator = "main/save";

                                    Toast.makeText(this, amount +  " has been added to " + selectedToAccounts  , Toast.LENGTH_SHORT).show();
                                }

                                break;



                                //========================= MONEY FROM MAIN ACCOUNT TO RANDOM ACCOUNT ===============================

                            case "Random Account":
                                //========================= MONEY FROM MAIN ACCOUNT TO RANDOM ACCOUNT ===============================

                                 if (amount <= 0) {
                                    Toast.makeText(this,"ENTER A VALID AMOUNT!!!", Toast.LENGTH_SHORT).show();
                                }

                                else if (amount > mainB) {
                                    Toast.makeText(this,"INSUFFICIENT FUNDS", Toast.LENGTH_SHORT).show();
                                }

                                else if (amount <= mainB) {

                                    randValue = bg.fromMainToRand(amount);
                                    mainValue = bg.getNewMainBalanceAfterRand();

                                    passMain = mainValue;
                                    passSave = saveExtra;
                                    passRand = randValue;

                                    homeIndicator = "main/rand";

                                    Toast.makeText(this, amount +  " has been added to " + selectedToAccounts  , Toast.LENGTH_SHORT).show();
                                }

                                break;
                        }

                    }








                    //========================= MONEY FROM SAVINGS ACCOUNT TO ACCOUNT SELECTED ===============================

                    else if (selectedFromAccounts.equalsIgnoreCase("Savings Account")) {
                        double saveB = saveExtra;
                        switch (selectedToAccounts){

                            //========================= MONEY FROM SAVINGS ACCOUNT TO MAIN ACCOUNT ===============================
                            case "Main Account":

                                if (amount <= 0) {
                                    Toast.makeText(this,"ENTER A VALID AMOUNT!!!", Toast.LENGTH_SHORT).show();
                                }

                                else if (amount > saveB) {
                                    Toast.makeText(this,"INSUFFICIENT FUNDS", Toast.LENGTH_SHORT).show();
                                }

                                else if (amount <= saveB) {

                                    mainValue = bg.fromSaveToMain(amount);
                                    saveValue = bg.getNewSaveBalanceAfterMain();

                                    passMain = mainValue;
                                    passSave = saveValue;
                                    passRand = randExtra;

                                    homeIndicator = "main/save";

                                    Toast.makeText(this, amount +  " has been added to " + selectedToAccounts  , Toast.LENGTH_SHORT).show();
                                }


                                break;



                                //========================= MONEY FROM SAVINGS ACCOUNT TO RANDOM ACCOUNT ===============================
                            case "Random Account":

                                if (amount <= 0) {
                                    Toast.makeText(this,"ENTER A VALID AMOUNT!!!", Toast.LENGTH_SHORT).show();
                                }

                                else if (amount > saveB) {
                                    Toast.makeText(this,"INSUFFICIENT FUNDS", Toast.LENGTH_SHORT).show();
                                }

                                else if (amount <= saveB) {

                                    randValue = bg.fromSaveToRand(amount);
                                    saveValue = bg.getNewSaveBalanceAfterRand();

                                    passMain = mainExtra;
                                    passSave = saveValue;
                                    passRand = randValue;

                                    homeIndicator = "save/rand";

                                    Toast.makeText(this, amount +  " has been added to " + selectedToAccounts  , Toast.LENGTH_SHORT).show();
                                }
                                break;
                        }

                    }


                    //========================= MONEY FROM RANDOM ACCOUNT TO ACCOUNT SELECTED ===============================
                    else if (selectedFromAccounts.equalsIgnoreCase("Random Account")) {

                        double randB = randExtra;
                        switch (selectedToAccounts){

                            //========================= MONEY FROM RANDOM ACCOUNT TO MAIN ACCOUNT ===============================
                            case "Main Account":

                                if (amount <= 0) {
                                    Toast.makeText(this,"ENTER A VALID AMOUNT!!!", Toast.LENGTH_SHORT).show();
                                }

                                else if (amount > randB) {
                                    Toast.makeText(this,"INSUFFICIENT FUNDS", Toast.LENGTH_SHORT).show();
                                }

                                else if (amount <= randB) {

                                    mainValue = bg.fromRandToMain(amount);
                                    randValue = bg.getNewRandBalanceAfterMain();

                                    passMain = mainValue;
                                    passSave = saveExtra;
                                    passRand = randValue;

                                    homeIndicator = "main/rand";

                                    Toast.makeText(this, amount +  " has been added to " + selectedToAccounts  , Toast.LENGTH_SHORT).show();
                                }


                                break;



                                //========================= MONEY FROM RANDOM ACCOUNT TO SAVINGS ACCOUNT ===============================
                            case "Savings Account":

                                if (amount <= 0) {
                                    Toast.makeText(this,"ENTER A VALID AMOUNT!!!", Toast.LENGTH_SHORT).show();
                                }

                                else if (amount > randB) {
                                    Toast.makeText(this,"INSUFFICIENT FUNDS", Toast.LENGTH_SHORT).show();
                                }

                                else if (amount <= randB) {

                                    saveValue = bg.fromRandToSave(amount);
                                    randValue = bg.getNewRandBalanceAfterSave();

                                    passMain = mainExtra;
                                    passSave = saveValue;
                                    passRand = randValue;

                                    homeIndicator = "save/rand";

                                    Toast.makeText(this, amount +  " has been added to " + selectedToAccounts  , Toast.LENGTH_SHORT).show();
                                }
                                break;


                        }

                    }

                }

            }

        });

        Intent i = getIntent();
        String AN = i.getStringExtra("accNum");

        accNum = AN;

        mainExtra = Double.parseDouble(Objects.requireNonNull(i.getStringExtra("MAIN")));
        saveExtra = Double.parseDouble(Objects.requireNonNull(i.getStringExtra("SAVE")));
        randExtra = Double.parseDouble(Objects.requireNonNull(i.getStringExtra("RAND")));

        final String cardNum = i.getStringExtra("cn");
        final String uniqueNum = i.getStringExtra("uNum");
        final String expDate = i.getStringExtra("date");


        cn = cardNum;
        uNum = uniqueNum;
        date = expDate;


    }



    public void homeBtn(View v) {
        Intent i = new Intent(this, accessAccount.class);
        DecimalFormat df = new DecimalFormat("0.00");
        i.putExtra("transferred","true");
        i.putExtra("new" , "true");
        i.putExtra("accNum", accNum);

        switch (homeIndicator) {
            case "main/save":
                passMain = Double.parseDouble(df.format(mainValue));
                passSave = Double.parseDouble(df.format(saveValue));
                passRand = Double.parseDouble(df.format(randExtra));

                i.putExtra("MAIN", String.valueOf(passMain));
                i.putExtra("SAVE", String.valueOf(passSave));
                i.putExtra("RAND", String.valueOf(passRand));
                break;
            case "main/rand":

                passMain = Double.parseDouble(df.format(mainValue));
                passSave = Double.parseDouble(df.format(saveExtra));
                passRand = Double.parseDouble(df.format(randValue));

                i.putExtra("MAIN", String.valueOf(passMain));
                i.putExtra("SAVE", String.valueOf(passSave));
                i.putExtra("RAND", String.valueOf(passRand));
                break;
            case "save/rand":

                passMain = Double.parseDouble(df.format(mainExtra));
                passSave = Double.parseDouble(df.format(saveValue));
                passRand = Double.parseDouble(df.format(randValue));

                i.putExtra("MAIN", String.valueOf(passMain));
                i.putExtra("SAVE", String.valueOf(passSave));
                i.putExtra("RAND", String.valueOf(passRand));
                break;
            case "none":

                passMain = Double.parseDouble(df.format(mainExtra));
                passSave = Double.parseDouble(df.format(saveExtra));
                passRand = Double.parseDouble(df.format(randExtra));

                i.putExtra("MAIN", String.valueOf(passMain));
                i.putExtra("SAVE", String.valueOf(passSave));
                i.putExtra("RAND", String.valueOf(passRand));

                break;
        }

        i.putExtra("cn", cn);
        i.putExtra("uNum", uNum);
        i.putExtra("date", date);

        startActivity(i);

    }



    public void cardBtn(View v) {
        Intent i = new Intent(this, displayCards.class);
        DecimalFormat df = new DecimalFormat("0.00");
        i.putExtra("transferred","true");
        i.putExtra("accNum", accNum);

        switch (homeIndicator) {
            case "main/save":
                passMain = Double.parseDouble(df.format(mainValue));
                passSave = Double.parseDouble(df.format(saveValue));
                passRand = Double.parseDouble(df.format(randExtra));

                i.putExtra("MAIN", String.valueOf(passMain));
                i.putExtra("SAVE", String.valueOf(passSave));
                i.putExtra("RAND", String.valueOf(passRand));
                break;
            case "main/rand":

                passMain = Double.parseDouble(df.format(mainValue));
                passSave = Double.parseDouble(df.format(saveExtra));
                passRand = Double.parseDouble(df.format(randValue));

                i.putExtra("MAIN", String.valueOf(passMain));
                i.putExtra("SAVE", String.valueOf(passSave));
                i.putExtra("RAND", String.valueOf(passRand));
                break;
            case "save/rand":

                passMain = Double.parseDouble(df.format(mainExtra));
                passSave = Double.parseDouble(df.format(saveValue));
                passRand = Double.parseDouble(df.format(randValue));

                i.putExtra("MAIN", String.valueOf(passMain));
                i.putExtra("SAVE", String.valueOf(passSave));
                i.putExtra("RAND", String.valueOf(passRand));
                break;
            case "none":

                passMain = Double.parseDouble(df.format(mainExtra));
                passSave = Double.parseDouble(df.format(saveExtra));
                passRand = Double.parseDouble(df.format(randExtra));

                i.putExtra("MAIN", String.valueOf(passMain));
                i.putExtra("SAVE", String.valueOf(passSave));
                i.putExtra("RAND", String.valueOf(passRand));

                break;
        }

        i.putExtra("cn", cn);
        i.putExtra("uNum", uNum);
        i.putExtra("date", date);

        startActivity(i);

    }


    public void transactBtn(View v) {
        Intent i = new Intent(this, transactionPage.class);
        DecimalFormat df = new DecimalFormat("0.00");
        i.putExtra("transferred","true");
        i.putExtra("accNum", accNum);

        switch (homeIndicator) {
            case "main/save":
                passMain = Double.parseDouble(df.format(mainValue));
                passSave = Double.parseDouble(df.format(saveValue));
                passRand = Double.parseDouble(df.format(randExtra));

                i.putExtra("MAIN", String.valueOf(passMain));
                i.putExtra("SAVE", String.valueOf(passSave));
                i.putExtra("RAND", String.valueOf(passRand));
                break;
            case "main/rand":

                passMain = Double.parseDouble(df.format(mainValue));
                passSave = Double.parseDouble(df.format(saveExtra));
                passRand = Double.parseDouble(df.format(randValue));

                i.putExtra("MAIN", String.valueOf(passMain));
                i.putExtra("SAVE", String.valueOf(passSave));
                i.putExtra("RAND", String.valueOf(passRand));
                break;
            case "save/rand":

                passMain = Double.parseDouble(df.format(mainExtra));
                passSave = Double.parseDouble(df.format(saveValue));
                passRand = Double.parseDouble(df.format(randValue));

                i.putExtra("MAIN", String.valueOf(passMain));
                i.putExtra("SAVE", String.valueOf(passSave));
                i.putExtra("RAND", String.valueOf(passRand));
                break;
            case "none":

                passMain = Double.parseDouble(df.format(mainExtra));
                passSave = Double.parseDouble(df.format(saveExtra));
                passRand = Double.parseDouble(df.format(randExtra));

                i.putExtra("MAIN", String.valueOf(passMain));
                i.putExtra("SAVE", String.valueOf(passSave));
                i.putExtra("RAND", String.valueOf(passRand));

                break;
        }

        i.putExtra("cn", cn);
        i.putExtra("uNum", uNum);
        i.putExtra("date", date);

        startActivity(i);

    }


    public void msgBtn(View v) {

        Intent i = new Intent(this, messagePage.class);
        DecimalFormat df = new DecimalFormat("0.00");
        i.putExtra("transferred","true");
        i.putExtra("accNum", accNum);

        switch (homeIndicator) {
            case "main/save":
                passMain = Double.parseDouble(df.format(mainValue));
                passSave = Double.parseDouble(df.format(saveValue));
                passRand = Double.parseDouble(df.format(randExtra));

                i.putExtra("MAIN", String.valueOf(passMain));
                i.putExtra("SAVE", String.valueOf(passSave));
                i.putExtra("RAND", String.valueOf(passRand));
                break;
            case "main/rand":

                passMain = Double.parseDouble(df.format(mainValue));
                passSave = Double.parseDouble(df.format(saveExtra));
                passRand = Double.parseDouble(df.format(randValue));

                i.putExtra("MAIN", String.valueOf(passMain));
                i.putExtra("SAVE", String.valueOf(passSave));
                i.putExtra("RAND", String.valueOf(passRand));
                break;
            case "save/rand":

                passMain = Double.parseDouble(df.format(mainExtra));
                passSave = Double.parseDouble(df.format(saveValue));
                passRand = Double.parseDouble(df.format(randValue));

                i.putExtra("MAIN", String.valueOf(passMain));
                i.putExtra("SAVE", String.valueOf(passSave));
                i.putExtra("RAND", String.valueOf(passRand));
                break;
            case "none":

                passMain = Double.parseDouble(df.format(mainExtra));
                passSave = Double.parseDouble(df.format(saveExtra));
                passRand = Double.parseDouble(df.format(randExtra));

                i.putExtra("MAIN", String.valueOf(passMain));
                i.putExtra("SAVE", String.valueOf(passSave));
                i.putExtra("RAND", String.valueOf(passRand));

                break;
        }

        i.putExtra("cn", cn);
        i.putExtra("uNum", uNum);
        i.putExtra("date", date);

        startActivity(i);

        Toast.makeText(this, "Messages", Toast.LENGTH_SHORT).show();
    }

    public void moreBtn(View v) {

        /*Intent i = new Intent(this, morePage.class);
        DecimalFormat df = new DecimalFormat("0.00");
        i.putExtra("transferred","true");
        i.putExtra("accNum", accNum);

        if (homeIndicator.equals("main/save")) {
            passMain = Double.parseDouble(df.format(mainValue));
            passSave = Double.parseDouble(df.format(saveValue));
            passRand = Double.parseDouble(df.format(randExtra));

            i.putExtra("MAIN", String.valueOf(passMain));
            i.putExtra("SAVE", String.valueOf(passSave));
            i.putExtra("RAND", String.valueOf(passRand));
        }

        else if (homeIndicator.equals("main/rand")) {

            passMain = Double.parseDouble(df.format(mainValue));
            passSave = Double.parseDouble(df.format(saveExtra));
            passRand = Double.parseDouble(df.format(randValue));

            i.putExtra("MAIN", String.valueOf(passMain));
            i.putExtra("SAVE", String.valueOf(passSave));
            i.putExtra("RAND", String.valueOf(passRand));
        }

        else if (homeIndicator.equals("save/rand")) {

            passMain = Double.parseDouble(df.format(mainExtra));
            passSave = Double.parseDouble(df.format(saveValue));
            passRand = Double.parseDouble(df.format(randValue));

            i.putExtra("MAIN", String.valueOf(passMain));
            i.putExtra("SAVE", String.valueOf(passSave));
            i.putExtra("RAND", String.valueOf(passRand));
        }


        else if (homeIndicator.equals("none")) {

            passMain = Double.parseDouble(df.format(mainExtra));
            passSave = Double.parseDouble(df.format(saveExtra));
            passRand = Double.parseDouble(df.format(randExtra));

            i.putExtra("MAIN", String.valueOf(passMain));
            i.putExtra("SAVE", String.valueOf(passSave));
            i.putExtra("RAND", String.valueOf(passRand));

        }

        i.putExtra("cn", cn);
        i.putExtra("uNum", uNum);
        i.putExtra("date", date);

        startActivity(i);*/


        Toast.makeText(this, "More", Toast.LENGTH_SHORT).show();
    }





}



















