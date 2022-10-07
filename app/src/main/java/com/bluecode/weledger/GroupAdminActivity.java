package com.bluecode.weledger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.bluecode.weledger.adapters.GroupAdminAdapter;
import com.bluecode.weledger.models.GroupAdminModel;


import java.util.ArrayList;
import java.util.List;

public class GroupAdminActivity extends AppCompatActivity {
    ArrayList<GroupAdminModel> models = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_admin);
        recyclerView = findViewById(R.id.mainAdminRecycler);
        models = (ArrayList<GroupAdminModel>) getData();
        GroupAdminAdapter groupAdminAdapter = new GroupAdminAdapter(models,GroupAdminActivity.this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(GroupAdminActivity.this,3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(groupAdminAdapter);

    }
    public List<GroupAdminModel> getData(){
        ArrayList<GroupAdminModel> models = new ArrayList<>();
        models.add(new GroupAdminModel("Create Group",R.drawable.ic_create_group,R.color.container_color,"create group"));
        models.add(new GroupAdminModel("Add Members",R.drawable.ic_add_members,R.color.container_color2,"add member"));
        models.add(new GroupAdminModel("View Groups",R.drawable.ic_view_groups,R.color.container_color,"view groups"));
        models.add(new GroupAdminModel("View Members",R.drawable.ic_members,R.color.container_color2,"view groups"));
        return models;
    }
}