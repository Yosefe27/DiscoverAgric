package com.bluecode.weledger;

import static com.bluecode.weledger.Constants.BASE_URL;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bluecode.weledger.adapters.MembersAdapter;
import com.bluecode.weledger.models.Members;
import com.bluecode.weledger.utils.Connectivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacilitatorNewGroupActivity extends AppCompatActivity {
    Toolbar toolbar;
    RequestQueue mRequestQueue;
    EditText group_name,interest_rate,cycle_number,first_training_meeting_date,date_savings_started,reinvested_savings_cycle_start;
    EditText registered_members_cycle_start,group_management;
    Spinner group_management_spinner;
    List<String> group_mgt_items;
    String submit_group_url=BASE_URL+"submit_group.php";
    TextView save_group_details,select_member;
    MembersAdapter membersAdapter;
    ArrayList<Members> listMembers = new ArrayList<>();
    Context context;
    String group_mgt_item,testString;
    RadioButton supervised, self_managed,spontaneous;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilitator_new_group);
        toolbar = findViewById(R.id.toolbar);
        group_name = findViewById(R.id.group_name);
        interest_rate = findViewById(R.id.interest_rate);
        cycle_number = findViewById(R.id.cycle_number);
//        group_management = findViewById(R.id.group_mgt);
        //group_management.setVisibility(View.GONE);
        first_training_meeting_date = findViewById(R.id.first_training_meeting_date);
        date_savings_started = findViewById(R.id.date_savings_started);
        reinvested_savings_cycle_start = findViewById(R.id.reinvested_savings_cycle_start);
        registered_members_cycle_start = findViewById(R.id.registered_members_cycle_start);
        select_member = findViewById(R.id.select_member);
        save_group_details = findViewById(R.id.save_group_details);
        setSupportActionBar(toolbar);

        supervised = (RadioButton) findViewById(R.id.supervised);
        self_managed = (RadioButton) findViewById(R.id.self_managed);
        spontaneous = (RadioButton) findViewById(R.id.spontaneous);


//        group_management_spinner = (Spinner) findViewById(R.id.group_mgt_spinner);
        group_mgt_items = new ArrayList<>();
        group_mgt_items.add("");
        group_mgt_items.add("Supervised");
        group_mgt_items.add("Self-Managed");
        group_mgt_items.add("Spontaneous");

//        group_management_spinner.setAdapter(new ArrayAdapter<>(this,
//                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,group_mgt_items));
//        group_management_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                group_mgt_item = group_mgt_items.get(position);
//                group_management.setText(group_mgt_item);
//                Toast.makeText(FacilitatorNewGroupActivity.this,group_mgt_item,Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        first_training_meeting_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(FacilitatorNewGroupActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = year+"/"+month+"/"+dayOfMonth;
                        first_training_meeting_date.setText(date);
                    }
                },year, month,day);
                dialog.show();
            }
        });
        date_savings_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(FacilitatorNewGroupActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = year+"/"+month+"/"+dayOfMonth;
                        date_savings_started.setText(date);
                    }
                },year, month,day);
                dialog.show();
            }
        });

        mRequestQueue = Connectivity.getInstance(this).getRequestQueue();
        save_group_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_group_name = group_name.getText().toString();
                String str_cycle_number = cycle_number.getText().toString();
                String str_interest_rate = interest_rate.getText().toString();
                String str_first_training_meeting_date = first_training_meeting_date.getText().toString();
                String str_date_savings_started = date_savings_started.getText().toString();
                String str_reinvested_savings_cycle_start = reinvested_savings_cycle_start.getText().toString();
                String str_registered_members_cycle_start = registered_members_cycle_start.getText().toString();
                String str_group_management_spinner = null;
                if (supervised.isChecked()) {
                    str_group_management_spinner = supervised.getText().toString();
                } else if (self_managed.isChecked()) {
                    str_group_management_spinner = self_managed.getText().toString();
                } else if (spontaneous.isChecked()) {
                    str_group_management_spinner = spontaneous.getText().toString();
                }

                if(str_group_name.isEmpty()) {
                    errorDialog("Group Name Should NOT Be Empty");
                }
                else
                {
                    startSubmission(str_group_name,
                            str_interest_rate,
                            str_cycle_number,
                            str_first_training_meeting_date,
                            str_date_savings_started,
                            str_reinvested_savings_cycle_start,
                            str_registered_members_cycle_start,
                            str_group_management_spinner,
                            "1");
                    //Toast.makeText(context,"GROUP ADDED SUCCESSFULLY",Toast.LENGTH_LONG).show();
                   addNewMemberToGroup(str_group_name);
                }
            }
        });
    }
    private void startSubmission(
            final String group_name,
            final String annual_interest_rate,
            final String cycle_number,
            final String first_training_meeting_date,
            final String date_savings_started,
            final String reinvested_savings_cycle_start,
            final String registered_members_cycle_start,
            final String group_mgt,
            final String status)
    {
//        signin_progress.setVisibility(View.VISIBLE);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String str_a = preferences.getString("a", "");
        ViewGroup viewGroup = findViewById(android.R.id.content);

        final View dialogView = LayoutInflater.from(this).inflate(R.layout.loading_dialog, viewGroup, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        final AlertDialog reportsAlert = builder.create();
        // Let's start with animation work. We just need to create a style and use it here as follow.
        if (reportsAlert.getWindow() != null)
            reportsAlert.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
        reportsAlert.setCancelable(true);
        reportsAlert.setCanceledOnTouchOutside(true);
        reportsAlert.show();
        reportsAlert.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, submit_group_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("group_url", response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("status").equals("success")) {// same as if (object.getBoolean("success") == true) {

                        String received_msg = object.getString("msg");

                        reportsAlert.dismiss();
                        errorDialog(object.getString("msg"));
                    } else if (object.getString("status").equals("failed")) {
                        reportsAlert.dismiss();
//                        signin_progress.setVisibility(View.GONE);
                        errorDialog(object.getString("msg"));
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
                parms.put("group_name", group_name);
                parms.put("annual_interest_rate", annual_interest_rate);
                parms.put("cycle_number", cycle_number);
                parms.put("first_training_meeting_date", first_training_meeting_date);
                parms.put("date_savings_started", date_savings_started);
                parms.put("reinvested_savings_cycle_start", reinvested_savings_cycle_start);
                parms.put("registered_members_cycle_start",registered_members_cycle_start);
                parms.put("group_mgt", group_mgt);
                parms.put("status", status);
                parms.put("a", str_a);
                return parms;
            }
        };
        stringRequest.setShouldCache(false);
        mRequestQueue.add(stringRequest);
    }

    public void membersDisplayDialog() {
        RecyclerView members_recyclerview;
        ViewGroup viewGroup = findViewById(android.R.id.content);

        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_payment_options, viewGroup, false);

        members_recyclerview = dialogView.findViewById(R.id.recyclerview_payment_options);
        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        final AlertDialog reportsAlert = builder.create();
        // Let's start with animation work. We just need to create a style and use it here as follow.
        if (reportsAlert.getWindow() != null)
            reportsAlert.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;

        reportsAlert.show();
        reportsAlert.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        members_recyclerview.setHasFixedSize(true);
        members_recyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        members_recyclerview.addItemDecoration(new DividerItemDecoration(getBaseContext(), DividerItemDecoration.HORIZONTAL));
        membersAdapter = new MembersAdapter(getBaseContext(), listMembers);
        members_recyclerview.setAdapter(membersAdapter);
        membersAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = members_recyclerview.getChildLayoutPosition(view);
                Members members = listMembers.get(position);
                select_member.setText(String.valueOf(members.getFirstname()+" "+members.getLastname()));
                reportsAlert.dismiss();
            }

        });
    }

    public void errorDialog(String error_text) {

        ViewGroup viewGroup = findViewById(android.R.id.content);
        final TextView main_text;
        final Button btn_ok;
        final LinearLayout linear_buttons;
        final CardView card_ok;
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_error, viewGroup, false);

        btn_ok = dialogView.findViewById(R.id.btn_ok);
        main_text = dialogView.findViewById(R.id.main_text);
        linear_buttons = dialogView.findViewById(R.id.linear_buttons);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        final AlertDialog reportsAlert = builder.create();
        // Let's start with animation work. We just need to create a style and use it here as follow.
        if (reportsAlert.getWindow() != null)
            reportsAlert.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;

        reportsAlert.show();
        reportsAlert.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        btn_ok.setVisibility(View.VISIBLE);
        linear_buttons.setVisibility(View.GONE);
//        To prevent dialog box from getting dismissed on back key pressed use this
        reportsAlert.setCancelable(false);

//        And to prevent dialog box from getting dismissed on outside touch use this
        reportsAlert.setCanceledOnTouchOutside(false);
        main_text.setText(error_text);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportsAlert.dismiss();
            }
        });
    }
    public void addNewMemberToGroup(String groupName){
        Intent intent = new Intent(getApplicationContext(), FacilitatorNewMemberActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString(Constants.GROUP_NAME, groupName);

        intent.putExtras(bundle);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(getApplicationContext(), FacilitatorGroupAdminDashboard.class);
        startActivity(intent);

    }
}