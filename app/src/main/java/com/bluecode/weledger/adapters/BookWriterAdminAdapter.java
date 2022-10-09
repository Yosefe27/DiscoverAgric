package com.bluecode.weledger.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bluecode.weledger.AddMemberToGroupActivity;
import com.bluecode.weledger.BookWriterMembersActivity;
import com.bluecode.weledger.FacilitatorGroupsActivity;
import com.bluecode.weledger.FacilitatorNewGroupActivity;
import com.bluecode.weledger.MembersActivity;
import com.bluecode.weledger.NewMemberActivity;
import com.bluecode.weledger.R;
import com.bluecode.weledger.models.BookWriterAdminModel;
import com.bluecode.weledger.models.GroupAdminModel;
import com.bluecode.weledger.models.MainActivityModel;

import java.util.ArrayList;

public class BookWriterAdminAdapter extends RecyclerView.Adapter<BookWriterAdminAdapter.viewHolder> {
    ArrayList<BookWriterAdminModel> models;
    Context context;
    public BookWriterAdminAdapter(ArrayList<BookWriterAdminModel> models, Context context){
        this.models = models;
        this.context = context;
    }
    @NonNull
    @Override
    public BookWriterAdminAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_layout_cardview,parent,false);
        BookWriterAdminAdapter.viewHolder viewHolder = new BookWriterAdminAdapter.viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookWriterAdminAdapter.viewHolder holder, int position) {
        final BookWriterAdminModel bookWriterAdminModel = models.get(position);
        //   holder.imageView.setImageDrawable(mainActivityModel.getImageView());
        holder.name.setText(bookWriterAdminModel.getNameType());
        holder.imageView.setBackgroundResource(bookWriterAdminModel.getImage());
        holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, bookWriterAdminModel.getColor()));

        String id = bookWriterAdminModel.getCardID();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(id){

                    case "add member":
                        Intent addMember = new Intent(context, NewMemberActivity.class);
                        context.startActivity(addMember);
                        break;
                    case "view members":
                        Intent viewGroup = new Intent(context, MembersActivity.class);
                        context.startActivity(viewGroup);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imageView;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
