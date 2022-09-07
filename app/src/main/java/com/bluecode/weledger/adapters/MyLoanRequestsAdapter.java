package com.bluecode.weledger.adapters;
/*
 * Created by Harold King on 6/03/2019.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bluecode.weledger.R;
import com.bluecode.weledger.models.LoanRequests;

import java.util.ArrayList;
import java.util.List;


public class MyLoanRequestsAdapter extends RecyclerView.Adapter<MyLoanRequestsAdapter.SingleViewHolder> {

    public static List<LoanRequests> listLoanRequests;
    private LoanRequests currentLoanRequests;
    private Context mContext;
    private List<LoanRequests> listFullLoanRequests;
    View.OnClickListener clickListener;
    // if checkedPosition = -1, there is no default selection
    // if checkedPosition = 0, 1st item is selected by default
    private int checkedPosition = -1;

    //constructor
    public MyLoanRequestsAdapter(Context context, ArrayList<LoanRequests> listLoanRequests) {
        this.mContext = context;
        this.listLoanRequests = listLoanRequests;
        listFullLoanRequests = new ArrayList<>(listLoanRequests);

    }

    public void setClickListener(View.OnClickListener callback) {
        clickListener = callback;
    }

    public void setLoanRequests(List<LoanRequests> listLoanRequests) {
        this.listLoanRequests = listLoanRequests;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return listLoanRequests.size();
    }

    public class SingleViewHolder extends RecyclerView.ViewHolder {

        public TextView amount, period;
        LinearLayout loan_details;
        ImageView secretary_approval_img,chairperson_approval_img,treasurer_approval_img;

        public SingleViewHolder(View itemView) {
            super(itemView);

            amount = itemView.findViewById(R.id.amount);
            period = itemView.findViewById(R.id.period);
            chairperson_approval_img = itemView.findViewById(R.id.chairperson_approval_img);
            treasurer_approval_img = itemView.findViewById(R.id.treasurer_approval_img);
            secretary_approval_img = itemView.findViewById(R.id.secretary_approval_img);
            loan_details = itemView.findViewById(R.id.loan_details);

        }//ends public viewholder

       /* void bind(final LoanRequests transactions) {
            initials.setText(transactions.getInitials());
            fullname.setText(transactions.getFullname());
            transaction_type_date.setText(transactions.getTransaction_type());
            amount.setText(transactions.getTransaction_amount());
//
//
        }*/
    }

    @Override
    public SingleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //inflating the layouts of each row
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_my_loan_requests, parent, false);
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SingleViewHolder holder, int position) {
        currentLoanRequests = listLoanRequests.get(position);
//        LoanRequests LoanRequests = listLoanRequests.get(holder.getAdapterPosition());

        holder.amount.setText(String.valueOf("K "+currentLoanRequests.getAmount()));
        holder.period.setSelected(true);
        holder.period.setText(String.valueOf(currentLoanRequests.getDuration()+"mths ("+ currentLoanRequests.getStart_date() +" - "+currentLoanRequests.getEnd_date()+")"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickListener.onClick(view);
            }
        });
    }


}
