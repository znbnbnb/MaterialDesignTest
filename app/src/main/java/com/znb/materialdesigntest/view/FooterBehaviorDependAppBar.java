package com.znb.materialdesigntest.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

public class FooterBehaviorDependAppBar extends CoordinatorLayout.Behavior<View> {
 
 
    public FooterBehaviorDependAppBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
         return dependency instanceof AppBarLayout;
    }
 
 
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = Math.abs(dependency.getTranslationY());
        child.setTranslationY(translationY);
        return true;
    }
}