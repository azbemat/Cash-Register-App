package ca.senecacollege.cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Manager extends AppCompatActivity implements View.OnClickListener{

    Button btn_history;
    Bundle bundleFromMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        btn_history = (Button) findViewById(R.id.button_history);

        btn_history.setOnClickListener(this);

        if(getIntent().hasExtra("bundle")){
            bundleFromMain = getIntent().getBundleExtra("bundle");
        }

    }

    @Override
    public void onClick(View view) {

        String btn_value = ((Button)view).getText().toString(); // capture button text


        switch (btn_value){
            case "History":

                Intent myHistoryIntent = new Intent(this,History.class);
                myHistoryIntent.putExtra("bundle", bundleFromMain);

                startActivity(myHistoryIntent);
                break;
        }
    }

}