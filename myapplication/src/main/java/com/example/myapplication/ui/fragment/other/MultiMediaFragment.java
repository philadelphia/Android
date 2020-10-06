package com.example.myapplication.ui.fragment.other;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

import com.example.myapplication.R;

import java.io.File;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class MultiMediaFragment extends Fragment implements View.OnClickListener {
    private Button btn_play;
    private  Button btn_pause;
    private  Button btn_stop;
    private MediaPlayer mediaPlayer;
    private VideoView videoView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_multi_media, container, false);
        initView(view);
        mediaPlayer = new MediaPlayer();
//        initMediaPlayer();
        initVedioView();
        return  view;

    }

    private void initMediaPlayer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(),"hangouts_incoming_call.ogg");
            mediaPlayer.setDataSource(file.getPath());
//            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initVedioView(){
        File file = new File(Environment.getExternalStorageDirectory(),"Wildlife.wmv");
        videoView.setVideoPath(file.getPath());
    }
    private void initView(View view) {
        btn_play = (Button) view.findViewById(R.id.btn_play);
        btn_pause = (Button) view.findViewById(R.id.btn_pause);
        btn_stop = (Button) view.findViewById(R.id.btn_stop);
        videoView = (VideoView) view.findViewById(R.id.videoView);
        btn_play.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
    }


    /**
     *  Music Player
     */

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btn_play:
//                if(! mediaPlayer.isPlaying()){
//                    mediaPlayer.start();
//                }
//                break;
//            case R.id.btn_pause:
//                if(mediaPlayer.isPlaying())
//                    mediaPlayer.pause();
//                break;
//            case R.id.btn_stop:
//                if (mediaPlayer.isPlaying())
//                    mediaPlayer.stop();
//                break;
//            default:
//                break;
//        }
//    }

    /**
     * Video Player
     *
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_play:
                if(! videoView.isPlaying()){
                    videoView.start();
                }
                break;
            case R.id.btn_pause:
                if(videoView.isPlaying())
                    videoView.pause();
                break;
            case R.id.btn_stop:
                if (videoView.isPlaying())
                    videoView.resume();
                break;
            default:
                break;
        }
    }


    @Override
    public  void  onDestroy(){
        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        if (videoView != null){
            videoView.suspend();
        }
        super.onDestroy();

    }
}
