package com.retrotech.disc_agric;

import static com.retrotech.disc_agric.Constants.BASE_URL;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.retrotech.disc_agric.utils.Connectivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    Toolbar toolbar;
    RequestQueue mRequestQueue;
    Button save_member_details;
    TextView groupName, groupID, admissionDate;
    EditText firstName, lastName, userName, passWord, user_password2, ecap_hh_ID, phoneNumber;
    String submit_member_url = BASE_URL + "sign_up.php";
    String str_group_name;
//    RadioButton male, female, option_yes, option_no, ordinary_member;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        groupName = findViewById(R.id.group_name);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        userName = findViewById(R.id.user_name);
        passWord = findViewById(R.id.user_password);
        user_password2 = findViewById(R.id.user_password2);
        phoneNumber = findViewById(R.id.phone_number);
        save_member_details = findViewById(R.id.save_member_details);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        str_group_name = preferences.getString("group_name", "");

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validatePhone(phoneNumber.getText().toString())) {
                    save_member_details.setEnabled(true);
                } else {
                    phoneNumber.setError("Invalid phone number");
                    save_member_details.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validateName(firstName.getText().toString())) {
                    save_member_details.setEnabled(true);
                } else {
                    firstName.setError("Name cannot contain numbers or special characters");
                    save_member_details.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validateName(lastName.getText().toString())) {
                    save_member_details.setEnabled(true);
                } else {
                    lastName.setError("Name cannot contain numbers or special characters");
                    save_member_details.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        mRequestQueue = Connectivity.getInstance(this).getRequestQueue();
        save_member_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_groupName = groupName.getText().toString();
                String str_firstName = firstName.getText().toString();
                String str_lastName = lastName.getText().toString();
                String str_userName = userName.getText().toString();
                String str_passWord = passWord.getText().toString();
                String str_phoneNumber = phoneNumber.getText().toString();

                if (TextUtils.isEmpty(str_groupName)) {
                    groupName.setError("Group name is required");
                    return;
                }
                if (TextUtils.isEmpty(str_firstName)) {
                    firstName.setError("First name is required");
                    return;
                } else if (TextUtils.isEmpty(str_lastName)) {
                    lastName.setError("Last name is required");
                    return;
                } else if (TextUtils.isEmpty(str_userName)) {
                    userName.setError("User name is required");
                    return;
                } else if (TextUtils.isEmpty(str_passWord)) {
                    passWord.setError("Set user password");
                    return;
                } else if (TextUtils.isEmpty(str_phoneNumber)) {
                    phoneNumber.setError("Phone number is required");
                    return;
                } else if (!passWord.getText().toString().equals(user_password2.getText().toString())) {
                    user_password2.setError("Password Not Matching");
                    return;
                }

                startSubmission(
                        str_groupName,
                        str_firstName,
                        str_lastName,
                        str_userName,
                        str_passWord,
                        str_phoneNumber
                );
            }
        });
    }

    private void startSubmission(
            final String groupname,
            final String firstname,
            final String surname,
            final String username,
            final String password,
            final String phonenumber
    ) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String str_a = preferences.getString("a", "");
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.loading_dialog, viewGroup, false);
        final AlertDialog loadingDialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(false)
                .create();

        if (loadingDialog.getWindow() != null) {
            loadingDialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
            loadingDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        loadingDialog.show(); // Show loading dialog

        StringRequest stringRequest = new StringRequest(Request.Method.POST, submit_member_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loadingDialog.dismiss(); // Dismiss loading dialog when response is received
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("status").equals("success")) {
                        errorDialog("Application Submitted Successfully. Our Admin will contact you by Phone/Email."); // Show error dialog
                    } else if (object.getString("status").equals("failed")) {
                        errorDialog(object.getString("msg")); // Show error dialog
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingDialog.dismiss(); // Dismiss loading dialog on error
                errorDialog("An error occurred. Please try again."); // Show error dialog
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<>();
                parms.put("group_name", groupname);
                parms.put("firstname", firstname);
                parms.put("lastname", surname);
                parms.put("nrc", username);
                parms.put("password", password);
                parms.put("phone_number", phonenumber);
                parms.put("a", str_a);
                return parms;
            }
        };
        stringRequest.setShouldCache(false);
        mRequestQueue.add(stringRequest);
    }

    public void errorDialog(String error_text) {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final TextView main_text;
        final Button btn_ok;
        final LinearLayout linear_buttons;
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_error, viewGroup, false);

        btn_ok = dialogView.findViewById(R.id.btn_ok);
        main_text = dialogView.findViewById(R.id.main_text);
        linear_buttons = dialogView.findViewById(R.id.linear_buttons);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        final AlertDialog errorDialog = builder.create();
        if (errorDialog.getWindow() != null) {
            errorDialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
            errorDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        // Set dialog properties
        errorDialog.setCancelable(false); // Prevent dialog from being dismissed by back button
        errorDialog.setCanceledOnTouchOutside(false); // Prevent dialog from being dismissed by touching outside

        main_text.setText(error_text);
        errorDialog.show(); // Show the error dialog

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorDialog.dismiss(); // Dismiss the dialog when the "OK" button is clicked
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    boolean validatePhone(String input) {
        Pattern pattern = Pattern.compile("((07||09)[5-7][0-9]{7})|s*");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    boolean validateName(String input) {
        Pattern pattern = Pattern.compile("[A-Za-z\\s\\.\\-]*");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
