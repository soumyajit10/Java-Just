package com.example.javajust;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    int quantity=0;
    int value=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void menuShow (View view){
        Switch priceSwitch = findViewById(R.id.swichme);
        boolean SwitchMe =priceSwitch.getShowText();
        String viewMenu = "1cappachino $" + "10" +" and javachip $" + " 10 ";
        displayMenu(viewMenu);

    }
    private void displayMenu(String menu){
        TextView zeroZeroTextView=(TextView) findViewById(R.id.zero_zero_text_view);
        zeroZeroTextView.setText(menu);

    }

    public void submitOrder(View view) {
        CheckBox takeaway = (CheckBox) findViewById(R.id.takeaway_CheckBox);
        boolean hasTakeAway = takeaway.isChecked();

        CheckBox homedelivery = (CheckBox) findViewById(R.id.homedelivery_CheakBox);
                boolean hasHomeDelivery = homedelivery.isChecked();

                CheckBox dinein = (CheckBox) findViewById(R.id.dinein_CheckBox);
                boolean hasDinein =dinein.isChecked();

        EditText name_place = (EditText) findViewById(R.id.nameplace);
        String name = name_place.getText().toString();

        CheckBox paymentOption = (CheckBox) findViewById(R.id.payment_option);
        boolean paymentWay = paymentOption.isChecked();
        String payment = getString(R.string.thank_u );
        if (paymentWay==true){
           payment = getString(R.string.go_cashless) ;
        }

        CheckBox addedCream = (CheckBox) findViewById(R.id.cream);
        boolean AddedCream = addedCream.isChecked();

        int price = calculate(quantity ,value,AddedCream);
       String priceMessage = orderSummary(price,hasTakeAway,hasHomeDelivery,hasDinein,name,paymentWay,AddedCream);

       Intent intent = new Intent ( Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto"));
        intent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.just_java_order_to) + name );
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage );
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }

       displayMessage(priceMessage);

    }

    private String orderSummary(int price,boolean hasTakeAway,boolean hasHomeDelivery,boolean hasDinein,String name,boolean paymentWay,boolean AddedCream){

       String priceMessage = getString(R.string.customer) + name;
       priceMessage += getString(R.string.Total_amount) + "gst " + paymentWay;
        if (hasHomeDelivery == true){
       priceMessage += "\nhome delivery? "+ hasHomeDelivery;}
       else if (hasTakeAway == true){
        priceMessage += "\ntake away? "+ hasTakeAway;}
       else if (hasDinein == true){
        priceMessage += "\ndine in? "+ hasDinein;}
        return priceMessage;
    }
    private int calculate (int quantity ,int value,boolean AddedCream) {
        int coffePrice = 10;
        int SugerPrice = 2;
        if (quantity <=0){
            return 0 ;
        }
        if (AddedCream == true) {

            int price = quantity * coffePrice + value * SugerPrice + 4;
            return price;
        }
        else {
            int price = quantity * coffePrice + value * SugerPrice;
            return price;
        }
    }

    public void displayMessage(String message){
       TextView priceTextView=(TextView) findViewById(R.id.order_summery);
      priceTextView .setText(message);
    }
public void increment(View view){

         quantity=quantity+1;
         if (quantity>20){
             quantity = 20;
         }
         displayQuantity(quantity);
}
    public void decrement(View view){

        quantity=quantity-1;
        if (quantity < 0){
            quantity=0;
        }
        displayQuantity(quantity);
    }
public void show(View view){
        value=value+1;
        displayContent(value);

}
private void displayContent(int number){
        TextView limitTextView = (TextView) findViewById(R.id.limit);
        limitTextView.setText(""+number);
}
    private void displayQuantity(int number) {
        TextView zeroTextView = (TextView) findViewById(R.id.zero_text_view);
        zeroTextView.setText(""+number);
        zeroTextView.setAllCaps(true);
    }







}

