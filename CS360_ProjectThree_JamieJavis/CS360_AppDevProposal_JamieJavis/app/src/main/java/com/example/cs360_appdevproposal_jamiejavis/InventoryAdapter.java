package com.example.cs360_appdevproposal_jamiejavis;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {
    private Context context;
    private Cursor cursor;

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close(); // close previous cursor
        }
        cursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged(); // notify data set has changed
        }
    }


    public InventoryAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new InventoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        if (!cursor.moveToPosition(position)) {
            return;
        }

        String itemName = cursor.getString(cursor.getColumnIndexOrThrow("item_name"));
        int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));

        holder.itemNameTextView.setText(itemName);
        holder.itemQuantityTextView.setText(String.valueOf(quantity));
    }


    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class InventoryViewHolder extends RecyclerView.ViewHolder {
        public TextView itemNameTextView;
        public TextView itemQuantityTextView;

        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            itemQuantityTextView = itemView.findViewById(R.id.itemQuantityTextView);
        }
    }
}
