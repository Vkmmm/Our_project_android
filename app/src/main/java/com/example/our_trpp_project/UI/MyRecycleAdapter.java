package com.example.our_trpp_project.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.our_trpp_project.R;

import java.util.List;

public class MyRecycleAdapter extends RecyclerView.Adapter< MyRecycleAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    private final List<Item> items;
    private OnItemClickListener listener;
    MyRecycleAdapter(Context context, List<Item> items) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public MyRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MyRecycleAdapter.ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.textView.setText(item.getText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(item);
                }
            }
        });
    }
    public static class ViewHolder extends
            RecyclerView.ViewHolder {
        final TextView textView;
        ViewHolder(View view){
            super(view);
            textView = view.findViewById(R.id.textView);
        }
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}

