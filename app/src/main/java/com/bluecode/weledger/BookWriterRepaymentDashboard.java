package com.bluecode.weledger;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bluecode.weledger.adapters.DefaultDashboardAdapter;
import com.bluecode.weledger.models.DefaultDashboardModel;

import java.util.ArrayList;
import java.util.List;

public class BookWriterRepaymentDashboard extends AppCompatActivity {
    Toolbar toolbar;
    LinearLayout my_savings_option,group_savings_option,add_savings,edit_savings;
    String group_name;
    String group_id;
    ArrayList<DefaultDashboardModel> models = new ArrayList<>();
    RecyclerView recyclerView;
    DefaultDashboardAdapter defaultDashboardAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_writer_repayment_dashboard);


        recyclerView = findViewById(R.id.mainRecycler);
        models = (ArrayList<DefaultDashboardModel>) getData();
        defaultDashboardAdapter = new DefaultDashboardAdapter(models,getBaseContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(BookWriterRepaymentDashboard.this,2);
        defaultDashboardAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildLayoutPosition(v);
                DefaultDashboardModel defaultDashboardModel = models.get(position);
                String card = defaultDashboardModel.getCardNumber();

                switch (card) {
                    case "My Repayments":
                        finish();
                        Intent mySavings = new Intent(getApplicationContext(), BookwriterRepaymentsActivity.class);
                        startActivity(mySavings );
                        break;
                    case "Group Repayments":
//                        finish();
//                        Intent groupSavings = new Intent(getApplicationContext(), BookwriterGroupRepaymentsActivity.class);
//                        startActivity(groupSavings);
                        break;
                    case "Add Repayments":
                        finish();
                        Intent addSavings = new Intent(getApplicationContext(), NewLoanRepaymentActivity.class);
                        startActivity(addSavings);
                        break;
                    case "Edit Repayments":
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
        mainModel.add(new DefaultDashboardModel("My Repayments",R.drawable.ic_saving,"My Repayments"));
        mainModel.add(new DefaultDashboardModel("Group Repayments",R.drawable.ic_group_saving,"Group Repayments"));
        mainModel.add(new DefaultDashboardModel("Add Repayments",R.drawable.ic_money,"Add Repayments"));
        mainModel.add(new DefaultDashboardModel("Edit Repayments",R.drawable.ic_add_pay,"Edit Repayments"));
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
