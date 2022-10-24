package com.bluecode.weledger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SocialDepositsAndDisbursmentsActivity extends AppCompatActivity {
CardView collected_social_fund;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_deposits_and_disbursments);
        collected_social_fund = findViewById(R.id.collect_social_fund);
        collected_social_fund.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TotalSocialFundsCollectedActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), BookWriterSocialDashboard.class);
        startActivity(intent);
        finish();
    }
}