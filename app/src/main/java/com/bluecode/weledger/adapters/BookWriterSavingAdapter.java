package com.bluecode.weledger.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bluecode.weledger.BookWriterAdminDashboard;
import com.bluecode.weledger.BookwriterFinesActivity;
import com.bluecode.weledger.BookwriterRepaymentsActivity;
import com.bluecode.weledger.BookwriterSavingsOptions;
import com.bluecode.weledger.BookwriterSocialFundActivity;
import com.bluecode.weledger.FacilitatorGroupFines;
import com.bluecode.weledger.FacilitatorGroupLoans;
import com.bluecode.weledger.FacilitatorGroupRepayments;
import com.bluecode.weledger.FacilitatorGroupSavings;
import com.bluecode.weledger.BookWriterLoansActivity;
import com.bluecode.weledger.FacilitatorGroupAdminDashboard;
import com.bluecode.weledger.R;
import com.bluecode.weledger.models.BookWriterSavingModel;
import com.bluecode.weledger.models.MainActivityModel;

import java.util.ArrayList;

public class BookWriterSavingAdapter extends RecyclerView.Adapter<BookWriterSavingAdapter.viewHolder> {
    ArrayList<BookWriterSavingModel> model;
    Context context;
    View.OnClickListener clickListener;

    public BookWriterSavingAdapter(ArrayList<BookWriterSavingModel> model, Context context) {
        this.model = model;
        this.context = context;
    }
    public void setClickListener(View.OnClickListener callback) {
        clickListener = callback;
    }

    @NonNull
    @Override
    public BookWriterSavingAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_layout_cardview, parent, false);
        BookWriterSavingAdapter.viewHolder mainActivityAdapter = new BookWriterSavingAdapter.viewHolder(view);
        return mainActivityAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull BookWriterSavingAdapter.viewHolder holder, int position) {
        final BookWriterSavingModel mainActivityModel = model.get(position);
        //   holder.imageView.setImageDrawable(mainActivityModel.getImageView());
        holder.name.setText(mainActivityModel.getNameType());
        holder.imageView.setBackgroundResource(mainActivityModel.getImage());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(view);
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
