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
    if (balance - amount < 0) {
      balance -= (amount + OVERDRAFT_FEE);
      input.setHint("Overdraft fee charged");
    } else {
      balance -= amount;
      input.setHint("Withdrew " + String.valueOf(amount));
    }
    normalizeBalance();
  }

  public void deposit(double amount, EditText input) {
    double depositBonusPercent = balance / 1000;  // rich get richer am i right...
    balance += (amount + (amount * depositBonusPercent));
    normalizeBalance();
    input.setHint("Deposited " + String.valueOf(amount) + " (Bonus " + (amount * depositBonusPercent) + ")" );
  }

  private void normalizeBalance() {
    balance = (double)Math.round(balance * 100) / 100;
  }
}
