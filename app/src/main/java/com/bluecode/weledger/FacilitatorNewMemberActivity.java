package com.bluecode.weledger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class FacilitatorNewMemberActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView save_bkwriter_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilitator_new_member);
        save_bkwriter_details = findViewById(R.id.save_member_details);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*toolbar.setTitle("Members");
        toolbar.setSubtitle("New Bookwriter Details");
        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), FacilitatorGroupsActivity.class);
                startActivity(intent);
            }
        });
        */
        save_bkwriter_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Work In Progress.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}