

package com.example.ezybanking;

import java.text.DecimalFormat;
import java.util.Random;

public class balanceGenerator {


    // Funds for newcomers
    Random rand = new Random();
    DecimalFormat df = new DecimalFormat("0.00");

    private double mainBalance = rand.nextInt(20000);
    private double savingsBalance = rand.nextInt(50000);
    private double randBalance = rand.nextInt(2000);

    private final double mainCents = Double.parseDouble(df.format(rand.nextDouble()));
    private final double saveCents = Double.parseDouble(df.format(rand.nextDouble()));
    private final double randCents = Double.parseDouble(df.format(rand.nextDouble()));


    double getMainBalance(){
        return mainBalance += mainCents ;
    }

    double getSavingsBalance(){
        return savingsBalance += saveCents;
    }

    double getRandBalance(){
        return randBalance += randCents;
    }






    // Controlling funds
    private double trMain;
    private double trSave;
    private double trRand;
    private double amount;

    private double updatedMainBalance;
    private double updatedSavingsBalance;
    private double updatedRandBalance;
    private double newBalance;

    void setValues(double m, double s, double r) {
        trMain = m;
        trSave = s;
        trRand = r;

    }

    // FROM MAIN ACCOUNT

    double fromMainToSave(double input) {
        amount = trMain - input;
        updatedSavingsBalance = trSave + input;
        setNewMainBalanceAfterSave(amount);

        return updatedSavingsBalance;
    }

    void setNewMainBalanceAfterSave(double x) {
        newBalance = x;
    }

    double getNewMainBalanceAfterSave() {
        return newBalance;
    }




    double fromMainToRand(double input) {
        amount = trMain - input;
        updatedRandBalance = trRand + input;
        setNewMainBalanceAfterRand(amount);

        return updatedRandBalance;
    }

    void setNewMainBalanceAfterRand(double x) {
        newBalance = x;
    }

    double getNewMainBalanceAfterRand() {
        return newBalance;
    }



    // FROM SAVINGS ACCOUNT

    double fromSaveToMain(double input) {
        amount = trSave - input;
        updatedMainBalance = trMain + input;
        setNewSaveBalanceAfterMain(amount);

        return updatedMainBalance;
    }

    void setNewSaveBalanceAfterMain(double x) {
        newBalance = x;
    }

    double getNewSaveBalanceAfterMain() {
        return newBalance;
    }




    double fromSaveToRand(double input) {
        amount = trSave - input;
        updatedRandBalance = trRand + input;
        setNewSaveBalanceAfterRand(amount);

        return updatedRandBalance;
    }

    void setNewSaveBalanceAfterRand(double x) {
        newBalance = x;
    }

    double getNewSaveBalanceAfterRand() {
        return newBalance;
    }




    // FROM RANDOM ACCOUNT

    double fromRandToMain(double input) {
        amount = trRand - input;
        updatedMainBalance = trMain + input;
        setNewRandBalanceAfterMain(amount);

        return updatedMainBalance;
    }

    void setNewRandBalanceAfterMain(double x) {
        newBalance = x;
    }

    double getNewRandBalanceAfterMain() {
        return newBalance;
    }




    double fromRandToSave(double input) {
        amount = trRand - input;
        updatedSavingsBalance = trSave + input;
        setNewRandBalanceAfterSave(amount);

        return updatedSavingsBalance;
    }

    void setNewRandBalanceAfterSave(double x) {
        newBalance = x;
    }

    double getNewRandBalanceAfterSave() {
        return newBalance;
    }




}







