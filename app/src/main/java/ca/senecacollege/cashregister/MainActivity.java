package ca.senecacollege.cashregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_clear;
    Button btn_buy, btn_manager;

    TextView view_product, view_total, view_quantity;

    ListView productList;

    ProductAdapter productAdapter;
    static ArrayList<Product> listOfProducts;
    static ArrayList<HistoryM> listOfHistory;

    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // **** Creating References **** //

        view_product = (TextView) findViewById(R.id.textView_product);
        view_total = (TextView) findViewById(R.id.text_total);
        view_quantity = (TextView) findViewById(R.id.textView_quantity);

        btn_1 = (Button) findViewById(R.id.button_1);
        btn_2 = (Button) findViewById(R.id.button_2);
        btn_3 = (Button) findViewById(R.id.button_3);
        btn_4 = (Button) findViewById(R.id.button_4);
        btn_5 = (Button) findViewById(R.id.button_5);
        btn_6 = (Button) findViewById(R.id.button_6);
        btn_7 = (Button) findViewById(R.id.button_7);
        btn_8 = (Button) findViewById(R.id.button_8);
        btn_9 = (Button) findViewById(R.id.button_9);
        btn_0 = (Button) findViewById(R.id.button_0);
        btn_clear = (Button) findViewById(R.id.button_clear);

        btn_buy = (Button) findViewById(R.id.button_buy);
        btn_manager = (Button) findViewById(R.id.button_manager);

        productList = (ListView) findViewById(R.id.listView_product);

        // **** Adding Click Listener **** //

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_0.setOnClickListener(this);
        btn_clear.setOnClickListener(this);

        btn_buy.setOnClickListener(this);
        btn_manager.setOnClickListener(this);

        listOfHistory = new ArrayList<>();

        // Check for Save data
        if(savedInstanceState == null){

            listOfProducts = new ArrayList<>(3);

            listOfProducts.add(new Product("Pante", 10, 20.44));
            listOfProducts.add(new Product("Shoes", 100, 10.44));
            listOfProducts.add(new Product("Hats", 30, 5.9));

        }else{
            listOfProducts = savedInstanceState.getParcelableArrayList("list");
            listOfHistory = savedInstanceState.getParcelableArrayList("history");
        }

        productAdapter = new ProductAdapter(this, listOfProducts);
        productList.setAdapter(productAdapter);

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view_product.setText(listOfProducts.get(i).getM_name());

                 calculateTotal(i);

            }
        });

    }

    @Override
    public void onClick(View view) {

        String btn_value = ((Button)view).getText().toString(); // capture button text


        switch (btn_value){
            case "c":
                view_quantity.setText(R.string.s_text_quantity);
                view_total.setText(R.string.s_text_total);
                break;

            case "Buy":

                //validate
                if((view_quantity.getText().toString().contains("Quantity")) || (view_product.getText().toString().contains("Product Type"))){
                    Toast.makeText(MainActivity.this, "All fields are required!!!", Toast.LENGTH_LONG).show();
                }
                else {
                    for(int i = 0; i < listOfProducts.size(); i++){

                        if(view_product.getText().toString() == listOfProducts.get(i).getM_name()){

                            // Check available stock
                            if(Integer.parseInt(view_quantity.getText().toString()) > listOfProducts.get(i).getM_quantity()){

                                Toast.makeText(MainActivity.this, "Not enough quantity in the stock!!!", Toast.LENGTH_LONG).show();

                            }else{
                                listOfProducts.get(i).setM_quantitySub(Integer.parseInt(view_quantity.getText().toString()));

                                //update the list
                                productAdapter.notifyDataSetChanged();

                                // Confirmation message
                                builder = new AlertDialog.Builder(this);
                                builder.setTitle("Thank you for your purchase");
                                builder.setMessage("Your purchase is " + view_quantity.getText().toString() + " " + view_product.getText().toString() + " for " + view_total.getText().toString());

                                AlertDialog alert = builder.create();
                                alert.show();

                                // Add Purchase Product into [History]
                                String tempProduct = view_product.getText().toString();
                                int tempQuantity = Integer.parseInt(view_quantity.getText().toString());
                                Double tempPrice = Double.parseDouble(view_total.getText().toString());
                                String tempDate = (new Date()).toString();

                                listOfHistory.add(new HistoryM(tempProduct, tempQuantity, tempPrice, tempDate));

                                // Reset UI
                                view_product.setText(R.string.s_text_product);
                                view_total.setText(R.string.s_text_total);
                                view_quantity.setText(R.string.s_text_quantity);

                            }
                        }
                    }
                }
                break;

            case "Manager" :

                Intent myManagerIntent = new Intent(this,Manager.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("historyList", listOfHistory);
                myManagerIntent.putExtra("bundle", bundle);
                startActivity(myManagerIntent);
                break;

            default: //  Numbers
            {

                if(view_quantity.getText().toString().contains("Quantity")){
                    view_quantity.setText("");
                }
                view_quantity.append(btn_value);

                for(int i = 0; i < listOfProducts.size(); i++){

                    if(view_product.getText().toString() == listOfProducts.get(i).getM_name()){

                        calculateTotal(i);

                    }
                }
                break;
            }
        }
    }


    // Calculate Total
    void calculateTotal(int index){

        if(!(view_quantity.getText().toString().contains("Quantity")) && !(view_product.getText().toString().contains("Product Type"))){

            double tempPrice = listOfProducts.get(index).getM_price();
            double tempQuantity = Double.parseDouble(view_quantity.getText().toString());

            double total = tempPrice * tempQuantity;

            DecimalFormat dec = new DecimalFormat("#0.00");

            String totalFormatted = dec.format(total);

            view_total.setText(totalFormatted);

        }

    }

    // Save Data
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("list", listOfProducts);
        outState.putParcelableArrayList("history", listOfHistory);
    }

}