package com.raqun.android.ui.view

import android.content.Context
import android.graphics.Rect
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewCompat
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

/**
 * Created by tyln on 06/09/2017.
 */

class FabScrollBehavior
/**

 * Suppoert Library 25.1 ve sonrasında Coordiantor Layout view'i hide ettikten sonra
 * visiblity'sini GONE'a çekiyor bu nedenle FabBehavior problem yaşıyor.
 * Support Library 25.1.0 ve üzerine çekildiğinde bu Listener açılmalı ve
 * child.hide() çağırılırken parametre olarak geçilmeli.
 */

/*
    @NonNull
    private final FloatingActionButton.OnVisibilityChangedListener supportHideListener =
            new FloatingActionButton.OnVisibilityChangedListener() {
                @Override
                public void onHidden(FloatingActionButton fab) {
                    super.onHidden(fab);
                    fab.setVisibility(View.INVISIBLE);
                }
            };
    */

/**
 * Behavior'un inflate edilebilmesi için gerekli.

 * @param context
 * *
 * @param attrs
 */
(context: Context, attrs: AttributeSet) : FloatingActionButton.Behavior() {

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: FloatingActionButton?, dependency: View?): Boolean {
        return dependency is FrameLayout
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, directTargetChild: View, target: View, nestedScrollAxes: Int): Boolean {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target,
                nestedScrollAxes)
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        if (dyConsumed > TRASHHOLD) {
            child.hide()
        } else {
            child.show()
        }
    }

    /**
     * Added for IllegalArgumentException("Rect should intersect with child's bounds.")
     */
    override fun getInsetDodgeRect(parent: CoordinatorLayout, child: FloatingActionButton, rect: Rect): Boolean {
        super.getInsetDodgeRect(parent, child, rect)
        return false
    }

    companion object {
        val TRASHHOLD = 0
    }
}
