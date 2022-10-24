package com.bluecode.weledger.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluecode.weledger.R;
import com.bluecode.weledger.models.Groups;
import com.bluecode.weledger.models.TotalSocialFundModel;

import java.util.ArrayList;

public class TotalSocialFundAdapter extends RecyclerView.Adapter<TotalSocialFundAdapter.ViewHolder> {
   ArrayList<TotalSocialFundModel> totalSocialFundModel;
   Context context;
   
    public TotalSocialFundAdapter(Context context, ArrayList<TotalSocialFundModel> totalSocialFundModel) {
        this.context = context;
        this.totalSocialFundModel = totalSocialFundModel;
    }
    
    @NonNull
    @Override
    public TotalSocialFundAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.social_fund_total,parent,false);
        TotalSocialFundAdapter.ViewHolder binder = new TotalSocialFundAdapter.ViewHolder(view);
        return binder;
    }

    @Override
    public void onBindViewHolder(@NonNull TotalSocialFundAdapter.ViewHolder holder, int position) {
    final TotalSocialFundModel model = totalSocialFundModel.get(position);
    holder.total_amount_social.setText(model.getAmount());
    }

    @Override
    public int getItemCount() {
        return totalSocialFundModel.size();
    }

    public void setClickListener(View.OnClickListener onClickListener) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView total_amount_social;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            total_amount_social = itemView.findViewById(R.id.total_amount_social);
        }
    }
}
