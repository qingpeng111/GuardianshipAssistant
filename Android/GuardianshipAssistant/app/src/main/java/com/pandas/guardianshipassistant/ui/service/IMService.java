package com.pandas.guardianshipassistant.ui.service;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.pandas.guardianshipassistant.ui.activity.LoginActivity;
import com.pandas.guardianshipassistant.ui.dbhelper.ContactsOpenHelper;
import com.pandas.guardianshipassistant.ui.dbhelper.SmsOpenHelper;
import com.pandas.guardianshipassistant.ui.provider.ContactsProvider;
import com.pandas.guardianshipassistant.ui.provider.SmsProvider;
import com.pandas.guardianshipassistant.utils.PinyinUtils;
import com.pandas.guardianshipassistant.utils.ThreadUtils;
import com.pandas.guardianshipassistant.utils.ToastUtils;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IMService extends Service {
    public static XMPPConnection conn;
    public static String mCurAccout;//当前登录用户的jid

    private Roster mRoster;
    private MyRosterListener mMyRosterListener;


    private ChatManager mChatManager;
    private Chat mCurChat;
    private MyMessageListener mMyMessageListener;

    private Map<String, Chat> mChatMap = new HashMap<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    public class MyBinder extends Binder {
        /**
         * 返回service的实例
         */
        public IMService getService() {
            return IMService.this;
        }
    }

    @Override
    public void onCreate() {
        System.out.println("service oncreate");
        /*---------------同步花名册-------------------*/
        ThreadUtils.runInThread(new Runnable() {
            @Override
            public void run() {
                /*---------------同步花名册 begin -------------------*/
                System.out.println("------同步花名册---------");
                //需要连接对象
                //得到花名册对象
                mRoster = IMService.conn.getRoster();
                //得到所有联系人
                final Collection<RosterEntry> entries = mRoster.getEntries();

//                //打印所有的联系人
//                for (RosterEntry entry: entries) {
//                    System.out.println("联系人：" + entry.toString() +
//                                            "\n" + entry.getName() +
//                                            "\n" + entry.getUser() +
//                                            "\n" + entry.getGroups() +
//                                            "\n" + entry.getStatus() +
//                                            "\n" + entry.getType());
//                }

                //监听联系人的改变
                mMyRosterListener = new MyRosterListener();
                mRoster.addRosterListener(mMyRosterListener);


                //数据保存到数据库里面
                for (RosterEntry entry : entries) {
                    saveOrUpdateEntry(entry);
                }
                System.out.println("同步花名册- end-");

                /*---------------同步花名册- end------------------*/



                /*=============== 创建消息的管理者  注册监听 begin ===============*/

                // 1.获取消息的管理者
                if (mChatManager == null) {
                    mChatManager = IMService.conn.getChatManager();
                }
                mChatManager.addChatListener(mMyChatManagerListener);

            }
        });
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println(" service onstartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("service  destory");
        //移除rosterListener
        if (mMyRosterListener != null && mRoster != null) {
            mRoster.removeRosterListener(mMyRosterListener);
        }

        //移除messageListener
        if(mCurChat!=null&&mMyMessageListener!=null){
            mCurChat.removeMessageListener(mMyMessageListener);
        }
        super.onDestroy();
    }


    /**
     * 保存或者更新
     */
    private void saveOrUpdateEntry(RosterEntry entry) {

        ContentValues values = new ContentValues();

        String account = entry.getUser();
        account = account.substring(0, account.indexOf("@")) + "@" + LoginActivity.SERVICENAME;
        values.put(ContactsOpenHelper.ContactTable.ACCOUNT, account);

        String nickname = entry.getName();

        if (nickname == null || "".equals(nickname)) {
            nickname = account.substring(0, account.indexOf("@"));
        }

        values.put(ContactsOpenHelper.ContactTable.NICKNAME, nickname);

        values.put(ContactsOpenHelper.ContactTable.AVATOR, "0");

        values.put(ContactsOpenHelper.ContactTable.PINGYING, PinyinUtils.getPinyin(account));

        values.put(ContactsOpenHelper.ContactTable.CELLPHONE, "18328367975");

        values.put(ContactsOpenHelper.ContactTable.MOTTO, "一万年");

        //  应该先更新，如果不存在则插入（重要）
        int updateCount = getContentResolver().update(ContactsProvider.URI_CONTACT, values,
                ContactsOpenHelper.ContactTable.ACCOUNT + "=?", new String[]{account});
        if (updateCount <= 0) {//如果没有跟新任何记录，则插入数据
            getContentResolver().insert(ContactsProvider.URI_CONTACT, values);
        }

    }


    /**
     * 监听服务端的改变(roster)
     */
    class MyRosterListener implements RosterListener {

        //发现变了就需要改变本地数据库
        @Override
        public void entriesAdded(Collection<String> addresss) {//联系人增加
            System.out.println("------------联系人增加---------------");
            //增加到数据库里面
            for (String address : addresss) {
                RosterEntry entry = mRoster.getEntry(address);
                //要么更新要么插入
                saveOrUpdateEntry(entry);
            }

        }

        @Override
        public void entriesUpdated(Collection<String> addresss) {//联系人更新
            System.out.println("-----------联系人更新----------------");
            //增加到数据库里面
            for (String address : addresss) {
                RosterEntry entry = mRoster.getEntry(address);
                //要么更新要么插入
                saveOrUpdateEntry(entry);
            }

        }

        @Override
        public void entriesDeleted(Collection<String> addresss) {//联系人删除
            System.out.println("-----------联系人删除----------------");
            for (String account : addresss) {
                //删除  account：6@192.168.133.2      实际上存储的格式为：6@----SERVICENAME----，因此需要转换成这种格式
//                    account=account.replace(DOMAIN,"@----SERVICENAME----");
                int deleteCount = getContentResolver().delete(ContactsProvider.URI_CONTACT,
                        ContactsOpenHelper.ContactTable.ACCOUNT + "=?", new String[]{account});
//
                System.out.println("deleteCount:: " + deleteCount);
                //这是自己加的
//                setOrUpdateApdater();
                System.out.println("-----删除结束-----" + deleteCount + "  account:" + account);


            }
        }

        @Override
        public void presenceChanged(Presence presence) {//联系人状态改变
            System.out.println("------------联系人状态改变---------------");
        }
    }

    class MyMessageListener implements MessageListener {
        @Override
        public void processMessage(Chat chat, Message message) {
            String body = message.getBody();
            ToastUtils.showToastSafe(getApplicationContext(), body);
            System.out.println("body：\t" + message.getBody());
            System.out.println("Type：\t" + message.getType());
            System.out.println("From：\t" + message.getFrom());
            System.out.println("To：  \t" + message.getTo());

//            Fromtest1@192.168.133.2/青鹏的电脑_18328367975
//            Toadmin@192.168.133.2/Smack

            //收到消息，然后保存消息 . sessionAccount:participant
            String participant = chat.getParticipant();

            if(message.getBody()!=null&&message.getBody()!=""){
                System.out.println("不為空才保存："+message.getBody());
                saveMessage(participant, message);
            }

        }
    }

    MyChatManagerListener	mMyChatManagerListener	= new MyChatManagerListener();
    /**
     *类功能： 监听类
     *@创建者 青鹏
     * @QQ    260498491
     *@date 2020/11/13 20:20
     */
    class MyChatManagerListener implements ChatManagerListener {
        @Override
        public void chatCreated(Chat chat, boolean createdLocally) {
            System.out.println("--------------chatCreated--------------");

            // 判断chat是否存在map里面
            String participant = chat.getParticipant();// 和我聊天的那个人

            // 因为别人创建和我自己创建,参与者(和我聊天的人)对应的jid不同.所以需要统一处理
            participant = filterAccount(participant);

            if (!mChatMap.containsKey(participant)) {
                // 保存chat
                mChatMap.put(participant, chat);
                chat.addMessageListener(mMyMessageListener);
            }
            System.out.println("participant:" + participant);

            if (createdLocally) {// true
                System.out.println("-------------- 我创建了一个chat--------------");
                // participant:hm1@itheima.com admin@itheima.com hm1@itheima.com
            } else {// false
                System.out.println("-------------- 别人创建了一个chat--------------");
                // participant:hm1@itheima.com/Spark 2.6.3 admin@itheima.com hm1@itheima.com
            }
        }
    }

    /**
     * 函数功能：  保存消息，利用SmsReesolve-->SmsProvider-->sqlite
     * 函数实现逻辑：
     *
     * @param
     * @创建者 青鹏
     * @QQ 260498491
     * @date 2020/11/13 0:16
     */
    private void saveMessage(String sessionAccount, Message msg) {
//        ContentValues mValues = new ContentValues();
//        System.out.println("msg.getBody()"+msg.getBody());
//        System.out.println("SmsOpenHelper.SmsTable.MESSAGE,"+ SmsOpenHelper.SmsTable.MESSAGE);
//
//        mValues.put(SmsOpenHelper.SmsTable.FROM_ACCOUNT, msg.getFrom());
//        mValues.put(SmsOpenHelper.SmsTable.TO_ACCOUNT, msg.getTo());
//        mValues.put(SmsOpenHelper.SmsTable.MESSAGE, msg.getBody());
//        mValues.put(SmsOpenHelper.SmsTable.STATUS, "offline");
//        mValues.put(SmsOpenHelper.SmsTable.TYPE, msg.getType().name());
//        mValues.put(SmsOpenHelper.SmsTable.TIME, String.valueOf(System.currentTimeMillis()));
//        mValues.put(SmsOpenHelper.SmsTable.SESSION_ACCOUNT, sessionAccount);
//
//        getContentResolver().insert(SmsProvider.URI_SMS, mValues);

        ContentValues values = new ContentValues();
        // 我(from)--->小蜜(to) ===>小蜜
        // 小蜜(from)--->我(to)====>小蜜
        sessionAccount = filterAccount(sessionAccount);
        String from = msg.getFrom();
        from = filterAccount(from);
        String to = msg.getTo();
        to = filterAccount(to);


        values.put(SmsOpenHelper.SmsTable.FROM_ACCOUNT, from);
        values.put(SmsOpenHelper.SmsTable.TO_ACCOUNT, to);
        values.put(SmsOpenHelper.SmsTable.MESSAGE, msg.getBody());
        values.put(SmsOpenHelper.SmsTable.STATUS, "offline");
        values.put(SmsOpenHelper.SmsTable.TYPE, msg.getType().name());
        values.put(SmsOpenHelper.SmsTable.TIME, System.currentTimeMillis());
        values.put(SmsOpenHelper.SmsTable.SESSION_ACCOUNT, sessionAccount);
        getContentResolver().insert(SmsProvider.URI_SMS, values);

    }

    /**
     *函数功能：
     *函数实现逻辑：
     *@创建者 青鹏
     * @QQ    260498491
     *@date 2020/11/13 18:51
     *
     *@param
     */
    public void sendMessage(final Message msg){
        try {

            //2.创建聊天对象
            if(mMyMessageListener==null){
                mMyMessageListener = new MyMessageListener();
            }

            String toAccount = msg.getTo();
            if (mChatMap.containsKey(toAccount)) {
                mCurChat = mChatMap.get(toAccount);
            } else {
                mCurChat = mChatManager.createChat(toAccount, mMyMessageListener);
                mChatMap.put(toAccount, mCurChat);
            }
            //发送消息
            mCurChat.sendMessage(msg);
            //保存消息  sessionAccount：mClickAccount（msg.getTo()）
            saveMessage(msg.getTo(), msg);
        }catch(Exception e){
            e.printStackTrace();
        }



    }

    private String filterAccount(String accout) {
        return accout.substring(0, accout.indexOf("@")) + "@" + LoginActivity.SERVICENAME;
    }
}
