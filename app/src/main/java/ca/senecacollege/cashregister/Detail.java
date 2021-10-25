package ca.senecacollege.cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Detail extends AppCompatActivity {
    HistoryM currentHistoryItem;

    TextView view_product, view_price, view_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        view_product = findViewById(R.id.product_name_d);
        view_price = findViewById(R.id.product_price_d);
        view_date = findViewById(R.id.product_date_d);

        if(getIntent().hasExtra("oneItem")){
            currentHistoryItem = getIntent().getParcelableExtra("oneItem");

            view_product.setText("Product: " + currentHistoryItem.getM_name());
            view_price.setText("Price: " + currentHistoryItem.getM_price());
            view_date.setText("Purchase Date: " + currentHistoryItem.getM_purchaseDate());
        }
    }
}