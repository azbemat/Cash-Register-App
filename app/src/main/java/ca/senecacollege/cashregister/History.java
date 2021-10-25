package ca.senecacollege.cashregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;


public class History extends AppCompatActivity {

    ArrayList<HistoryM> listOfHistory;
    RecyclerView recyclerList;
    HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerList = (RecyclerView) findViewById(R.id.recyclerList_history);

        if(getIntent().hasExtra("bundle")){
            Bundle bundlefromMain = getIntent().getBundleExtra("bundle");
            listOfHistory = bundlefromMain.getParcelableArrayList("historyList");
        }

        recyclerList.setLayoutManager(new LinearLayoutManager(this));

        adapter = new HistoryAdapter(listOfHistory, this, new HistoryAdapter.OnItemClickListener() {
            @Override
            public void onHistoryItemClick(HistoryM item) {

                Intent intent = new Intent(History.this, Detail.class);
                intent.putExtra("oneItem", item);

                startActivity(intent);
            }
        });
        recyclerList.setAdapter(adapter);

    }

}