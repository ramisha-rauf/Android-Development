package com.example.devilapplication.recylerview;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devilapplication.recylerview.database.ListItemEntity;
import com.example.devilapplication.R;
import com.example.devilapplication.recylerview.Mvvm.ViewModel;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListItemEntity> items;
    private ViewModel viewModel;

    public ListAdapter(List<ListItemEntity> items, ViewModel viewModel) {
        this.items = items;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ListItemEntity item = items.get(position);
        holder.nameTextView.setText(item.getName());
        holder.emailTextView.setText(item.getEmail());

        holder.deleteitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the corresponding entity/item
                ListItemEntity entity = items.get(position);

                // Call the delete method in the ViewModel
                viewModel.delete(entity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView deleteitem;
        TextView nameTextView;
        TextView emailTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            deleteitem = itemView.findViewById(R.id.deleteitem);
        }
    }

    public void setData(List<ListItemEntity> newData) {
        Log.d("MyApp", "Setting new data to the adapter");
        this.items.clear();
        this.items.addAll(newData);
        notifyDataSetChanged();
    }
}
