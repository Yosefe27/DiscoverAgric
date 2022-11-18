package com.bluecode.weledger.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluecode.weledger.R;
import com.bluecode.weledger.models.LoanRepaymentModel;
import com.bluecode.weledger.models.LoanRequests;

import java.util.ArrayList;
import java.util.List;

public class LoanRepaymentAdapter extends RecyclerView.Adapter<LoanRepaymentAdapter.ViewHolder> {
    ArrayList<LoanRepaymentModel> listLoanRequests;
    private Context mContext;
    View.OnClickListener clickListener;


    //constructor
    public LoanRepaymentAdapter(Context context, ArrayList<LoanRepaymentModel> listLoanRequests) {
        this.mContext = context;
        this.listLoanRequests = listLoanRequests;


    }
    public void setClickListener(View.OnClickListener callback) {
        clickListener = callback;
    }
    @NonNull
    @Override
    public LoanRepaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_loan_repayment, parent, false);
        LoanRepaymentAdapter.ViewHolder bind = new LoanRepaymentAdapter.ViewHolder(view);
        return bind;


    }

    @Override
    public void onBindViewHolder(@NonNull LoanRepaymentAdapter.ViewHolder holder, int position) {
        final  LoanRepaymentModel loanRepaymentModel = listLoanRequests.get(position);
        holder.amount.setText(loanRepaymentModel.getFull_name()+" made repayment of K"+loanRepaymentModel.getAmount());
        holder.date_created.setText("On: "+loanRepaymentModel.getDate_created()+" ID: "+loanRepaymentModel.getId());
    }

    @Override
    public int getItemCount() {
        return listLoanRequests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView amount, date_created;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.amount);
            date_created = itemView.findViewById(R.id.date_created);
        }
    }
}
