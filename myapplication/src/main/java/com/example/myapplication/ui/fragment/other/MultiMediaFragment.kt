package com.example.myapplication.ui.fragment.other

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import java.io.File
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 */
class MultiMediaFragment : Fragment(), View.OnClickListener {
    private var btn_play: Button? = null
    private var btn_pause: Button? = null
    private var btn_stop: Button? = null
    private var mediaPlayer: MediaPlayer? = null
    private var videoView: VideoView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_multi_media, container, false)
        initView(view)
        mediaPlayer = MediaPlayer()
        //        initMediaPlayer();
        initVedioView()
        return view
    }

    private fun initMediaPlayer() {
        try {
            val file = File(Environment.getExternalStorageDirectory(), "hangouts_incoming_call.ogg")
            mediaPlayer!!.setDataSource(file.path)
            //            mediaPlayer.prepare();
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun initVedioView() {
        val file = File(Environment.getExternalStorageDirectory(), "Wildlife.wmv")
        videoView!!.setVideoPath(file.path)
    }

    private fun initView(view: View) {
        btn_play = view.findViewById<View>(R.id.btn_play) as Button
        btn_pause = view.findViewById<View>(R.id.btn_pause) as Button
        btn_stop = view.findViewById<View>(R.id.btn_stop) as Button
        videoView = view.findViewById<View>(R.id.videoView) as VideoView
        btn_play!!.setOnClickListener(this)
        btn_pause!!.setOnClickListener(this)
        btn_stop!!.setOnClickListener(this)
    }
    /**
     * Music Player
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
    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_play -> if (!videoView!!.isPlaying) {
                videoView!!.start()
            }
            R.id.btn_pause -> if (videoView!!.isPlaying) videoView!!.pause()
            R.id.btn_stop -> if (videoView!!.isPlaying) videoView!!.resume()
            else -> {
            }
        }
    }

    override fun onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
        }
        if (videoView != null) {
            videoView!!.suspend()
        }
        super.onDestroy()
    }
}