package com.savantspender.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.savantspender.R;
import com.savantspender.db.entity.Transaction;

import java.util.LinkedList;
import java.util.List;

public class TransactionViewAdapter extends RecyclerView.Adapter<TransactionViewAdapter.ViewHolder> {
    private List<? extends Transaction> mData = new LinkedList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_transaction, parent, false);
        ViewHolder vh = new ViewHolder(view, this);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction trans = mData.get(position);

        holder.txtTransactionName.setText(trans.getName());
        holder.txtTransactionPrice.setText(Double.toString(trans.getAmount()));

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void submitData(@NonNull List<? extends Transaction> transactions) {
        mData = transactions;

        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtTransactionName;
        private final TextView txtTransactionPrice;
        private final TextView txtTransactionDate;
        private final ImageView btnViewDetails;


        public ViewHolder(@NonNull View itemView, @NonNull TransactionViewAdapter adapter) {
            super(itemView);

            txtTransactionName = itemView.findViewById(R.id.txtTransName);
            txtTransactionPrice = itemView.findViewById(R.id.txtTransPrice);
            txtTransactionDate = itemView.findViewById(R.id.txtTransDate);
            btnViewDetails = itemView.findViewById(R.id.btnDetails);
            btnViewDetails.setOnClickListener(i -> Log.w("Spender", "view details here")); // todo
        }

        public void bind(boolean isSelected) {
            itemView.setActivated(isSelected);
        }
    }
}
