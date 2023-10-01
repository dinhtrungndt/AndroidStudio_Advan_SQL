package com.example.asm1_nguyendinhtrung_pk02294.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asm1_nguyendinhtrung_pk02294.R;
import com.example.asm1_nguyendinhtrung_pk02294.activity.WebViewActivity;
import com.example.asm1_nguyendinhtrung_pk02294.model.channelEduNews;
import com.example.asm1_nguyendinhtrung_pk02294.model.itemEduUserNews;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<itemEduUserNews> list;

    public NewsAdapter(Context context, ArrayList<itemEduUserNews> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_news, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitle.setText(list.get(position).getTitle());
        holder.txtDescription.setText(list.get(position).getDescription().get__cdata());
        holder.txtPubDate.setText(list.get(position).getPubDate());
        holder.txtLink.setText(list.get(position).getLink());
        holder.txtGuid.setText(list.get(position).getGuid());
        holder.txtComments.setText(list.get(position).getComments() +"");

        //Glide
        Glide
                .with(context)
                .load(list.get(position).getTitle())
                .load(list.get(position).getDescription())
                .load(list.get(position).getPubDate())
                .load(list.get(position).getLink())
                .load(list.get(position).getGuid())
                .load(list.get(position).getComments())
                .centerCrop()
                .placeholder(R.drawable.acc_login);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                // list.get(holder.getAdapterPosition()).getLink();
                intent.putExtra("link", list.get(holder.getAdapterPosition()).getLink());
                context.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle,txtDescription,txt__cdata,txtPubDate,txtLink,txtGuid,txtComments;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txt__cdata = itemView.findViewById(R.id.txt__cdata);
            txtPubDate = itemView.findViewById(R.id.txtPubDate);
            txtLink = itemView.findViewById(R.id.txtLink);
            txtGuid = itemView.findViewById(R.id.txtGuid);
            txtComments = itemView.findViewById(R.id.txtComments);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
