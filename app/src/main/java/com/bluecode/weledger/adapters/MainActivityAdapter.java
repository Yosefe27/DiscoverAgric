package com.bluecode.weledger.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bluecode.weledger.BookwriterFinesActivity;
import com.bluecode.weledger.BookwriterRepaymentsActivity;
import com.bluecode.weledger.BookwriterSavingsOptions;
import com.bluecode.weledger.FacilitatorGroupFines;
import com.bluecode.weledger.FacilitatorGroupLoans;
import com.bluecode.weledger.FacilitatorGroupRepayments;
import com.bluecode.weledger.FacilitatorGroupSavings;
import com.bluecode.weledger.FacilitatorGroupsActivity;
import com.bluecode.weledger.FacilitatorMembersActivity;
import com.bluecode.weledger.LoanRequestsApprovalsActivity;
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
                        Intent bookAdmin = new Intent(context, FacilitatorMembersActivity.class);
                           context.startActivity(bookAdmin);
                        break;
                    case 2:
                        Intent bookSaving= new Intent(context, BookwriterSavingsOptions.class);
                        context.startActivity(bookSaving);
                        break;
                    case 3:
                        Intent bookLoan= new Intent(context, LoanRequestsApprovalsActivity.class);
                        context.startActivity(bookLoan);
                        break;
                    case 4:
                        Intent bookRepayment= new Intent(context, BookwriterRepaymentsActivity.class);
                        context.startActivity(bookRepayment);
                        break;
                    case 5:
                        Intent bookFines= new Intent(context, BookwriterFinesActivity.class);
                        context.startActivity(bookFines);
                        break;
                    case 6:
//                        Intent bookSocial= new Intent(context, BookwriterSocialFundsActivity.class);
//                        context.startActivity(bookSocial);
                        break;
                    case 7:
                        Intent groupAdmin = new Intent(context, FacilitatorGroupsActivity.class);
                        context.startActivity(groupAdmin);
                        break;
                    case 8:
                        Intent groupSaving = new Intent(context, FacilitatorGroupSavings.class);
                        context.startActivity(groupSaving);
                        break;
                    case 9:
                        Intent groupLoan = new Intent(context, FacilitatorGroupLoans.class);
                        context.startActivity(groupLoan);
                        break;
                    case 10:
                        Intent groupRepayments = new Intent(context, FacilitatorGroupRepayments.class);
                        context.startActivity(groupRepayments);
                        break;
                    case 11:
                        Intent groupFines = new Intent(context, FacilitatorGroupFines.class);
                        context.startActivity(groupFines);
                        break;
                    case 12:
//                        Intent groupLedger = new Intent(context, FacilitatorGroupLoans.class);
//                        context.startActivity(groupLedger);
                        Toast.makeText(context,"Work in progress",Toast.LENGTH_SHORT).show();
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
