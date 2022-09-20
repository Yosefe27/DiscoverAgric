package com.bluecode.weledger;

import static com.bluecode.weledger.Constants.BASE_URL;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bluecode.weledger.models.MyContributions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    LinearLayout linear_members, linear_loans, linear_payments,linear_manage_members,linear_manage_loans,linear_manage_payments;
    GridLayout linear_bookkeeper_menu;
    TextView name, group_balance,top_name;
    TextView save_payment;
    String str_a, str_name, str_user_role;
    Toolbar toolbar;
    LinearLayout linear_manage_groups,linear_center_container;
    GridLayout linear_facilitator_menu,linear_ordinary_member_menu;
    String homepage_stats = BASE_URL + "homepage_stats.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //toolbar.setTitle("WE eLedger");
        //toolbar.setSubtitle("Home");
        name = findViewById(R.id.name);
        linear_members = findViewById(R.id.linear_members);
        linear_loans = findViewById(R.id.linear_loans);
        linear_payments = findViewById(R.id.linear_payments);
        //group_balance = findViewById(R.id.group_balance);
        //linear_center_container = findViewById(R.id.linear_center_container);
        //top_name = findViewById(R.id.top_name);
        linear_bookkeeper_menu = findViewById(R.id.linear_bookkeeper_menu);
        linear_ordinary_member_menu = findViewById(R.id.linear_ordinary_member_menu);
        linear_manage_groups = findViewById(R.id.linear_manage_groups);
        linear_facilitator_menu = findViewById(R.id.linear_facilitator_menu);
        linear_manage_members = findViewById(R.id.linear_manage_members);
        linear_manage_loans = findViewById(R.id.linear_manage_loans);
        linear_manage_payments = findViewById(R.id.linear_manage_payments);
        save_payment = findViewById(R.id.save_payment_details);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        str_a = preferences.getString("a", "");
        str_name = preferences.getString("name", "");
        str_user_role = preferences.getString("user_role", "");
        name.setText(String.valueOf("Welcome! " + str_name));
        //top_name.setText(String.valueOf("Welcome! " + str_name));
        if (str_user_role.equals("2")) {
            //linear_bookkeeper_menu.setVisibility(View.VISIBLE);
            linear_facilitator_menu.setVisibility(View.GONE);
            //top_name.setVisibility(View.GONE);
        } else if (str_user_role.equals("3")) {
            linear_facilitator_menu.setVisibility(View.VISIBLE);
            //linear_bookkeeper_menu.setVisibility(View.GONE);
            linear_ordinary_member_menu.setVisibility(View.GONE);
            //linear_center_container.setVisibility(View.GONE);
        } else {
            //linear_bookkeeper_menu.setVisibility(View.GONE);
            linear_facilitator_menu.setVisibility(View.GONE);
            //top_name.setVisibility(View.GONE);
        }
        linear_members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MembersActivity.class);
                startActivity(intent);
            }
        });
        linear_manage_groups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), FacilitatorGroupsActivity.class);
                startActivity(intent);
            }
        });
        linear_payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MyTransactionsHistoryActivity.class);
                startActivity(intent);
            }
        });
        linear_loans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MyLoanRequestsActivity.class);
                startActivity(intent);
            }
        });
        linear_manage_members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), FacilitatorMembersActivity.class);
                startActivity(intent);
            }
        });
        linear_manage_payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), FacilitatorGroupTransactionsHistoryActivity.class);
                startActivity(intent);
            }
        });
        linear_manage_loans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), LoanRequestsApprovalsActivity.class);
                startActivity(intent);
            }
        });

        myContributionsHistory();
    }

    public void myContributionsHistory() {
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
        reportsAlert.setCancelable(false);
        reportsAlert.setCanceledOnTouchOutside(false);
        reportsAlert.show();
        reportsAlert.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        StringRequest request = new StringRequest(Request.Method.POST, homepage_stats, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(),"Response : "+response,Toast.LENGTH_SHORT).show();
//                textView.setText(response.toString());
                Log.v("transactions_response", response);
                try {
                    JSONObject object = new JSONObject(response);

                    String str_user_name = object.getString("full_name");
                    String group_name = object.getString("group_name");
                    //String s_group_balance = object.getString("group_balance");
                    String my_contributions = object.getString("my_contributions");
                    String annual_interest_rate = object.getString("annual_interest_rate");
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    //editor.putString("group_balance", s_group_balance);
                    editor.putString("annual_interest_rate", annual_interest_rate);
                    editor.apply();
                   // group_balance.setText(String.valueOf("K " + s_group_balance));
//                    txt_group_name.setText(group_name);
//                    txt_prof_name.setText("Welcome " + str_user_name);
//                    txt_my_contributions.setText(String.valueOf("K " + my_contributions));
                    JSONArray array = object.getJSONArray("statement");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject stackObject = array.getJSONObject(i);
//                        JSONObject stackObject2 = array2.getJSONObject(i);

                        // textView.setText(object1.toString());
                        MyContributions myTransactions = new MyContributions(
                                stackObject.getString("payment_mode"),
                                stackObject.getString("amount"),
                                stackObject.getString("month_contributed_for"),
                                stackObject.getString("payment_ref_number")

                        );
//                        listMytransactions.add(myTransactions);
                    }


                    reportsAlert.dismiss();


                } catch (
                        JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    errorDialog(" No Internet Connection ");
                } else if (error instanceof AuthFailureError) {
                    //Toast.makeText(getApplicationContext(), "authentication error", Toast.LENGTH_SHORT).show();
                    errorDialog("authentication error");
                } else if (error instanceof ServerError) {
                    //Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();
                    errorDialog("server error");
                } else if (error instanceof NetworkError) {
                    errorDialog("Network error");
                    //Toast.makeText(getApplicationContext(), "authentication error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    errorDialog("Parse Error");
                    //Toast.makeText(getApplicationContext(), "parse error", Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("a", str_a);
//                parms.put("a", access_token);
                return parms;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        request.setShouldCache(false);
        requestQueue.add(request);
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
    public void dialogLogout() {

        ViewGroup viewGroup = findViewById(android.R.id.content);
        TextView btn_yes, btn_no;
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_logout, viewGroup, false);
        btn_yes = dialogView.findViewById(R.id.btn_yes);
        btn_no = dialogView.findViewById(R.id.btn_no);

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
        reportsAlert.getWindow().setBackgroundDrawableResource(android.R.color.white);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportsAlert.dismiss();
            }
        });
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("pref_login_status", "0");
                editor.apply();
                reportsAlert.dismiss();
                finish();
                Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);


        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_profile) {
//            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
//            startActivity(intent);
        }
        if (id == R.id.action_reload) {
            Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_logout) {
            dialogLogout();
        }


        return super.onOptionsItemSelected(item);
    }
}