package com.pandas.guardianshipassistant.ui.widget;

import android.graphics.drawable.Drawable;

/**
 *类功能  ：
 *@创建者 ： 青鹏
 * @QQ   ： 260498491
 *@date  ： 2020/11/16 22:03
 */
public interface ITitleBarStyle {

    /**
     * 文字和图标的间距
     */
    int getDrawablePadding();

    /**
     * 子 View 内间距
     */
    int getChildPadding();

    /**
     * 标题栏高度（默认为ActionBar的高度）
     */
    int getTitleBarHeight();

    /**
     * 标题重心
     */
    int getTitleGravity();

    /**
     * 标题栏背景
     */
    Drawable getBackground();

    /**
     * 默认返回按钮图标
     */
    Drawable getBackIcon();

    /**
     * 左边文本颜色
     */
    int getLeftColor();

    /**
     * 标题文本颜色
     */
    int getTitleColor();

    /**
     * 右标题文本颜色
     */
    int getRightColor();

    /**
     * 左标题文本大小
     */
    float getLeftSize();

    /**
     * 标题文本大小
     */
    float getTitleSize();

    /**
     * 右标题文本大小
     */
    float getRightSize();

    /**
     * 分割线是否可见
     */
    boolean isLineVisible();

    /**
     * 分割线背景
     */
    Drawable getLineDrawable();

    /**
     * 分割线的大小
     */
    int getLineSize();

    /**
     * 左标题背景
     */
    Drawable getLeftBackground();

    /**
     * 右标题背景
     */
    Drawable getRightBackground();
}