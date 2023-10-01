package com.example.asm1_nguyendinhtrung_pk02294.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm1_nguyendinhtrung_pk02294.R;
import com.example.asm1_nguyendinhtrung_pk02294.model.MonHoc;
import com.example.asm1_nguyendinhtrung_pk02294.service.DangKyVaHuyMHService;

import java.util.ArrayList;

public class ListCourseAdapter extends RecyclerView.Adapter<ListCourseAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MonHoc> list;

    public ListCourseAdapter(Context context, ArrayList<MonHoc> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.list_course_item_monhoc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtCode.setText(list.get(position).getCode());
        holder.txtName.setText(list.get(position).getName());
        holder.txtTeacher.setText(list.get(position).getTeacher());

        SharedPreferences sharedPreferences = context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        int idNguoiDung = sharedPreferences.getInt("idNguoiDung", -1);

        if (idNguoiDung == list.get(holder.getAdapterPosition()).getId()) {
            holder.btnDangKy.setText("Hủy đăng ký");
            holder.btnDangKy.setBackgroundColor(Color.RED);
        } else {
            holder.btnDangKy.setText("Đăng ký");
            holder.btnDangKy.setBackgroundColor(Color.BLUE);
        }

        holder.btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // type: 1: đăng ký, 0: Hủy đăng ký
                int type = -1;
                if (idNguoiDung == list.get(holder.getAdapterPosition()).getId()){
                    type = 0;
                }else {
                    type = 1;
                }

                Intent intent = new Intent(context, DangKyVaHuyMHService.class);
                Bundle bundle = new Bundle();
                bundle.putInt("idNguoiDung", idNguoiDung);
                bundle.putString("code", list.get(holder.getAdapterPosition()).getCode());
                bundle.putInt("type", type);
                intent.putExtras(bundle);
                context.startService(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCode, txtName, txtTeacher;
        Button btnDangKy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCode = itemView.findViewById(R.id.txtCode);
            txtName = itemView.findViewById(R.id.txtName);
            txtTeacher = itemView.findViewById(R.id.txtTeacher);
            btnDangKy = itemView.findViewById(R.id.btnDangKy);

        }
    }

}
