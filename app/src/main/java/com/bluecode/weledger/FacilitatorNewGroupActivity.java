package com.bluecode.weledger;

import static com.bluecode.weledger.Constants.BASE_URL;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import java.util.HashMap;
import java.util.Map;

public class FacilitatorNewGroupActivity extends AppCompatActivity {
    Toolbar toolbar;
    RequestQueue mRequestQueue;
    EditText group_name,interest_rate;
    String submit_group_url=BASE_URL+"submit_group.php";
    TextView save_group_details,select_member;
    String str_a, members_list = BASE_URL + "list_of_group_members.php";
    String membership_response = BASE_URL + "membership_response.php";
    MembersAdapter membersAdapter;
    ArrayList<Members> listMembers = new ArrayList<>();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilitator_new_group);
        toolbar = findViewById(R.id.toolbar);
        group_name = findViewById(R.id.group_name);
        interest_rate = findViewById(R.id.interest_rate);
        select_member = findViewById(R.id.select_member);
        save_group_details = findViewById(R.id.save_group_details);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Group");
        toolbar.setSubtitle("New Group Details");
        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), FacilitatorGroupsActivity.class);
                startActivity(intent);
            }
        });
        mRequestQueue = Connectivity.getInstance(this).getRequestQueue();
        save_group_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_group_name = group_name.getText().toString();
                String str_interest_rate = interest_rate.getText().toString();
                if(group_name.getText().toString().isEmpty()) {
                    errorDialog("Group Name Cannot Be Empty");
                }else {
                    startSubmission(str_group_name, str_interest_rate, "1");
                }
            }
        });
    }
    private void startSubmission(
            final String group_name,
            final String annual_interest_rate,
            final String status) {
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
                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
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

            //            first_name, surname, nrc, phone, email, pas, dob
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("group_name", group_name);
                parms.put("annual_interest_rate", annual_interest_rate);
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

//                                Intent intent = new Intent(getApplicationContext(), MembersDetailsActivity.class);
//                                intent.putExtra("intent_full_name", members.getFirstname() + " " + members.getLastname());
//                                intent.putExtra("intent_email", members.getEmail());
//                                intent.putExtra("intent_nrc", members.getNrc());
//                                intent.putExtra("intent_address", members.getAddress());
//                                intent.putExtra("intent_group_id", members.getGroup_id());
//                                intent.putExtra("intent_chairperson_approval", members.getChairperson_approval());
//                                intent.putExtra("intent_treasurer_approval", members.getTreasurer_approval());
//                                intent.putExtra("intent_secretary_approval", members.getSecretary_approval());
//                                startActivity(intent);
                reportsAlert.dismiss();
            }


        });
//                        reportsAlert.dismiss();





    }

    public void errorDialog(String error_text) {

        ViewGroup viewGroup = findViewById(android.R.id.content);
        final TextView main_text;
        final Button btn_ok;
        final LinearLayout linear_buttons;
        final CardView card_ok;
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_error, viewGroup, false);


        btn_ok = dialogView.findViewById(R.id.btn_ok);
//        card_ok = dialogView.findViewById(R.id.card_ok);
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

}