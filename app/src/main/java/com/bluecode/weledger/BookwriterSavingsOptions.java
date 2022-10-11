package com.bluecode.weledger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bluecode.weledger.adapters.BookWriterSavingAdapter;
import com.bluecode.weledger.adapters.MainActivityAdapter;
import com.bluecode.weledger.models.BookWriterAdminModel;
import com.bluecode.weledger.models.BookWriterSavingModel;
import com.bluecode.weledger.models.MainActivityModel;

import java.util.ArrayList;
import java.util.List;

public class BookwriterSavingsOptions extends AppCompatActivity {
    Toolbar toolbar;
    LinearLayout my_savings_option,group_savings_option,add_savings,edit_savings;
    String group_name;
    String group_id;
    ArrayList<BookWriterSavingModel> models = new ArrayList<>();
    RecyclerView recyclerView;
    BookWriterSavingAdapter bookWriterSavingAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookwriter_savings_options);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Savings");
        toolbar.setSubtitle("Savings Options");
        Bundle bundle = getIntent().getExtras();

        try {
            group_name = bundle.getString(Constants.GROUP_NAME,"Default");
            group_id = bundle.getString(Constants.GROUP_ID,"Default");
        }catch (Exception e){

            Log.e("Error","Attempt to invoke virtual method 'java.lang.String android.os.Bundle.getString(java.lang.String, java.lang.String)' on a null object reference ");
        }

        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        recyclerView = findViewById(R.id.mainRecycler);
        models = (ArrayList<BookWriterSavingModel>) getData();
        bookWriterSavingAdapter = new BookWriterSavingAdapter(models,getBaseContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(BookwriterSavingsOptions.this,2);
        bookWriterSavingAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildLayoutPosition(v);
                BookWriterSavingModel bookWriterSavingModel = models.get(position);
                String card = bookWriterSavingModel.getCardNumber();

                switch (card) {
                    case "My Savings":
                        finish();
                Intent mySavings = new Intent(getApplicationContext(), MyTransactionsHistoryActivity.class);
                startActivity(mySavings );
                        break;
                    case "Group Savings":
                        finish();
//                Intent groupSavings = new Intent(getApplicationContext(), MyGroupTransactionsHistoryActivity.class);
//                startActivity(groupSavings);
                        break;
                    case "Add Savings":
                        finish();
                Intent addSavings = new Intent(getApplicationContext(), NewPaymentActivity.class);
                startActivity(addSavings);
                        break;
                    case "Edit Savings":
                        Toast.makeText(getApplicationContext(), "Work In Progress.", Toast.LENGTH_SHORT).show();
//            }
                        break;

                }

            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bookWriterSavingAdapter);

//        my_savings_option.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//                Intent intent = new Intent(getApplicationContext(), MyTransactionsHistoryActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        group_savings_option.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//                Intent intent = new Intent(getApplicationContext(), MyTransactionsHistoryActivity.class);
//                startActivity(intent);
//            }
//        });
//        add_savings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//                Intent intent = new Intent(getApplicationContext(), NewPaymentActivity.class);
//                startActivity(intent);
//            }
//        });
//        edit_savings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Work In Progress.", Toast.LENGTH_SHORT).show();
//            }
//        });

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
    private List<BookWriterSavingModel> getData(){
        ArrayList<BookWriterSavingModel> mainModel = new ArrayList<>();
            mainModel.add(new BookWriterSavingModel("My Savings",R.drawable.ic_saving,"My Savings"));
            mainModel.add(new BookWriterSavingModel("Group Savings",R.drawable.ic_group_saving,"Group Savings"));
            mainModel.add(new BookWriterSavingModel("Add Savings",R.drawable.ic_money,"Add Savings"));
            mainModel.add(new BookWriterSavingModel("Edit Savings",R.drawable.ic_add_pay,"Edit Savings"));
        return mainModel;
    }
    @Override
    public void onBackPressed() {


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.GROUP_NAME, group_name);
        bundle.putString(Constants.GROUP_ID,group_id);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
