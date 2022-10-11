package com.bluecode.weledger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bluecode.weledger.adapters.BookWriterAdminAdapter;
import com.bluecode.weledger.models.BookWriterAdminModel;
import com.bluecode.weledger.models.MainActivityModel;
import com.bluecode.weledger.utils.BookwriterNewMemberActivity;


import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class BookWriterAdminDashboard extends AppCompatActivity {
    ArrayList<BookWriterAdminModel> models = new ArrayList<>();
    RecyclerView recyclerView;
    String group_name;
    String group_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_writer_admin);
        recyclerView = findViewById(R.id.mainAdminRecycler);



        models = (ArrayList<BookWriterAdminModel>) getData();
        BookWriterAdminAdapter bookWriterAdminAdapter = new   BookWriterAdminAdapter(models, BookWriterAdminDashboard.this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(BookWriterAdminDashboard.this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bookWriterAdminAdapter);

        Bundle bundle = getIntent().getExtras();

            try {
                group_name = bundle.getString(Constants.GROUP_NAME,"Default");
                group_id = bundle.getString(Constants.GROUP_ID,"Default");
            }catch (Exception e){

                Log.e("Error","Attempt to invoke virtual method 'java.lang.String android.os.Bundle.getString(java.lang.String, java.lang.String)' on a null object reference ");
            }



        bookWriterAdminAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildLayoutPosition(v);
                BookWriterAdminModel bookWriterAdminModel = models.get(position);
                String id = bookWriterAdminModel.getCardID();
                Bundle bundle = new Bundle();
                switch(id){

                    case "add member":

                        Intent addMember = new Intent(getBaseContext(),BookwriterNewMemberActivity.class);

                        bundle.putString(Constants.GROUP_NAME, group_name);
                        bundle.putString(Constants.GROUP_ID, group_id);
                        addMember.putExtras(bundle);
                        startActivity(addMember);
                        finish();
                        break;
                    case "view members":
                        Intent viewGroup = new Intent(getBaseContext(), MembersActivity.class);
                        bundle.putString(Constants.GROUP_NAME, group_name);
                        bundle.putString(Constants.GROUP_ID, group_id);
                        viewGroup.putExtras(bundle);
                        startActivity(viewGroup);
                        finish();
                        break;
                }

            }
        });

    }
    public List<BookWriterAdminModel> getData(){
        ArrayList<BookWriterAdminModel> models = new ArrayList<>();
        models.add(new BookWriterAdminModel("Add Members",R.drawable.ic_add_members,R.color.container_color,"add member"));
        models.add(new BookWriterAdminModel("View Members",R.drawable.ic_members,R.color.container_color,"view members"));
        return models;
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