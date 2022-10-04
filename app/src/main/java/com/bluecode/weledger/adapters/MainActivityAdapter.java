package com.bluecode.weledger.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bluecode.weledger.FacilitatorMembersActivity;
import com.bluecode.weledger.R;
import com.bluecode.weledger.models.MainActivityModel;

import java.util.ArrayList;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.viewHolder> {
    ArrayList<MainActivityModel> model;
    Context context;

    public MainActivityAdapter(ArrayList<MainActivityModel> model, Context context) {
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public MainActivityAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_layout_cardview, parent, false);
        MainActivityAdapter.viewHolder mainActivityAdapter = new MainActivityAdapter.viewHolder(view);
        return mainActivityAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull MainActivityAdapter.viewHolder holder, int position) {
        final MainActivityModel mainActivityModel = model.get(position);
        //   holder.imageView.setImageDrawable(mainActivityModel.getImageView());
        holder.name.setText(mainActivityModel.getNameType());
        holder.imageView.setBackgroundResource(mainActivityModel.getImageView());
//        holder.cardView.setBackgroundColor(mainActivityModel.getCardNumber());
        holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, mainActivityModel.getColor()));

        int card = mainActivityModel.getCardNumber();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (card) {
                    case 1:
                        Intent memberAdmin = new Intent(context, FacilitatorMembersActivity.class);
                           context.startActivity(memberAdmin);
                        break;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return model.size();
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
