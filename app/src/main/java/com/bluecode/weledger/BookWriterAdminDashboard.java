package com.bluecode.weledger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.bluecode.weledger.adapters.BookWriterAdminAdapter;
import com.bluecode.weledger.models.BookWriterAdminModel;


import java.util.ArrayList;
import java.util.List;

public class BookWriterAdminDashboard extends AppCompatActivity {
    ArrayList<BookWriterAdminModel> models = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_writer_admin);
        recyclerView = findViewById(R.id.mainAdminRecycler);
        models = (ArrayList<BookWriterAdminModel>) getData();
        BookWriterAdminAdapter bookWriterAdminModel = new   BookWriterAdminAdapter(models, BookWriterAdminDashboard.this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(BookWriterAdminDashboard.this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bookWriterAdminModel);

    }
    public List<BookWriterAdminModel> getData(){
        ArrayList<BookWriterAdminModel> models = new ArrayList<>();
        models.add(new BookWriterAdminModel("Add Members",R.drawable.ic_add_members,R.color.container_color,"add member"));
        models.add(new BookWriterAdminModel("View Members",R.drawable.ic_members,R.color.container_color2,"view members"));
        return models;
    }
}