package com.bluecode.weledger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bluecode.weledger.adapters.DefaultDashboardAdapter;
import com.bluecode.weledger.models.DefaultDashboardModel;

import java.util.ArrayList;
import java.util.List;

public class BookWriterLoanRequestDashboard extends AppCompatActivity {
    Toolbar toolbar;
    LinearLayout my_savings_option,group_savings_option,add_savings,edit_savings;
    String group_name;
    String group_id;
    ArrayList<DefaultDashboardModel> models = new ArrayList<>();
    RecyclerView recyclerView;
    DefaultDashboardAdapter defaultDashboardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_request_dashboard);
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
        models = (ArrayList<DefaultDashboardModel>) getData();
        defaultDashboardAdapter = new DefaultDashboardAdapter(models,getBaseContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(BookWriterLoanRequestDashboard.this,2);
        defaultDashboardAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildLayoutPosition(v);
                DefaultDashboardModel defaultDashboardModel = models.get(position);
                String card = defaultDashboardModel.getCardNumber();

                switch (card) {
                    case "My Loan":
                        finish();
                        Intent myLoan = new Intent(getApplicationContext(), BookWriterLoansActivity.class);
                        startActivity(myLoan );
                        break;
                    case "Group Loan":
//                        finish();
//                        Intent groupSavings = new Intent(getApplicationContext(), BookWriterGroupLoansActivity.class);
//                        startActivity(groupSavings);
                        break;
                    case "Add Loan":
                        finish();
                        Intent addSavings = new Intent(getApplicationContext(), NewLoanRequestActivity.class);
                        startActivity(addSavings);
                        break;
                    case "Edit Loan":
                        Toast.makeText(getApplicationContext(), "Work In Progress.", Toast.LENGTH_SHORT).show();
                        break;

                }

            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(defaultDashboardAdapter);
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
    private List<DefaultDashboardModel> getData(){
        ArrayList<DefaultDashboardModel> mainModel = new ArrayList<>();
        mainModel.add(new DefaultDashboardModel("My Loan",R.drawable.ic_saving,"My Loan"));
        mainModel.add(new DefaultDashboardModel("Group Loan",R.drawable.ic_group_saving,"Group Loan"));
        mainModel.add(new DefaultDashboardModel("Add Loan",R.drawable.ic_money,"Add Loan"));
        mainModel.add(new DefaultDashboardModel("Edit Loan",R.drawable.ic_add_pay,"Edit Loan"));
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