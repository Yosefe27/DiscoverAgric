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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.bluecode.weledger.adapters.MainActivityAdapter;
import com.bluecode.weledger.models.MainActivityModel;
import com.bluecode.weledger.models.MyContributions;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    GridLayout linear_bookkeeper_menu;
    TextView name, group_balance,top_name;
    TextView save_payment,logout;
    String str_a, str_name, str_user_role;
    Toolbar toolbar;
    LinearLayout linear_members, linear_loans, member_payments,linear_manage_payments,bookwriter_social_fund;
    LinearLayout bookwriter_savings,bookwriter_repayments,bookwriter_members,bookwriter_loans,bookwriter_ledger,bookwriter_fines;
    LinearLayout linear_manage_groups,linear_facilitator_savings,linear_loans_facilitator,linear_ledger_member,linear_fines;
    LinearLayout linear_repayments_facilitator,linear_fines_facilitator,linear_ledger_facilitator,linear_center_container;
    GridLayout linear_facilitator_menu,linear_ordinary_member_menu;
    String homepage_stats = BASE_URL + "homepage_stats.php";


    DrawerLayout drawer;
    NavigationView navigationView;


    ArrayList<MainActivityModel> models = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic__menu);

      recyclerView = findViewById(R.id.mainRecycler);
       models = (ArrayList<MainActivityModel>) getData();
        MainActivityAdapter mainActivityAdapter = new MainActivityAdapter(models,MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this,3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainActivityAdapter);

        drawer = findViewById(R.id.DrawerLayout);
        navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
//                    case R.id.nav_group_admins:
//                        Intent i = new Intent(MainActivity.this, RegistrationActivity.class);
//                        startActivity(i);
//                        Toast.makeText(getApplicationContext(),"Ad",Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.nav_group_savings:
//                        Toast.makeText(getApplicationContext(),"Savings",Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.nav_group_loans:
//
////                        Intent i = new Intent(MainActivity.this, RegistrationActivity.class);
////                        startActivity(i);
//                        Toast.makeText(getApplicationContext(),"Loans",Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.nav_repayments:
//                        Toast.makeText(getApplicationContext(),"Repayment",Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.nav_group_fines:
//
////                        Intent i = new Intent(MainActivity.this, RegistrationActivity.class);
////                        startActivity(i);
//                        Toast.makeText(getApplicationContext(),"Group Fines",Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.nav_ledger:
//                        Toast.makeText(getApplicationContext(),"Ledger",Toast.LENGTH_SHORT).show();
//                        break;
                    default:
                        break;
                }
                item.setChecked(true);
                drawer.closeDrawers();
                return true;
            }
        });

    }

//    public void myContributionsHistory() {
//        ViewGroup viewGroup = findViewById(android.R.id.content);
//
//        final View dialogView = LayoutInflater.from(this).inflate(R.layout.loading_dialog, viewGroup, false);
//
//
//        //Now we need an AlertDialog.Builder object
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        //setting the view of the builder to our custom view that we already inflated
//        builder.setView(dialogView);
//        //finally creating the alert dialog and displaying it
//        final AlertDialog reportsAlert = builder.create();
//        // Let's start with animation work. We just need to create a style and use it here as follow.
//        if (reportsAlert.getWindow() != null)
//            reportsAlert.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
//        reportsAlert.setCancelable(false);
//        reportsAlert.setCanceledOnTouchOutside(false);
//        reportsAlert.show();
//        reportsAlert.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        StringRequest request = new StringRequest(Request.Method.POST, homepage_stats, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
////                Toast.makeText(getApplicationContext(),"Response : "+response,Toast.LENGTH_SHORT).show();
////                textView.setText(response.toString());
//                Log.v("transactions_response", response);
//                try {
//                    JSONObject object = new JSONObject(response);
//
//                    String str_user_name = object.getString("full_name");
//                    String group_name = object.getString("group_name");
//                    //String s_group_balance = object.getString("group_balance");
//                    String my_contributions = object.getString("my_contributions");
//                    String annual_interest_rate = object.getString("annual_interest_rate");
//                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                    SharedPreferences.Editor editor = preferences.edit();
//                    //editor.putString("group_balance", s_group_balance);
//                    editor.putString("annual_interest_rate", annual_interest_rate);
//                    editor.apply();
//                   // group_balance.setText(String.valueOf("K " + s_group_balance));
////                    txt_group_name.setText(group_name);
////                    txt_prof_name.setText("Welcome " + str_user_name);
////                    txt_my_contributions.setText(String.valueOf("K " + my_contributions));
//                    JSONArray array = object.getJSONArray("statement");
//                    for (int i = 0; i < array.length(); i++) {
//                        JSONObject stackObject = array.getJSONObject(i);
////                        JSONObject stackObject2 = array2.getJSONObject(i);
//
//                        // textView.setText(object1.toString());
//                        MyContributions myTransactions = new MyContributions(
//                                stackObject.getString("payment_mode"),
//                                stackObject.getString("amount"),
//                                stackObject.getString("month_contributed_for"),
//                                stackObject.getString("payment_ref_number")
//
//                        );
////                        listMytransactions.add(myTransactions);
//                    }
//
//
//                    reportsAlert.dismiss();
//
//
//                } catch (
//                        JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//                    errorDialog(" No Internet Connection ");
//                } else if (error instanceof AuthFailureError) {
//                    //Toast.makeText(getApplicationContext(), "authentication error", Toast.LENGTH_SHORT).show();
//                    errorDialog("authentication error");
//                } else if (error instanceof ServerError) {
//                    //Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();
//                    errorDialog("server error");
//                } else if (error instanceof NetworkError) {
//                    errorDialog("Network error");
//                    //Toast.makeText(getApplicationContext(), "authentication error", Toast.LENGTH_SHORT).show();
//                } else if (error instanceof ParseError) {
//                    errorDialog("Parse Error");
//                    //Toast.makeText(getApplicationContext(), "parse error", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> parms = new HashMap<String, String>();
//                parms.put("a", str_a);
////                parms.put("a", access_token);
//                return parms;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        request.setShouldCache(false);
//        requestQueue.add(request);
//    }
//
//    public void errorDialog(String error_text) {
//
//        ViewGroup viewGroup = findViewById(android.R.id.content);
//        final TextView main_text;
//        final Button btn_ok;
//        final LinearLayout linear_buttons;
//        final CardView card_ok;
//        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_error, viewGroup, false);
//
//
//        btn_ok = dialogView.findViewById(R.id.btn_ok);
////        card_ok = dialogView.findViewById(R.id.card_ok);
//        main_text = dialogView.findViewById(R.id.main_text);
//        linear_buttons = dialogView.findViewById(R.id.linear_buttons);
//
//
//        //Now we need an AlertDialog.Builder object
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        //setting the view of the builder to our custom view that we already inflated
//        builder.setView(dialogView);
//        //finally creating the alert dialog and displaying it
//        final AlertDialog reportsAlert = builder.create();
//        // Let's start with animation work. We just need to create a style and use it here as follow.
//        if (reportsAlert.getWindow() != null)
//            reportsAlert.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
//
//        reportsAlert.show();
//        reportsAlert.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        btn_ok.setVisibility(View.VISIBLE);
//        linear_buttons.setVisibility(View.GONE);
////        To prevent dialog box from getting dismissed on back key pressed use this
//        reportsAlert.setCancelable(false);
//
////        And to prevent dialog box from getting dismissed on outside touch use this
//        reportsAlert.setCanceledOnTouchOutside(false);
//        main_text.setText(error_text);
//
//        btn_ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                reportsAlert.dismiss();
//            }
//        });
//
//
//    }
//    public void dialogLogout() {
//
//        ViewGroup viewGroup = findViewById(android.R.id.content);
//        TextView btn_yes, btn_no;
//        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_logout, viewGroup, false);
//        btn_yes = dialogView.findViewById(R.id.btn_yes);
//        btn_no = dialogView.findViewById(R.id.btn_no);
//
//        //Now we need an AlertDialog.Builder object
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        //setting the view of the builder to our custom view that we already inflated
//        builder.setView(dialogView);
//        //finally creating the alert dialog and displaying it
//        final AlertDialog reportsAlert = builder.create();
//        // Let's start with animation work. We just need to create a style and use it here as follow.
//        if (reportsAlert.getWindow() != null)
//            reportsAlert.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
//
//        reportsAlert.show();
//        reportsAlert.getWindow().setBackgroundDrawableResource(android.R.color.white);
//        btn_no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                reportsAlert.dismiss();
//            }
//        });
//        btn_yes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("pref_login_status", "0");
//                editor.apply();
//                reportsAlert.dismiss();
//                finish();
//                Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
//                startActivity(intent);
//            }
//        });
//
//    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_home, menu);
//
//
//        return true;
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_profile) {
////            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
////            startActivity(intent);
//        }
//        if (id == R.id.action_reload) {
//            Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
//            startActivity(intent);
//        }
//        if (id == R.id.action_logout) {
//            dialogLogout();
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }

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
        getMenuInflater().inflate(R.menu.main_tool_bar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_logout:
                dialogLogout();
                break;
            case R.id.action_profile:
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                str_a = preferences.getString("a", "");
                str_name = preferences.getString("name", "");
                str_user_role = preferences.getString("user_role", "");


                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast,
                        (ViewGroup) findViewById(R.id.toast_layout_root));
                TextView toastTextView = (TextView) layout.findViewById(R.id.toastTextView);
                ImageView toastImageView = (ImageView) layout.findViewById(R.id.toastImageView);
                if(str_user_role.equals("2")){
                    toastTextView.setText("You`re logged in as a book writer");
                }
                else if(str_user_role.equals("3")){
                    toastTextView.setText("You`re logged in as a facilitator");
                }
                else{
                    toastTextView.setText("You`re logged in as an ordinary member");
                }
                toastImageView.setImageResource(R.drawable.ic_user_profile);
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();



                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private List<MainActivityModel> getData(){
        ArrayList<MainActivityModel> mainModel = new ArrayList<>();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        str_a = preferences.getString("a", "");
        str_name = preferences.getString("name", "");
        str_user_role = preferences.getString("user_role", "");
        if(str_user_role.equals("2")){ //2 is for book writer
            mainModel.add(new MainActivityModel("Member Admin",R.drawable.ic_admin,R.color.container_color,1));
            mainModel.add(new MainActivityModel("Savings",R.drawable.ic_saving,R.color.container_color2,2));
            mainModel.add(new MainActivityModel("Loan Requests",R.drawable.ic_loan,R.color.container_color,3));
            mainModel.add(new MainActivityModel("Repayments",R.drawable.ic_payments,R.color.container_color2,4));
            mainModel.add(new MainActivityModel("Fines",R.drawable.ic_fine,R.color.container_color,5));
            mainModel.add(new MainActivityModel("Social Funds",R.drawable.ic_social_fund,R.color.container_color2,6));
            mainModel.add(new MainActivityModel("Member Register",R.drawable.ic_register,R.color.container_color,7));


        }
        else if(str_user_role.equals("3")){//3 is for facilitator
            mainModel.add(new MainActivityModel("Group Admin",R.drawable.ic_admin,R.color.container_color,8));
            mainModel.add(new MainActivityModel("Group Savings",R.drawable.ic_saving,R.color.container_color2,9));
            mainModel.add(new MainActivityModel("Group Loans",R.drawable.ic_loan,R.color.container_color,10));
            mainModel.add(new MainActivityModel("Repayment",R.drawable.ic_payments,R.color.container_color2,11));
            mainModel.add(new MainActivityModel("Group Fines",R.drawable.ic_fine,R.color.container_color,12));
            mainModel.add(new MainActivityModel("Member Register",R.drawable.ic_register,R.color.container_color2,13));
            mainModel.add(new MainActivityModel("Social Funds",R.drawable.ic_social_fund,R.color.container_color,14));

        }
        else {

            mainModel.add(new MainActivityModel("Members",R.drawable.ic_member,R.color.container_color,15));
            mainModel.add(new MainActivityModel("Savings",R.drawable.ic_saving,R.color.container_color2,16));
            mainModel.add(new MainActivityModel("Loans Request",R.drawable.ic_loan,R.color.container_color,17));
            mainModel.add(new MainActivityModel("Repayments",R.drawable.ic_payments,R.color.container_color2,18));
            mainModel.add(new MainActivityModel("Fines",R.drawable.ic_fine,R.color.container_color,19));
            mainModel.add(new MainActivityModel("Ledger",R.drawable.ic_ledger,R.color.container_color2,20));
            mainModel.add(new MainActivityModel("Social Funds",R.drawable.ic_social_fund,R.color.container_color,21));
            mainModel.add(new MainActivityModel("Attendance Register",R.drawable.ic_register,R.color.container_color2,22));


        }
             return mainModel;
    }
}