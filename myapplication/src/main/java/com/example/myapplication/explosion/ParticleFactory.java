package com.example.myapplication.explosion;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * @author zhangtao
 * @date 2019-06-14
 **/
public abstract class ParticleFactory  {
    float radius = 100;
    float alpha = 1.0f;

    public abstract  Particle[][] generateParticles(Bitmap bitmap, Rect bound);

}
