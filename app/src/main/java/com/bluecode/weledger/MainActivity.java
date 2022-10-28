package com.bluecode.weledger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bluecode.weledger.adapters.MainActivityAdapter;
import com.bluecode.weledger.models.MainActivityModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String str_a, str_name, str_user_role;
    DrawerLayout drawer;
    NavigationView navigationView;

    String group_name;
    String group_id;
    String user_name;
    String role;

    ArrayList<MainActivityModel> models = new ArrayList<>();
    RecyclerView recyclerView;
    MainActivityAdapter mainActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic__menu);

        recyclerView = findViewById(R.id.mainRecycler);
        models = (ArrayList<MainActivityModel>) getData();
        mainActivityAdapter = new MainActivityAdapter(models,getBaseContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2);
        mainActivityAdapter.setClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int position = recyclerView.getChildLayoutPosition(v);
               MainActivityModel mainActivityModel = models.get(position);
               int card = mainActivityModel.getCardNumber();
               Bundle bundle = new Bundle();
               switch (card) {
                   case 1:
                       Intent bookAdmin = new Intent(getBaseContext(), BookWriterAdminDashboard.class);
                       bundle.putString(Constants.GROUP_NAME, group_name);
                       bundle.putString(Constants.GROUP_ID,group_id);
                       bundle.putString(Constants.USER_NAME,user_name);
                       bookAdmin.putExtras(bundle);
                       startActivity(bookAdmin);
                       finish();
                       break;
                   case 2:
                       Intent bookSaving= new Intent(getBaseContext(), BookWriterSavingsOptionsDashboard.class);
                       startActivity(bookSaving);
                       bundle.putString(Constants.GROUP_NAME, group_name);
                       bundle.putString(Constants.GROUP_ID,group_id);
                       bundle.putString(Constants.USER_NAME,user_name);
                       bookSaving.putExtras(bundle);
                       startActivity(bookSaving);
                       finish();
                       break;
                   case 3:
                       Intent bookLoan= new Intent(getBaseContext(), BookWriterLoanRequestDashboard.class);
                       startActivity(bookLoan);
                       finish();
                       break;
                   case 4:
                       Intent bookRepayment= new Intent(getBaseContext(), BookWriterRepaymentDashboard.class);
                       startActivity(bookRepayment);
                       finish();
                       break;
                   case 5:
                       Intent bookFines= new Intent(getBaseContext(), BookWriterFinesDashboard.class);
                       startActivity(bookFines);
                       finish();
                       break;
                   case 6:
                       Intent bookSocial= new Intent(getBaseContext(), BookWriterSocialDashboard.class);
                       startActivity(bookSocial);
                       finish();
                       break;
                   case 7:
                        Intent bookRegister= new Intent(getBaseContext(), BookWriterMemberRegisterDashboard.class);
                        startActivity(bookRegister);
                        finish();

                       break;
                   case 8:
//                        Intent groupAdmin = new Intent(context, FacilitatorGroupsActivity.class);
//                        context.startActivity(groupAdmin);
                       Intent groupAdmin = new Intent(getBaseContext(), FacilitatorGroupAdminDashboard.class);
                       startActivity(groupAdmin);
                       finish();
                       break;
                   case 9:
                       Intent groupSaving = new Intent(getBaseContext(), FacilitatorGroupSavings.class);
                       startActivity(groupSaving);
                       finish();
                       break;
                   case 10:
                       Intent groupLoan = new Intent(getBaseContext(), FacilitatorGroupLoans.class);
                       startActivity(groupLoan);
                       finish();
                       break;
                   case 11:
                       Intent groupRepayments = new Intent(getBaseContext(), FacilitatorGroupRepayments.class);
                       startActivity(groupRepayments);
                       finish();
                       break;
                   case 12:
                       Intent groupFines = new Intent(getBaseContext(), FacilitatorGroupFines.class);
                       startActivity(groupFines);
                       finish();
                       break;
                   case 13:
//                        Intent groupLedger = new Intent(context, FacilitatorGroupLoans.class);
//                        context.startActivity(groupLedger);
                       Toast.makeText(getBaseContext(),"Work in progress",Toast.LENGTH_SHORT).show();
                       break;
                   case 14:
                       Toast.makeText(getBaseContext(),"Work in progress",Toast.LENGTH_SHORT).show();
                       break;
                   case 15:
                       finish();
                        Intent ordinaryMembers = new Intent(getBaseContext(), OrdinaryMembersActivity.class);
                        startActivity(ordinaryMembers);
                       break;
                   case 16:
                       finish();
                       Intent ordinarySavingDashboard = new Intent(getBaseContext(), OrdinaryMemberSavingsDashboard.class);
                       startActivity(ordinarySavingDashboard);
                       break;
                   case 17:

                       Intent ordinaryLoanDashboard = new Intent(getBaseContext(), OrdinaryMemberLoanRequestDashboard.class);
                       startActivity(ordinaryLoanDashboard);
                       finish();
                       break;
                   case 18:
                       Intent ordinaryRepaymentDashboard = new Intent(getBaseContext(), OrdinaryMemberRepaymentDashboard.class);
                       startActivity(ordinaryRepaymentDashboard);
                       finish();
                       break;
                   case 19:
                       Intent ordinaryFines = new Intent(getBaseContext(), ViewTotalFineOrdinaryMemberActivity.class);
                       startActivity(ordinaryFines);
                       finish();
                       break;
//                   case 20:
////                        Intent groupLedger = new Intent(context, FacilitatorGroupLoans.class);
////                        context.startActivity(groupLedger);
//                       Toast.makeText(getBaseContext(),"Work in progress",Toast.LENGTH_SHORT).show();
//                       break;
                   case 21:
                       Intent ordinarySocialDashboard = new Intent(getBaseContext(), OrdinaryMemberSocialFundDashboard.class);
                       startActivity(ordinarySocialDashboard);
                       finish();
                       break;
                   case 22:
                       Intent attendance_register = new Intent(getBaseContext(), OrdinaryMemberViewRegister.class);
                       startActivity(attendance_register);
                       finish();
                       break;

               }

           }
       });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainActivityAdapter);


//        drawer = findViewById(R.id.DrawerLayout);
//        navigationView = findViewById(R.id.navigation);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
////                    case R.id.nav_group_admins:
////                        Intent i = new Intent(MainActivity.this, RegistrationActivity.class);
////                        startActivity(i);
////
//                    default:
//                        break;
//                }
//                item.setChecked(true);
//                drawer.closeDrawers();
//                return true;
//            }
//        });

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
//            case R.id.action_profile:
//              Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
//              startActivity(intent);
//              finish();
//                break;
            case R.id.action_member_profile:
                Intent member_profile = new Intent(MainActivity.this, MemberProfileActivity.class);
                startActivity(member_profile);
                finish();
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
            mainModel.add(new MainActivityModel("Savings",R.drawable.ic_saving,R.color.container_color,2));
            mainModel.add(new MainActivityModel("Loan Requests",R.drawable.ic_loan,R.color.container_color,3));
            mainModel.add(new MainActivityModel("Repayments",R.drawable.ic_payments,R.color.container_color,4));
            mainModel.add(new MainActivityModel("Fines",R.drawable.ic_fine,R.color.container_color,5));
            mainModel.add(new MainActivityModel("Social Funds",R.drawable.ic_social_fund,R.color.container_color,6));
            mainModel.add(new MainActivityModel("Member Register",R.drawable.ic_register,R.color.container_color,7));


        }
        else if(str_user_role.equals("3")){//3 is for facilitator
            mainModel.add(new MainActivityModel("Group Admin",R.drawable.ic_admin,R.color.container_color,8));
            mainModel.add(new MainActivityModel("Group Savings",R.drawable.ic_saving,R.color.container_color,9));
            mainModel.add(new MainActivityModel("Group Loans",R.drawable.ic_loan,R.color.container_color,10));
            mainModel.add(new MainActivityModel("Repayment",R.drawable.ic_payments,R.color.container_color,11));
            mainModel.add(new MainActivityModel("Group Fines",R.drawable.ic_fine,R.color.container_color,12));
            mainModel.add(new MainActivityModel("Member Register",R.drawable.ic_register,R.color.container_color,13));
            mainModel.add(new MainActivityModel("Social Funds",R.drawable.ic_social_fund,R.color.container_color,14));

        }
        else {

            mainModel.add(new MainActivityModel("Members",R.drawable.ic_member,R.color.container_color,15));
            mainModel.add(new MainActivityModel("Savings",R.drawable.ic_saving,R.color.container_color,16));
            mainModel.add(new MainActivityModel("Loans Request",R.drawable.ic_loan,R.color.container_color,17));
            mainModel.add(new MainActivityModel("Repayments",R.drawable.ic_payments,R.color.container_color,18));
            mainModel.add(new MainActivityModel("Fines",R.drawable.ic_fine,R.color.container_color,19));
//            mainModel.add(new MainActivityModel("Ledger",R.drawable.ic_ledger,R.color.container_color,20));
            mainModel.add(new MainActivityModel("Social Funds",R.drawable.ic_social_fund,R.color.container_color,21));
            mainModel.add(new MainActivityModel("Attendance Register",R.drawable.ic_register,R.color.container_color,22));


        }
             return mainModel;
    }

}