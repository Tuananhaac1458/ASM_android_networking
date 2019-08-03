package com.example.asm_android_networking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GenericTransitionOptions;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class InforMangaActivity extends AppCompatActivity {
    TextView namemanga;
    Context context;
    ImageView imgbackground;
    CircleImageView  imgmanga;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_manga);
        namemanga = findViewById(R.id.namemanga);
        imgbackground = findViewById(R.id.imgbackground);
        imgmanga = findViewById(R.id.imgmanga);
          //  namemanga.setText(getIntent().getStringExtra("Name"));

        Glide.with(this).asBitmap().load("https://www.nhunao.com/wp-content/uploads/2019/05/Culture_DragonBallBroly-2.jpg").apply(bitmapTransform(new BlurTransformation(7,3))).into(imgbackground);

        Glide.with(this).asBitmap().load("https://www.nhunao.com/wp-content/uploads/2019/05/Culture_DragonBallBroly-2.jpg").into(imgmanga);

    }


}
