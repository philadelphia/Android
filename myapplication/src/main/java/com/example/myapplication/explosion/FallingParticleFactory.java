package com.example.myapplication.explosion;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * @author zhangtao
 * @date 2019-06-14
 **/
public class FallingParticleFactory extends ParticleFactory {
    public static final int PART_WH = 8;

    @Override
    public Particle[][] generateParticles(Bitmap bitmap, Rect bound) {
        int w = bound.width();
        int h = bound.height();

        int partW_count = w / PART_WH; //列数
        int partH_count = h / PART_WH; //行数
        partW_count = partW_count > 0 ? partW_count : 1;
        partH_count = partH_count > 0 ? partW_count : 1;
        int bitmap_part_w = bitmap.getWidth() / partW_count; //列
        int bitmap_part_h = bitmap.getHeight() / partH_count; //行

        Particle[][] particles = new Particle[partH_count][partW_count];
        for (int row = 0; row < partW_count; row++) {
            for (int column = 0; column < partH_count; column++) {
                //获取当前例子所在的位置的颜色
                int color = bitmap.getPixel(column * bitmap_part_w, row * bitmap_part_h);
                float x = bound.left + PART_WH * column;
                float y = bound.top + PART_WH * row;
                particles[row][column] = new FallingParticle(x, y, color, bound);
            }
        }
        return particles;
    }
}
