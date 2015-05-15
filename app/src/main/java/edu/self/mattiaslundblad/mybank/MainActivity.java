package edu.self.mattiaslundblad.mybank;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

  private static final String TAG = "MainActivity";

  private EditText editTextAmount;
  private Button buttonWithdraw;
  private Button buttonDeposit;
  private TextView textViewBalance;
  private BankAccount myAccount;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getViewElements();
    createViewListeners();

    myAccount = new BankAccount(100.0);

    textViewBalance.setText(String.valueOf(myAccount.getBalance()));
  }

  private void getViewElements() {
    textViewBalance = getViewElement(R.id.textViewBalance);
    buttonDeposit = getViewElement(R.id.buttonDeposit);
    buttonWithdraw = getViewElement(R.id.buttonWithdraw);
    editTextAmount = getViewElement(R.id.editTextAmount);
  }

  @SuppressWarnings("unchecked")
  private <T> T getViewElement(int id) {
    return (T)findViewById(id);
  }

  private void createViewListeners() {

    buttonWithdraw.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (checkInput(editTextAmount)) {
          double amount = Double.parseDouble(editTextAmount.getText().toString());
          myAccount.withdraw(amount, editTextAmount);
        }

        updateViewElements();
      }
    });

    buttonDeposit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (checkInput(editTextAmount)) {
          double amount = Double.parseDouble(editTextAmount.getText().toString());
          myAccount.deposit(amount, editTextAmount);
        }
        updateViewElements();
      }
    });
  }

  private boolean checkInput(EditText editable) {
    if (editable.getText().toString().equals("")) {
      editable.setHint("Enter an amount first");
      return false;
    } else try {
      Double.parseDouble(editable.getText().toString());
      return true;
    } catch (NumberFormatException e) {
      editable.setHint("Not a valid amount");
      Log.d(TAG, e.getMessage());
      return false;
    }
  }

  private void updateViewElements() {
    editTextAmount.setText("");
    textViewBalance.setText(String.valueOf(myAccount.getBalance()));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
