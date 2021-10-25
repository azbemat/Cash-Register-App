package ca.senecacollege.cashregister;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    ArrayList<HistoryM> listOfHistory;
    Context context;

    public interface OnItemClickListener{
        void onHistoryItemClick(HistoryM item);
    }

    private final OnItemClickListener listener;

    public HistoryAdapter(ArrayList<HistoryM> listOfHistory, Context context, OnItemClickListener listenerFromActivity) {
        this.listOfHistory = listOfHistory;
        this.context = context;
        listener = listenerFromActivity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView total;
        private final TextView quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.product_name_h);
            total = itemView.findViewById(R.id.product_total_h);
            quantity = itemView.findViewById(R.id.product_quantity_h);

        }

        public TextView getName() {
            return name;
        }

        public TextView getTotal() {
            return total;
        }

        public TextView getQuantity() {
            return quantity;
        }
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View myview = inflater.inflate(R.layout.activity_history_list, parent, false);

        return new HistoryAdapter.ViewHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        holder.getName().setText(listOfHistory.get(position).m_name);
        holder.getTotal().setText(Double.toString(listOfHistory.get(position).m_price));
        holder.getQuantity().setText(Integer.toString(listOfHistory.get(position).m_quantity));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onHistoryItemClick(listOfHistory.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfHistory.size();
    }
}
