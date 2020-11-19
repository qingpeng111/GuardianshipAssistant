package com.pandas.guardianshipassistant.ui.provider;

import android.content.ContentValues;
import android.database.Cursor;

import com.pandas.guardianshipassistant.config.ConstantConfig;
import com.pandas.guardianshipassistant.ui.dbhelper.ContactsOpenHelper;

import org.junit.Test;

import static androidx.test.InstrumentationRegistry.getContext;

public class ContactsProviderTest {

    @Test
    public void onCreate() {
        System.out.println("test");

    }

    @Test
    public void getType() {
//        ContactsProvider.on
    }

    @Test
    public void insert() {
        ContentValues values=new ContentValues();
        values.put(ContactsOpenHelper.ContactTable.ACCOUNT,"260498491@qq.com");
        values.put(ContactsOpenHelper.ContactTable.NICKNAME,"青鹏111");
        values.put(ContactsOpenHelper.ContactTable.AVATOR,"0");
        values.put(ContactsOpenHelper.ContactTable.PINGYING,"qingpeng111");
        values.put(ContactsOpenHelper.ContactTable.CELLPHONE,"18328367975");
        values.put(ContactsOpenHelper.ContactTable.MOTTO,"一万年");

        getContext().getContentResolver().insert(ContactsProvider.URI_CONTACT,values);
//        System.out.println("插入成功------------");
    }

    @Test
    public void delete() {
        int deleteCount = getContext()
                .getContentResolver()
                .delete(ContactsProvider.URI_CONTACT, ContactsOpenHelper.ContactTable.ACCOUNT + "=?", new String[]{"6@"+ ConstantConfig.DOMAIN});
        System.out.println("删除结束 deleteCount 测试："+deleteCount);
    }

    @Test
    public void update() {
        ContentValues values=new ContentValues();
        values.put(ContactsOpenHelper.ContactTable.ACCOUNT,"260498491@qq.com");
        values.put(ContactsOpenHelper.ContactTable.NICKNAME,"青鹏1234568");
        values.put(ContactsOpenHelper.ContactTable.AVATOR,"0");
        values.put(ContactsOpenHelper.ContactTable.PINGYING,"qingpeng1234568");
        values.put(ContactsOpenHelper.ContactTable.CELLPHONE,"183283679751111");
        values.put(ContactsOpenHelper.ContactTable.MOTTO,"一万年");

        getContext().getContentResolver().update(ContactsProvider.URI_CONTACT, values,ContactsOpenHelper.ContactTable.ACCOUNT+ "=?",new String[]{"260498491@qq.com"});
//        System.out.println("更新结束");
    }

    @Test
    public void query() {
        Cursor query = getContext().getContentResolver().query(ContactsProvider.URI_CONTACT, null, null, null, null);
        int columnCount = query.getColumnCount();//一共多少列

        System.out.println("数据：");
        while(query.moveToNext()){
            //循环打印列
//            for(int i=0;i<columnCount;i++){
//                System.out.print(query.getString(i)+"\t");
//            }
            System.out.println("-------------------------------------"+query.getString(1));
        }
        String[] names = query.getColumnNames();
        System.out.println(query.getColumnNames());
        for (String name:names ) {
            System.out.println(name);
        }
    }

    @Test
    public  void replaceString(){
        String account="6@192.168.133.2";//      实际上存储的格式为：6@----SERVICENAME----，因此需要转换成这种格式
        account=account.replace("@192.168.133.2","@----SERVICENAME----");
        System.out.println("account:"+account);
    }
}