package com.itismob.s03.group2.KaBarangay.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itismob.s03.group2.KaBarangay.R;
import com.itismob.s03.group2.KaBarangay.models.Hotline;

import java.util.List;

public class HotlineAdapter extends RecyclerView.Adapter<HotlineAdapter.HotlineViewHolder> {

    private final List<Hotline> hotlines;

    public HotlineAdapter(List<Hotline> hotlines) {
        this.hotlines = hotlines;
    }

    @NonNull
    @Override
    public HotlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hotline, parent, false);
        return new HotlineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotlineViewHolder holder, int position) {
        Hotline hotline = hotlines.get(position);

        holder.tvCategory.setText(hotline.getCategory());
        holder.tvTitle.setText(hotline.getTitle());
        holder.tvDescription.setText(hotline.getDescription());
        holder.tvBadge.setText(hotline.getBadgeText());
        holder.tvNumberLabel.setText(hotline.getNumberLabel());
        holder.tvNumber.setText(hotline.getNumber());
        holder.ivIcon.setImageResource(hotline.getIconResId());
    }

    @Override
    public int getItemCount() {
        return hotlines.size();
    }

    public static class HotlineViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory, tvTitle, tvDescription, tvBadge, tvNumberLabel, tvNumber;
        ImageView ivIcon;

        public HotlineViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvBadge = itemView.findViewById(R.id.tvBadge);
            tvNumberLabel = itemView.findViewById(R.id.tvNumberLabel);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }
    }
}