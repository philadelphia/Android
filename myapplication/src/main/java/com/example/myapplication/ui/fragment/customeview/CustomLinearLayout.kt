package com.example.myapplication.ui.fragment.customeview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlin.math.max

/**
@Author zhang tao
@Date   6/23/22 11:12 PM
@Desc 自定义ViewGroup，仿LinearLayout，只支持竖直方向
 */
private const val TAG = "CustomLinearLayout"

class CustomLinearLayout(context: Context, attributes: AttributeSet?, defStyle: Int) : ViewGroup(context, attributes, defStyle) {

    constructor(context: Context, attributes: AttributeSet?) : this(context, attributes, 0)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.d(TAG, "onMeasure: ")
        val widthMeasured = MeasureSpec.getSize(widthMeasureSpec)
        val heightMeasured = MeasureSpec.getSize(heightMeasureSpec)
        val widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec)

        var width = 0
        var height = 0
        val childCount = childCount
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val marginLayoutParams = child.layoutParams as MarginLayoutParams

            val childWidth = child.measuredWidth + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin
            val childHeight = child.measuredHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin
            width = max(width, childWidth)
            height += childHeight
            Log.d(TAG, "onMeasure: 第$(i+1)次测量的高度是 $height")
        }

        height += paddingTop
        height += paddingBottom
        val finalWidth: Int
        val finalHeight: Int
        finalWidth = when (widthMeasureMode) {
            MeasureSpec.EXACTLY ->
                widthMeasured
            MeasureSpec.AT_MOST ->
                width
            else ->
                width
        }

        finalHeight = when (heightMeasureMode) {
            MeasureSpec.EXACTLY ->
                heightMeasured
            MeasureSpec.AT_MOST ->
                height
            else ->
                height
        }

        setMeasuredDimension(finalWidth, finalHeight)

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.d(TAG, "onLayout: changed = $changed, l = $l, t= $t, r=$r, b =$b")
        var top = 0
        top += paddingTop

        for (i in 0 until childCount) {
            var left = paddingLeft
            val child: View = getChildAt(i)
            val marginLayoutParams = child.layoutParams as MarginLayoutParams
            left += marginLayoutParams.leftMargin
            top += marginLayoutParams.topMargin
            var right = 0
            right = if (r > child.measuredWidth + left + marginLayoutParams.leftMargin) {
                child.measuredWidth + left + marginLayoutParams.leftMargin - marginLayoutParams.rightMargin - paddingRight
            } else {
                r - marginLayoutParams.rightMargin - paddingRight
            }
            val bottom = top + child.measuredHeight + marginLayoutParams.bottomMargin
            child.layout(left, top, right, bottom)
            top = bottom
        }

    }


    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(MarginLayoutParams.MATCH_PARENT, MarginLayoutParams.MATCH_PARENT)
    }
}