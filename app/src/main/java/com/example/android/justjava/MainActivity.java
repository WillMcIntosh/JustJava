package com.example.android.justjava;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        if (quantity < 1 || quantity > 100) {
            Context context = getApplicationContext();
            CharSequence text = "Please select an amount between \n\t\t\t\t" +
                    "1 and 100 cups of coffee.";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
        }
        else {
            CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream);
            boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

            CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
            boolean hasChocolate = chocolateCheckBox.isChecked();

            EditText nameEntry = (EditText) findViewById(R.id.name_entry);
            String userName = nameEntry.getText().toString();

            int price = calculatePrice(hasWhippedCream, hasChocolate);
            String displayMessage = createOrderSummary(price, userName);
            String emailMessage = createOrderSummary(price, userName, hasWhippedCream, hasChocolate);

//            emailOrder(emailMessage);
            displayMessage(displayMessage);
        }

    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {

        }
        else {
            quantity = quantity + 1;
            display(quantity);
            CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream);
            boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

            CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
            boolean hasChocolate = chocolateCheckBox.isChecked();

            displayPrice(calculatePrice(hasWhippedCream, hasChocolate));
        }

    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            return;
        }
        else {
            quantity = quantity - 1;
            display(quantity);
            CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream);
            boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

            CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
            boolean hasChocolate = chocolateCheckBox.isChecked();

            displayPrice(calculatePrice(hasWhippedCream, hasChocolate));
        }

    }

    /*
     * Calculate and return price of order
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = 5; // price of one cup of coffee
        if (hasWhippedCream) { // whipped cream adds $1
            basePrice += 1;
        }
        if (hasChocolate) { // chocolate adds $2
            basePrice += 2;
        }

        return quantity * basePrice;
    }

    /*
     * Create summary of order
     * @param price of the order
     * @param addWhipped cream for whether the user wants whipped cream or not
     *
     * @return text summary
     */

    private String createOrderSummary(int price, String userName) {
        return  "Order sent for " + userName + "\n" +
                "Total: $" + price + "\n" +
                "Thank you!";
    }

    private String createOrderSummary(int price, String userName, boolean hasWhippedCream, boolean hasChocolate) {
        return  "Name: " + userName + "\n" +
                "Add Whipped Cream? " + hasWhippedCream + "\n" +
                "Add Chocolate? " + hasChocolate + "\n" +
                "Quantity: " + quantity + "\n" +
                "Total: $" + price + "\n" +
                "Thank you!";
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance(Locale.US).format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}