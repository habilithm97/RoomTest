package com.example.roomtest;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<MainModel> items;
    private Activity context;
    private RoomDB database;

    public ItemAdapter(Activity context, List<MainModel> items) {
        this.context = context;
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        MainModel item = items.get(position);
        database = RoomDB.getInstance(context);

        holder.itemTv.setText(item.getText());

        holder.edtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainModel item = items.get(holder.getAdapterPosition());
                int uID = item.getId();
                String uText = item.getText();

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.update_dialog);
                // 대화상자를 단말 넓이에 맞춤
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width, height);
                dialog.show();

                EditText dialogEdt = dialog.findViewById(R.id.dialogEdt);
                Button updateBtn = dialog.findViewById(R.id.updateBtn);

                dialogEdt.setText(uText);

                updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss(); // 대화상자 종료(대화상자의 버튼을 눌러 종료된 경우, cancel()은 뒤로가기 버튼을 눌러 종료된 경우)
                        String uText = dialogEdt.getText().toString().trim();

                        database.mainDao().update(uID, uText);
                        items.clear();
                        items.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();
                    }
                });
            }
        });

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainModel item = items.get(holder.getAdapterPosition());
                database.mainDao().delete(item);

                int position = holder.getAdapterPosition();
                items.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, items.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTv;
        ImageView edtBtn, deleteBtn;

        public ViewHolder(@NonNull View view) {
            super(view);
            itemTv = view.findViewById(R.id.itemTv);
            edtBtn = view.findViewById(R.id.edtBtn);
            deleteBtn = view.findViewById(R.id.deleteBtn);
        }
    }
}