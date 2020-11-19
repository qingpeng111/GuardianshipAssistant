package com.pandas.guardianshipassistant.ui.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pandas.guardianshipassistant.ui.dbhelper.ContactsOpenHelper;

public class ContactsProvider extends ContentProvider {

    //的到一个类的完全路径,com.pandas.guardianshipassistant.ui.provider.ContactsProvider
    public static final String authorities = ContactsProvider.class.getCanonicalName();
    //地址匹配对象
    static UriMatcher mUriMatcher;
    //对应联系人表的一个uri常量
    public static Uri URI_CONTACT = Uri.parse("content://" + authorities + "/contact");
    public static final int CONTACT = 1;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //添加一个匹配规则
        mUriMatcher.addURI(authorities, "/contact", CONTACT);
        //content://com.pandas.guardianshipassistant.ui.provider.ContactsProvider/contact-->CONTACT

    }

    private ContactsOpenHelper mHelper;


    @Override
    public boolean onCreate() {
        mHelper = new ContactsOpenHelper(getContext());
        if (mHelper != null) {
            return true;
        }

        return false;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
    //--------------------curd 增删改查-------------------------//

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //数据存放到sqlite里面--> 创建db文件，建表-->sqliteOpenHelper
        int code = mUriMatcher.match(uri);
        switch (code) {
            case CONTACT:
                SQLiteDatabase db = mHelper.getWritableDatabase();
                long id = db.insert(ContactsOpenHelper.T_CONTACT, "", values);
                System.out.println("id:" + id);
                if (id != -1) {
                    System.out.println("---------ContactsProvider-----InsertSuccess-------");
                    //拼接最新的uri,如：content://com.pandas.guardianshipassistant.ui.provider.ContactsProvider/contact/id
                    uri = ContentUris.withAppendedId(uri, id);

                    //通知ContentObserver数据改变了
                    getContext().getContentResolver().notifyChange(ContactsProvider.URI_CONTACT, null);//为空所有的可以搜到，不为空指定某一个收到
                }
                break;

            default:
                break;

        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int code = mUriMatcher.match(uri);
        int deleteCount = 0;
        switch (code) {
            case CONTACT:
                SQLiteDatabase db = mHelper.getWritableDatabase();
                //影响的行数
                deleteCount = db.delete(ContactsOpenHelper.T_CONTACT, selection, selectionArgs);
                if (deleteCount > 0) {
                    System.out.println("---------ContactsProvider-----DeleteSuccess-------");
                    System.out.println("删除影响行数：" + deleteCount);

                    //通知ContentObserver数据改变了
                    getContext().getContentResolver().notifyChange(ContactsProvider.URI_CONTACT, null);//为空所有的可以搜到，不为空指定某一个收到
                }
                break;


            default:
                break;

        }
        return deleteCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int code = mUriMatcher.match(uri);
        int updateCount = 0;
        switch (code) {
            case CONTACT:
                SQLiteDatabase db = mHelper.getWritableDatabase();
                //更新的行数
                updateCount = db.update(ContactsOpenHelper.T_CONTACT, values, selection, selectionArgs);
                if (updateCount > 0) {
                    System.out.println("---------ContactsProvider-----UpdateSuccess-------");
                    System.out.println("更新影响行数：" + updateCount);
                    //通知ContentObserver数据改变了
                    getContext().getContentResolver().notifyChange(ContactsProvider.URI_CONTACT, null);//为空所有的可以搜到，不为空指定某一个收到
                }

                break;

            default:
                break;

        }

        return updateCount;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor query = null;
        int code = mUriMatcher.match(uri);
        switch (code) {
            case CONTACT:
                SQLiteDatabase db = mHelper.getWritableDatabase();
                //更新的行数

                query = db.query(ContactsOpenHelper.T_CONTACT, projection, selection, selectionArgs, null, null, sortOrder);
                System.out.println("---------ContactsProvider-----QuerySuccess-------");
                break;

            default:
                break;

        }

        return query;
    }
}
