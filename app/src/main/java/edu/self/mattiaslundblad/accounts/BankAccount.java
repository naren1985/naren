package edu.self.mattiaslundblad.accounts;

import java.util.ArrayList;

/**
 * Created by mattiaslundblad on 2015-05-14.
 */


public abstract class BankAccount {

  private static final double OVERDRAFT_FEE = 30.0;
  private ArrayList<Double> transactions;

  public double getBalance() {
    double total = 0.0;

    for (int i = 0; i < transactions.size(); i++) {
      total += transactions.get(i);
    }

    return total;
  }

  public BankAccount() {
    transactions = new ArrayList<>();
  }

  public String withdraw(double amount) {
    transactions.add(-amount);

    if (getBalance() < 0) {
      transactions.add(-OVERDRAFT_FEE);
      return "Overdraft fee charged";
    } else {
      return "Withdrew " + amount;
    }
  }

  public String deposit(double amount) {
    transactions.add(amount);
    return "Depostied " + amount;
  }

  protected int numberOfWithdrawals() {
    int count = 0;
    for (int i = 0; i < transactions.size(); i++) {
      if (Math.signum(transactions.get(i)) == -1.0) {
        count++;
      }
    }
    return count;
  }
}
