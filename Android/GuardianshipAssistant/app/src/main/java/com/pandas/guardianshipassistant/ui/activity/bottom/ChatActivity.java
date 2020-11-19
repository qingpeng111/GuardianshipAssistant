package com.pandas.guardianshipassistant.ui.activity.bottom;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pandas.guardianshipassistant.R;
import com.pandas.guardianshipassistant.ui.activity.MainActivity;
import com.pandas.guardianshipassistant.ui.dbhelper.SmsOpenHelper;
import com.pandas.guardianshipassistant.ui.provider.SmsProvider;
import com.pandas.guardianshipassistant.ui.service.IMService;
import com.pandas.guardianshipassistant.utils.ThreadUtils;
import com.pandas.guardianshipassistant.utils.ToastUtils;

import org.jivesoftware.smack.packet.Message;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {

    public static String CLICKACCOUNT = "clickaccount";
    public static String CLICKNICKNAME = "clicknickname";
    @BindView(R.id.chat_page_title)
    TextView chatPageTitle;
    @BindView(R.id.chat_page_listview)
    ListView chatPageListview;
    @BindView(R.id.chat_page_message)
    EditText chatPageMessage;
    @BindView(R.id.chat_page_btn_send)
    Button chatPageBtnSend;
    @BindView(R.id.chat_page_return)//返回按钮
    Button chatPageReturn;


    private String mClickNickName;
    private String mClickAccount;
    private CursorAdapter mAdapter;
    private ContentValues mValues;
    private IMService mImService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        init();
        initView();
        initData();
        initListener();
        //
    }

    @Override
    protected void onDestroy() {
        unRegisterContentObserver();
        // 解绑服务
        if (mMyServiceConnection != null) {
            unbindService(mMyServiceConnection);
        }

        super.onDestroy();
    }


    private void init() {
        registerContentObserver();
        //绑定服务
        Intent service = new Intent(ChatActivity.this, IMService.class);
        bindService(service, mMyServiceConnection, BIND_AUTO_CREATE);

        mClickAccount = getIntent().getStringExtra(ChatActivity.CLICKACCOUNT);
        mClickNickName = getIntent().getStringExtra(ChatActivity.CLICKNICKNAME);
    }

    private void initView() {
        //設置titile
        chatPageTitle.setText("与 " + mClickNickName + " 聊天中...");
   }

    private void initData() {
        setAdapterOrNotify();
    }

    /**
     * 函数功能：  重置Adapter或者更新Adapter
     * 函数实现逻辑：
     *
     * @param
     * @创建者 青鹏
     * @QQ 260498491
     * @date 2020/11/13 9:27
     */
    private void setAdapterOrNotify() {
        // 1.首先判断是否存在adapter
        if (mAdapter != null) {//已近存在，则刷新
            // 刷新
            Cursor cursor = mAdapter.getCursor();
            cursor.requery();
            //显示最后一行
            chatPageListview.setSelection(cursor.getCount() - 1);
            return;
        }

        //从数据库里面查询出来，需要放在线程里面
        ThreadUtils.runInThread(new Runnable() {
            @Override
            public void run() {
//               final Cursor c = getContentResolver().query(SmsProvider.URI_SMS, null, null, null, null);
                final Cursor c = getContentResolver().query(SmsProvider.URI_SMS,//
                        null,//
                        "(from_account = ? and to_account=?)or(from_account = ? and to_account= ? )",// where条件
                        new String[]{IMService.mCurAccout, mClickAccount, mClickAccount, IMService.mCurAccout},// where条件的参数
                        SmsOpenHelper.SmsTable.TIME + " ASC"// 根据时间升序排序

                );//按照时间排序
                System.out.println("-------------------------------------------------");
                System.out.println(" IMService.mCurAccout, mClickAccount :" + IMService.mCurAccout + "  --> " + mClickAccount);
                // 如果没有数据直接返回
                System.out.println("-------------------------------------------------");
                while (c.moveToNext()) {
                    System.out.println(c.getColumnName(0) + "\t" + c.getColumnName(1) + "\t" + c.getColumnName(2) + "\t" + c.getColumnName(3) + "\t" + c.getColumnName(4));
                    System.out.println("-------------------------------------------------");
                }
                if (c.getCount() < 1) {
                    return;
                }

                ThreadUtils.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行顺序 CursorAdapter:getView()->newView()->bindView()
                        mAdapter = new CursorAdapter(ChatActivity.this, c) {
                            public static final int RECEIVE = 1;
                            public static final int SEND = 0;
                            //如果convertView ==null的时候会调用，--》返回根布局
                           /* @Override
                            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                                TextView tv = new TextView(context);
                                //



                                return tv;

                            }

                            @Override
                            public void bindView(View view, Context context, Cursor cursor) {
                                TextView tv=(TextView) view;
                                //具体设置数据
                                String message = cursor.getString(cursor.getColumnIndex(SmsOpenHelper.SmsTable.MESSAGE));
                                tv.setText(message);

                                System.out.println("------------message:"+message);
                            }*/

                            @Override
                            public int getItemViewType(int position) {
                                //一种是接受   即当前账号不等于消息的创建者
                                c.moveToPosition(position);
                                // 取出消息的创建者
                                String fromAccount = c.getString(c.getColumnIndex(SmsOpenHelper.SmsTable.FROM_ACCOUNT));
                                if (!IMService.mCurAccout.equals(fromAccount)) {// 接收
                                    return RECEIVE;
                                } else {// 发送
                                    return SEND;

                                }
                                //一种是發送   即当前账号等于消息的创建者
//                                return super.getItemViewType(position);//只能是 0、1
                            }

                            @Override
                            public int getViewTypeCount() {
                                return super.getViewTypeCount() + 1;//2
                            }

                            @Override
                            public View getView(int position, View convertView, ViewGroup parent) {
                                ViewHolder holder;
                                //接受
                                if (getItemViewType(position) == RECEIVE) {
                                    if (convertView == null) {
                                        convertView = View.inflate(ChatActivity.this, R.layout.item_chat_receive, null);
                                        holder = new ViewHolder();
                                        convertView.setTag(holder);

                                        // holder赋值
                                        holder.time = (TextView) convertView.findViewById(R.id.time);
                                        holder.message = (TextView) convertView.findViewById(R.id.content);
                                        holder.head = (ImageView) convertView.findViewById(R.id.head);
                                    } else {
                                        holder = (ViewHolder) convertView.getTag();
                                    }

                                    // 得到数据,展示数据
                                } else {// 发送
                                    if (convertView == null) {
                                        convertView = View.inflate(ChatActivity.this, R.layout.item_chat_send, null);
                                        holder = new ViewHolder();
                                        convertView.setTag(holder);

                                        // holder赋值
                                        holder.time = (TextView) convertView.findViewById(R.id.time);
                                        holder.message = (TextView) convertView.findViewById(R.id.content);
                                        holder.head = (ImageView) convertView.findViewById(R.id.head);
                                    } else {
                                        holder = (ViewHolder) convertView.getTag();
                                    }
                                    // 得到数据,展示数据

                                }
                                // 得到数据,展示数据
                                c.moveToPosition(position);

                                String time = c.getString(c.getColumnIndex(SmsOpenHelper.SmsTable.TIME));
                                String message = c.getString(c.getColumnIndex(SmsOpenHelper.SmsTable.MESSAGE));

                                String formatTime =
                                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long
                                                .parseLong(time)));

                                holder.time.setText(formatTime);
                                holder.message.setText(message);

                                return super.getView(position, convertView, parent);
                                //                                return super.getView(position, convertView, parent);

                            }


                            @Override
                            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                                return null;
                            }

                            @Override
                            public void bindView(View view, Context context, Cursor cursor) {

                            }

                            class ViewHolder {
                                TextView message;
                                TextView time;
                                ImageView head;

                            }

                        };
                        chatPageListview.setAdapter(mAdapter);
                        // 滚动到最后一行
                        chatPageListview.setSelection(mAdapter.getCount() - 1);

                    }
                });
            }
        });
    }

    private void initListener() {

    }

    @OnClick(R.id.chat_page_btn_send)
    public void send(View v) {
        ThreadUtils.runInThread(new Runnable() {
            @Override
            public void run() {
                final String message = chatPageMessage.getText().toString();

                ToastUtils.showToastSafe(getApplicationContext(), message);
//                    //1.获取消息管理者
//                    if(mChatManager==null){
//                        mChatManager = IMService.conn.getChatManager();
//                    }


                //3.初始化消息
                Message msg = new Message();
                msg.setFrom(IMService.mCurAccout);   //当前登录用户
                msg.setTo(mClickAccount);
                msg.setBody(message);//输入框里面的内容
                msg.setType(Message.Type.chat);//聊天类型chat
                msg.setProperty("key", "value");//额外信息

                //调用IMservice  sendMessage
                mImService.sendMessage(msg);


                // 1、发送消息需要保存，
                // 2、收到消息需要保存


                //清空消息输入框内的内容
                ThreadUtils.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        chatPageMessage.setText("");
                    }
                });
            }
        });
    }


    /*=============== 使用contentObserver时刻监听记录的改变 ===============*/
    MyContentObserver mMyContentObserver = new MyContentObserver(new Handler());

    /**
     * 注册监听
     */
    public void registerContentObserver() {
        getContentResolver().registerContentObserver(SmsProvider.URI_SMS, true, mMyContentObserver);
    }

    /**
     * 反注册监听
     */
    public void unRegisterContentObserver() {
        getContentResolver().unregisterContentObserver(mMyContentObserver);
    }

    //点击返回按钮，返回上一个来的界面，主要是FragmentBottomChat 和FragmentBottomContacts
    @OnClick(R.id.chat_page_return)
    public void onClick() {
        //sourceId 和 returnFragmentId 一样
        String mark = getIntent().getStringExtra("sourceId");
        if (mark.equals("0")) {          // chat界面为 0  跳转到Mainactivity  chatfragment

            ToastUtils.showToastSafe(getApplicationContext(), mark + "跳转到MainActivity");
            Intent intent = new Intent(ChatActivity.this, MainActivity.class);
            intent.putExtra("returnFragmentId", "0");
            startActivity(intent);
        } else if (mark.equals("1")) {  //  contacts界面为  1  跳转到Mainactivity  Contactsfragment

            ToastUtils.showToastSafe(getApplicationContext(), mark + "跳转到MainActivity");
            Intent intent = new Intent(ChatActivity.this, MainActivity.class);
            intent.putExtra("returnFragmentId", "1");
            startActivity(intent);
        }
    }

    class MyContentObserver extends ContentObserver {

        public MyContentObserver(Handler handler) {
            super(handler);
        }

        /**
         * 接收到数据记录的改变
         */
        @Override
        public void onChange(boolean selfChange, Uri uri) {
            // 设置adapter或者notifyadapter
            setAdapterOrNotify();
            super.onChange(selfChange, uri);
        }
    }


    /*=============== 定义ServiceConnection调用服务里面的方法 ===============*/

    MyServiceConnection mMyServiceConnection = new MyServiceConnection();

    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("--------------onServiceConnected--------------");
            IMService.MyBinder binder = (IMService.MyBinder) service;
            mImService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("--------------onServiceDisconnected--------------");

        }
    }
}
