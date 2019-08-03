package com.example.asm_android_networking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asm_android_networking.R;
import com.example.asm_android_networking.Repository.Manga;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Recyclerview_adapter extends RecyclerView.Adapter<Recyclerview_adapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Manga> manga = new ArrayList<>();

    private onClicklistener onClicklistener;
    private Context context;


    public Recyclerview_adapter(Context context,ArrayList<Manga> manga, onClicklistener onClicklistener) {
        this.manga = manga;
        this.context = context;
        this.onClicklistener = onClicklistener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
       ViewHolder holder = new ViewHolder(view,onClicklistener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).asBitmap().load(manga.get(position).getImg()).into(holder.image_item);
        holder.tvname_item.setText(manga.get(position).getName());
        holder.tvstatus_item.setText(" Chap: " + String.valueOf(manga.get(position).getStatus()));
        holder.tvtacgia_item.setText(manga.get(position).getTacgia());
        String[] cates = manga.get(position).getCategori();
        StringBuilder buffer = new StringBuilder();
        for (String each : cates)
            buffer.append(",").append(each);
        String joined = buffer.deleteCharAt(0).toString();
        holder.tvcategori_item.setText(joined);


    }

    @Override
    public int getItemCount() {
        return manga.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        CardView layout_parent_item;
        TextView tvname_item, tvstatus_item, tvtacgia_item, tvcategori_item;
        CircleImageView image_item;
        onClicklistener onClicklistener;
        public ViewHolder(View view, onClicklistener onClicklistener){
            super(view);
            layout_parent_item = view.findViewById(R.id.layout_parent_item);
            tvname_item = view.findViewById(R.id.tvname_item);
            tvstatus_item = view.findViewById(R.id.tvstatus_item);
            tvtacgia_item = view.findViewById(R.id.tvtacgia_item);
            tvcategori_item = view.findViewById(R.id.tvcategori_item);
            image_item = view.findViewById(R.id.image_item);
            this.onClicklistener = onClicklistener;
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {

            onClicklistener.OnTypeClick(getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View view) {
            onClicklistener.OnTypeClick(getAdapterPosition(),true);
            return true;
        }
    }
    public  interface  onClicklistener{
        void OnTypeClick(int position, boolean typeClick);
    }

}
