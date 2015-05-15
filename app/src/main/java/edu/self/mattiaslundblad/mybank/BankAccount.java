package edu.self.mattiaslundblad.mybank;

import android.widget.EditText;

/**
 * Created by mattiaslundblad on 2015-05-14.
 */

public class BankAccount {

  private static final double OVERDRAFT_FEE = 30;

  private double balance;

  private void setBalance(double balance) {
    this.balance = balance;
  }

  public double getBalance() {
    return balance;
  }

  public BankAccount(double balance) {
    setBalance(balance);
  }

  public BankAccount() {
    setBalance(0);
  }

  public void withdraw(double amount, EditText input) {
    balance -= amount;

    if (balance < 0) {
      balance -= OVERDRAFT_FEE;
      input.setHint("Overdraft fee charged");
    } else {
      input.setHint("Withdrew " + String.valueOf(amount));
    }

    normalizeBalance();
  }

  public void deposit(double amount, EditText input) {
    double depositBonusPercent = 0.0;

    if (balance > 0) {
      depositBonusPercent = balance / 1000;  // rich get richer am i right...
    }
    balance += (amount + (amount * depositBonusPercent));
    normalizeBalance();
    
    input.setHint("Deposited " + String.valueOf(amount) + " (Bonus " + (amount * depositBonusPercent) + ")" );
  }

  private void normalizeBalance() {
    balance = (double)Math.round(balance * 100) / 100;
  }
}
