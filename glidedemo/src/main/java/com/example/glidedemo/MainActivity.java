package com.example.glidedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.glidedemo.R;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnShow;
    private Button btnShowGif;


    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnShow = (Button) findViewById(R.id.btn_show);
        btnShowGif = (Button) findViewById(R.id.btn_showgif);
        btnShow.setOnClickListener(this);
        btnShowGif.setOnClickListener(this);
        img = (ImageView) findViewById(R.id.img);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_show:
                fetchPicture();
                break;
            case R.id.btn_showgif:
                btnShowGif();
                break;
            default:
                break;
        }
    }

    private void btnShowGif() {
        Glide.with(this)
                .load("http://i.kinja-img.com/gawker-media/image/upload/s--B7tUiM5l--/gf2r69yorbdesguga10i.gif")
                .asGif()
                .placeholder(R.mipmap.ic_map_black_48dp)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .fitCenter()
                .into(img);

    }

    private void fetchPicture() {
        Glide.with(this)
                .load("http://58pic.ooopic.com/58pic/16/55/32/03v58PICnpm.jpg")
                .placeholder(R.mipmap.ic_map_black_48dp)
                .error(R.mipmap.ic_launcher)
                .crossFade()
//                .dontAnimate()
//                .fitCenter()
                .centerCrop()
                .into(img);

    }
}
