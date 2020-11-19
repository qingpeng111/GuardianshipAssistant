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

import com.pandas.guardianshipassistant.ui.dbhelper.SmsOpenHelper;

/**
 *类功能： 用于记录聊天信息的
 *@创建者 青鹏
 * @QQ    260498491
 *@date 2020/11/12 11:09
 */
public class SmsProvider extends ContentProvider {
    private static  final String AUTHORITIES=SmsProvider.class.getCanonicalName();

    static UriMatcher mUriMatcher;
    public static Uri			URI_SESSION	= Uri.parse("content://" + AUTHORITIES + "/session");
    public static Uri URI_SMS=Uri.parse("content://" + AUTHORITIES + "/sms");
//    public  static Uri URI_CONTACT=Uri.parse("content://" + AUTHORITIES + "/contact");
    //content://com.pandas.guardianshipassistant.ui.provider.SmsProvider/sms

    public static final int SMS = 1;
    public static final int		SESSION		= 2;

    static {
        mUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITIES,"/sms",SMS);
        mUriMatcher.addURI(AUTHORITIES, "/session", SESSION);
    }

    private SmsOpenHelper mHelper;

    @Override
    public boolean onCreate() {
        //创建数据库
        mHelper = new SmsOpenHelper(getContext());
        if(mHelper!=null){
            System.out.println("----true----oncreate");
            return true;
        }
        return false;
    }


    /*--------------------CRUD --------------------------*/
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        switch (mUriMatcher.match(uri)){
            case SMS:
                //插入之后对应id
                long id = mHelper.getWritableDatabase().insert(SmsOpenHelper.T_SMS, "", values);
                if(id>0){
                    System.out.println("---------------SmsProvider----InsertSuccess-------------"+id);
                    uri = ContentUris.withAppendedId(uri,id);
                    //发送数据改变的信号
                    getContext().getContentResolver().notifyChange(SmsProvider.URI_SMS, null);

                }
                break;

            default:
                break;
        }

        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
          int mDeleteCount =0;
        switch (mUriMatcher.match(uri)){
            case SMS:
                //删除之后影响的条数
                mDeleteCount = mHelper.getWritableDatabase().delete(SmsOpenHelper.T_SMS, selection, selectionArgs);
                if(mDeleteCount >0){
                    System.out.println("---------------SmsProvider----DeleteSuccess-------------"+mDeleteCount);
                    getContext().getContentResolver().notifyChange(SmsProvider.URI_SMS,null);
                }
                break;

            default:
                break;
        }

        return mDeleteCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int updateCount=0;
        switch (mUriMatcher.match(uri)){
            case SMS:
                updateCount= mHelper.getWritableDatabase().update(SmsOpenHelper.T_SMS, values, selection, selectionArgs);
                if(updateCount>0){
                    System.out.println("---------------SmsProvider----UpdateSuccess-------------"+updateCount);
                    getContext().getContentResolver().notifyChange(SmsProvider.URI_SMS, null);

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
        Cursor cursor=null;
        switch (mUriMatcher.match(uri)){
            case SMS:
                 cursor = mHelper.getWritableDatabase().query(SmsOpenHelper.T_SMS,projection,selection,selectionArgs,null,null,sortOrder );

                 int columnCount = cursor.getColumnCount();
                 while(cursor.moveToNext()){
                    System.out.println("---------------SmsProvider----QuerySuccess-------------");
                    for (int i = 0; i < columnCount; i++) {
                        System.out.print(cursor.getString(i) + "  ");
                    }

                }
//                getContext().getContentResolver().notifyChange(SmsProvider.URI_SMS,null);
                break;

            case SESSION:
                SQLiteDatabase db = mHelper.getReadableDatabase();
                cursor = db.rawQuery("SELECT * FROM " //
                        + "(SELECT * FROM t_sms WHERE from_account = ? or to_account = ? ORDER BY time ASC)" //
                        + " GROUP BY session_account", selectionArgs);//
            default:
                break;
        }

        return cursor;
    }
}
