package com.pandas.guardianshipassistant.ui.bean;

import android.graphics.drawable.Drawable;

/**
 *类功能  ：  app 实体类,图片和名称
 *@创建者 ： 青鹏
 * @QQ   ： 260498491
 *@date  ： 2020/11/18 19:01
 */
public class AppInfo {

    private Drawable image;
    private String name;

    public AppInfo() {

    }

    public AppInfo(Drawable image, String name) {
        this.image = image;
        this.name = name;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
