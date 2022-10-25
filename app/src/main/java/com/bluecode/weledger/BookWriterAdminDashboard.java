package com.bluecode.weledger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bluecode.weledger.adapters.BookWriterAdminAdapter;
import com.bluecode.weledger.models.BookWriterAdminModel;


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
        //TODO change recyclerView name
        recyclerView = findViewById(R.id.mainAdminRecycler);

        models = (ArrayList<BookWriterAdminModel>) getData();
        BookWriterAdminAdapter bookWriterAdminAdapter = new   BookWriterAdminAdapter(models, getBaseContext());
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



        bookWriterAdminAdapter.setClickListener(v -> {
            int position = recyclerView.getChildLayoutPosition(v);
            BookWriterAdminModel bookWriterAdminModel = models.get(position);
            String id = bookWriterAdminModel.getCardID();
            switch(id){

                case "addMember":
                    Intent addMember = new Intent(getBaseContext(),BookwriterNewMemberActivity.class);
                    startActivity(addMember);
                    finish();
                    break;
                case "viewMembers":
                    Intent viewGroup = new Intent(getBaseContext(), MembersActivity.class);
                    startActivity(viewGroup);
                    finish();
                    break;
            }

        });

    }
    public List<BookWriterAdminModel> getData(){
        ArrayList<BookWriterAdminModel> models = new ArrayList<>();
        //TODO C
        models.add(new BookWriterAdminModel("Add Members",R.drawable.ic_add_members,R.color.container_color,"addMember"));
        models.add(new BookWriterAdminModel("View Members",R.drawable.ic_members,R.color.container_color,"viewMembers"));

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