package com.pandas.guardianshipassistant.ui.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 *类功能：
 *@创建者 青鹏
 * @QQ    260498491
 *@date 2020/11/12 11:20
 */
public class SmsOpenHelper extends SQLiteOpenHelper {
    public  static final String T_SMS="t_sms";

    public  class SmsTable implements BaseColumns{//自动增加主键列 _id
        /**
         * from_account      //发送者
         * to_account        //接受者
         * message（body）          //消息内容
         * status           //发送状态
         * type             //类型
         * time             //时间
         * session_account  //会话id--》最近拟合那些人聊天了
         */
        public  static final String FROM_ACCOUNT="from_account";
        public  static final String TO_ACCOUNT="to_account";
        public  static final String MESSAGE="message";
        public  static final String STATUS="status";
        public  static final String TYPE="type";
        public  static final String TIME="time";
        public  static final String SESSION_ACCOUNT="session_account";

    }

    public SmsOpenHelper(Context context) {
        super(context, "sms.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //表结构
        String sql = "CREATE TABLE " + T_SMS + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                SmsTable.FROM_ACCOUNT       +" TEXT, "+
                                                SmsTable.TO_ACCOUNT         +" TEXT, "+
                                                SmsTable.MESSAGE            +" TEXT, "+
                                                SmsTable.STATUS             +" TETX, "+
                                                SmsTable.TYPE               +" TEXT, "+
                                                SmsTable.TIME               +" TEXT, "+
                                                SmsTable.SESSION_ACCOUNT    +" TEXT);";


//        System.out.println("db----begin");

        db.execSQL(sql);
//        System.out.println("db-------end");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
