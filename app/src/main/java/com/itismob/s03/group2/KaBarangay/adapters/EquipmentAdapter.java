package com.itismob.s03.group2.KaBarangay.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itismob.s03.group2.KaBarangay.R;
import com.itismob.s03.group2.KaBarangay.models.EquipmentItem;

import java.util.ArrayList;
import java.util.List;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder> {
	public interface OnEquipmentClickListener {
		void onEquipmentClick(EquipmentItem item);
	}

	private final List<EquipmentItem> items = new ArrayList<>();
	private final OnEquipmentClickListener listener;

	public EquipmentAdapter(List<EquipmentItem> items, OnEquipmentClickListener listener) {
		this.items.addAll(items);
		this.listener = listener;
	}

	public void updateItems(List<EquipmentItem> updatedItems) {
		items.clear();
		items.addAll(updatedItems);
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public EquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_equipment, parent, false);
		return new EquipmentViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull EquipmentViewHolder holder, int position) {
		EquipmentItem item = items.get(position);
		holder.tvCategory.setText(item.getCategory());
		holder.tvTitle.setText(item.getTitle());
		holder.tvDescription.setText(item.getDescription());
		holder.tvAvailability.setText(item.getAvailability());
		holder.ivImage.setImageResource(item.getImageResId());
		holder.itemView.setOnClickListener(v -> listener.onEquipmentClick(item));
	}

	@Override
	public int getItemCount() { return items.size(); }

	public static class EquipmentViewHolder extends RecyclerView.ViewHolder {
		final ImageView ivImage;
		final TextView tvCategory, tvTitle, tvDescription, tvAvailability;

		EquipmentViewHolder(@NonNull View itemView) {
			super(itemView);
			ivImage = itemView.findViewById(R.id.ivEquipmentImage);
			tvCategory = itemView.findViewById(R.id.tvEquipmentCategory);
			tvTitle = itemView.findViewById(R.id.tvEquipmentTitle);
			tvDescription = itemView.findViewById(R.id.tvEquipmentDescription);
			tvAvailability = itemView.findViewById(R.id.tvEquipmentAvailability);
		}
	}
}
