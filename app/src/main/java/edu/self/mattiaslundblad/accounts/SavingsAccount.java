package edu.self.mattiaslundblad.accounts;

/**
 * Created by mattiaslundblad on 2015-05-16.
 */

public class SavingsAccount extends BankAccount {

  @Override
  public String withdraw(double amount) {
    if (numberOfWithdrawals() <= 3) {
      return super.withdraw(amount);
    } else {
      return "No more withdrawals";
    }
  }
}
