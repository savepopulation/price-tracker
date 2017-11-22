package com.raqun.android.util

import android.content.Context
import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.graphics.drawable.Drawable
import com.raqun.android.R


/**
 * Created by tyln on 28/09/2017.
 */
class DividerDecorator(context: Context) : RecyclerView.ItemDecoration() {
    private val divider: Drawable

    init {
        divider = context.getResources().getDrawable(R.drawable.divider)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + divider.intrinsicHeight

            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }
}