package com.bluecode.weledger;

import static com.bluecode.weledger.Constants.BASE_URL;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bluecode.weledger.utils.Connectivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FacilitatorNewMemberActivity extends AppCompatActivity {
    Toolbar toolbar;
    RequestQueue mRequestQueue;
    TextView save_member_details;
    EditText groupName,firstName,lastName,userName,passWord;
    String submit_member_url=BASE_URL+"submit_member.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_member);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firstName = findViewById(R.id.first_name);
        groupName = findViewById(R.id.group_name);
        lastName = findViewById(R.id.last_name);
        userName = findViewById(R.id.user_name);
        passWord = findViewById(R.id.user_password);
        save_member_details = findViewById(R.id.save_member_details);
        mRequestQueue = Connectivity.getInstance(this).getRequestQueue();

        Bundle bundle = getIntent().getExtras();
        String gName = bundle.getString(Constants.GROUP_NAME, "Default");
        groupName.setText(gName);
        groupName.setEnabled(false);
        groupName.setTextColor(Color.BLACK);
        save_member_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_firstName = firstName.getText().toString();
                String str_lastName = lastName.getText().toString();
                String str_userName = userName.getText().toString();
                String str_passWord = passWord.getText().toString();
                if(userName.getText().toString().isEmpty()) {
//                    errorDialog("Username Cannot Be Empty");
//                    put validations here
                }
                else {
                    startSubmission(str_firstName,str_lastName,str_userName,str_passWord);

                }

            }
        });

    }
    private void startSubmission(final String firstname,
                                 final String surname,
                                 final String username,
                                 final String password)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String str_a = preferences.getString("a", "");
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.loading_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog reportsAlert = builder.create();
        if (reportsAlert.getWindow() != null)
            reportsAlert.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
        reportsAlert.setCancelable(true);
        reportsAlert.setCanceledOnTouchOutside(true);
        reportsAlert.show();
        reportsAlert.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, submit_member_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("member_url", response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("status").equals("success")) {

                        String received_msg = object.getString("msg");

                        reportsAlert.dismiss();
                      //  errorDialog(object.getString("msg"));
                        dialogToContinueAddingMembers();
                    } else if (object.getString("status").equals("failed")) {
                        reportsAlert.dismiss();
//                        errorDialog(object.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("first_name", firstname);
                parms.put("last_name", surname);
                parms.put("username", username);
                parms.put("password", password);
                parms.put("a", str_a);
                return parms;
            }
        };
        stringRequest.setShouldCache(false);
        mRequestQueue.add(stringRequest);
    }

    public void dialogToContinueAddingMembers() {

        ViewGroup viewGroup = findViewById(android.R.id.content);
        TextView btn_yes, btn_no;
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_open_activity, viewGroup, false);
        btn_yes = dialogView.findViewById(R.id.btn_yes);
        btn_no = dialogView.findViewById(R.id.btn_no);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        final AlertDialog reportsAlert = builder.create();
        if (reportsAlert.getWindow() != null)
            reportsAlert.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;

        reportsAlert.show();
        reportsAlert.getWindow().setBackgroundDrawableResource(android.R.color.white);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportsAlert.dismiss();
                Intent intent = new Intent(getApplicationContext(), FacilitatorGroupAdminDashboard.class);
                startActivity(intent);
                finish();
            }
        });
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FacilitatorNewMemberActivity.this, AddMemberToGroupActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}