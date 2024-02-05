package com.example.devilapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devilapplication.R;
import com.example.devilapplication.Room.Users;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private List<Users> usersList;
    private AdapterListener adapterListener;
    public UserAdapter( Context context,AdapterListener listener){
        this.context=context;
        usersList=new ArrayList<>();
        this.adapterListener=listener;
    }
    public void addUser(Users users){
        usersList.add(users);
        notifyDataSetChanged();
    }

    public void removeUser(int position){
        usersList.remove(position);
        notifyDataSetChanged();
    }

    public void clearData(){
        usersList.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Users users = usersList.get(position);
        holder.name.setText(users.getName());
        holder.email.setText(users.getEmail());
        //holder.contact.setText(users.getContact());

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    adapterListener.OnUpdate(users);
                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    adapterListener.OnDelete(users.getId(), adapterPosition);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public void setusersList(List<Users> users) {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name,email,contact;
        private ImageView update,delete;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
           // contact = itemView.findViewById(R.id.contact);
            update=itemView.findViewById(R.id.update);
            delete=itemView.findViewById(R.id.delete);
        }

    }
}
