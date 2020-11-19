package com.pandas.guardianshipassistant.ui.provider;

import android.content.ContentValues;
import android.database.Cursor;

import com.pandas.guardianshipassistant.config.ConstantConfig;
import com.pandas.guardianshipassistant.ui.dbhelper.SmsOpenHelper;

import org.junit.Test;

import static androidx.test.InstrumentationRegistry.getContext;

public class SmsProviderTest {

    @Test
    public void onCreate() {

    }

    @Test
    public void getType() {

    }

    /*-------------------------测试CRUD---------------------------------*/
//    public  static final String FROM_ACCOUNT="from_account";
//    public  static final String TO_ACCOUNT="to_account";
//    public  static final String MESSAGE="message";
//    public  static final String STATUS="status";
//    public  static final String TYPE="type";
//    public  static final String TIME="time";
//    public  static final String SESSION_ACCOUNT="session_account";

    @Test
    public void insert() {


        ContentValues values = new ContentValues();
        values.put(SmsOpenHelper.SmsTable.FROM_ACCOUNT,"331@"+ ConstantConfig.DOMAIN);
        values.put(SmsOpenHelper.SmsTable.TO_ACCOUNT,"111@"+ ConstantConfig.DOMAIN);
        values.put(SmsOpenHelper.SmsTable.MESSAGE,"约会不");
        values.put(SmsOpenHelper.SmsTable.STATUS,"offline");
        values.put(SmsOpenHelper.SmsTable.TYPE,"chat");
        values.put(SmsOpenHelper.SmsTable.TIME,String.valueOf(System.currentTimeMillis()));
        values.put(SmsOpenHelper.SmsTable.SESSION_ACCOUNT,"111@"+ ConstantConfig.DOMAIN);
        getContext().getContentResolver().insert(SmsProvider.URI_SMS,values);

    }

    @Test
    public void delete() {
        getContext().getContentResolver().delete(SmsProvider.URI_SMS, SmsOpenHelper.SmsTable.FROM_ACCOUNT + "=?",
                new String[] { "33@192.168.133.2" });
    }

    @Test
    public void update() {
        ContentValues values = new ContentValues();
        values.put(SmsOpenHelper.SmsTable.MESSAGE, "今晚约吗?我好久没有看到你了.");
        values.put(SmsOpenHelper.SmsTable.TIME, System.currentTimeMillis());
        values.put(SmsOpenHelper.SmsTable.SESSION_ACCOUNT, "11@192.168.133.2");
        getContext().getContentResolver().update(SmsProvider.URI_SMS, values,
                SmsOpenHelper.SmsTable.FROM_ACCOUNT + "=?", new String[] { "33@192.168.133.2" });
    }

    @Test
    public void query() {
//        Cursor c = getContext().getContentResolver().query(SmsProvider.URI_SMS, null, null, null, null);

        Cursor c = getContext().getContentResolver().query(SmsProvider.URI_SMS,//
                null,//
                "(from_account = ? and to_account=?)or(from_account = ? and to_account= ? )",// where条件
                new String[] { "admin@192.168.133.2", "test1@192.168.133.2", "test1@192.168.133.2", "admin@192.168.133.2"},// where条件的参数
                SmsOpenHelper.SmsTable.TIME + " ASC");// 根据时间升序排序
        // 得到所有的列
//        int columnCount = c.getColumnCount();
//        int x=0;
//        while (c.moveToNext()) {
//            System.out.println("------------ -------------"+x);
//            for (int i = 0; i < columnCount; i++) {
//                System.out.print(c.getString(i) + "  ");
//            }
//            System.out.println("------------ -------------");
//            System.out.println("------------query测试-------------");
//        }
//        x++;
    }

}