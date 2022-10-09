package com.bluecode.weledger.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluecode.weledger.R;
import com.bluecode.weledger.models.FacilitatorViewMembersModel;
import com.bluecode.weledger.models.Groups;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class FacilitatorViewGroupMembersAdapter extends RecyclerView.Adapter<FacilitatorViewGroupMembersAdapter.viewHolder> {
    Context context;
    ArrayList<FacilitatorViewMembersModel> listGroups;
    View.OnClickListener clickListener;
    private Groups currentGroups;

    public FacilitatorViewGroupMembersAdapter(Context context, ArrayList<FacilitatorViewMembersModel> listGroups) {
        this.context = context;
        this.listGroups = listGroups;
    }

    @NonNull
    @Override
    public FacilitatorViewGroupMembersAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_groups, parent, false);
        FacilitatorViewGroupMembersAdapter.viewHolder binder = new FacilitatorViewGroupMembersAdapter.viewHolder(view);
        return binder;
    }

    @Override
    public void onBindViewHolder(@NonNull FacilitatorViewGroupMembersAdapter.viewHolder holder, int position) {
        final FacilitatorViewMembersModel currentGroups = listGroups.get(position);
        holder.group_name.setSelected(true);
        holder.group_name.setText(String.valueOf(currentGroups.getGroup_name()));

    }

    @Override
    public int getItemCount() {
        return listGroups.size();
    }

    public void setClickListener(View.OnClickListener callback) {
        clickListener = callback;
    }

    public void setGroups(List<FacilitatorViewMembersModel> listGroups) {
        this.listGroups = (ArrayList<FacilitatorViewMembersModel>) listGroups;
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView group_name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            group_name = itemView.findViewById(R.id.group_name);
        }
    }
}