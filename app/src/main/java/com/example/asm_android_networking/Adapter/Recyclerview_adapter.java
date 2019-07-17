package com.example.asm_android_networking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asm_android_networking.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Recyclerview_adapter extends RecyclerView.Adapter<Recyclerview_adapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mStatus = new ArrayList<>();
    private ArrayList<String> mTacgias = new ArrayList<>();
    private ArrayList<String> mCategoris = new ArrayList<>();
    private Context context;

    public Recyclerview_adapter(Context context,ArrayList<String> mImages, ArrayList<String> mNames, ArrayList<String> mStatus, ArrayList<String> mTacgias, ArrayList<String> mCategoris) {
        this.mImages = mImages;
        this.mNames = mNames;
        this.mStatus = mStatus;
        this.mTacgias = mTacgias;
        this.mCategoris = mCategoris;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
       ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).asBitmap().load(mImages.get(position)).into(holder.image_item);
        holder.tvname_item.setText(mNames.get(position));
        holder.tvstatus_item.setText(mStatus.get(position));
        holder.tvtacgia_item.setText(mTacgias.get(position));
        holder.tvcategori_item.setText(mCategoris.get(position));
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView layout_parent_item;
        TextView tvname_item, tvstatus_item, tvtacgia_item, tvcategori_item;
        CircleImageView image_item;

        public ViewHolder(View view){
            super(view);
            layout_parent_item = view.findViewById(R.id.layout_parent_item);
            tvname_item = view.findViewById(R.id.tvname_item);
            tvstatus_item = view.findViewById(R.id.tvstatus_item);
            tvtacgia_item = view.findViewById(R.id.tvtacgia_item);
            tvcategori_item = view.findViewById(R.id.tvcategori_item);
            image_item = view.findViewById(R.id.image_item);
        }
    }


}
