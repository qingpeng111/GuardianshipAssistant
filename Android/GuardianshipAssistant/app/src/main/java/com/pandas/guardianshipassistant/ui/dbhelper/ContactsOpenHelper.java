package com.pandas.guardianshipassistant.ui.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class ContactsOpenHelper extends SQLiteOpenHelper {
    public static final String T_CONTACT="t_contact";

    public class ContactTable implements BaseColumns{  // BaseColumns就是默认给我们添加一列 _id，这是主键
        /**
         * _id ：    主键
         * account:  账号
         * nickname: 昵称
         * avator:   头像
         * pingying: 账号拼音（用于排序）
         * cellphone:电话
         * motto:    座右铭
         *
         */

        public static final String  ACCOUNT="account";
        public static final String  NICKNAME="nickname";
        public static final String  AVATOR="avator";
        public static final String  PINGYING="pingying";

        public static final String  CELLPHONE="cellphone";
        public static final String  MOTTO="motto";
    }

    public ContactsOpenHelper(@Nullable Context context) {
        //创建数据库
        super(context, "contact.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建contacts表-->表结构
        String sql="CREATE TABLE " + T_CONTACT + "(_id integer PRIMARY KEY AUTOINCREMENT," +
                                                    ContactTable.ACCOUNT    +" TEXT, "+
                                                    ContactTable.NICKNAME   +" TEXT, "+
                                                    ContactTable.AVATOR     +" TEXT, "+
                                                    ContactTable.PINGYING   +" TETX, "+

                                                    ContactTable.CELLPHONE  +" TEXT, "+
                                                    ContactTable.MOTTO      +" TEXT);";
        //执行
        db.execSQL(sql);
        System.out.println("ContactsOpenHelper  执行创建表");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
