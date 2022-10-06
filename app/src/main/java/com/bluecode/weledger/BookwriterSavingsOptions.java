package com.bluecode.weledger;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BookwriterSavingsOptions extends AppCompatActivity {
    Toolbar toolbar;
    LinearLayout my_savings_option,group_savings_option,add_savings,edit_savings;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookwriter_savings_options);
        my_savings_option = findViewById(R.id.my_savings);
        group_savings_option = findViewById(R.id.group_savings);
        add_savings = findViewById(R.id.add_savings);
        edit_savings = findViewById(R.id.edit_savings);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Savings");
        toolbar.setSubtitle("Savings Options");
        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        my_savings_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MyTransactionsHistoryActivity.class);
                startActivity(intent);
            }
        });

        group_savings_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MyTransactionsHistoryActivity.class);
                startActivity(intent);
            }
        });
        add_savings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), NewPaymentActivity.class);
                startActivity(intent);
            }
        });
        edit_savings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Work In Progress.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_stuff, menu);


        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), NewPaymentActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
