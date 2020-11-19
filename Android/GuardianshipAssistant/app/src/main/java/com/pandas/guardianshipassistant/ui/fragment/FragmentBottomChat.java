package com.pandas.guardianshipassistant.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pandas.guardianshipassistant.R;
import com.pandas.guardianshipassistant.ui.activity.bottom.ChatActivity;
import com.pandas.guardianshipassistant.ui.dbhelper.ContactsOpenHelper;
import com.pandas.guardianshipassistant.ui.dbhelper.SmsOpenHelper;
import com.pandas.guardianshipassistant.ui.provider.ContactsProvider;
import com.pandas.guardianshipassistant.ui.provider.SmsProvider;
import com.pandas.guardianshipassistant.ui.service.IMService;
import com.pandas.guardianshipassistant.utils.ThreadUtils;


public class FragmentBottomChat extends Fragment {
    private ListView		mListView;
    private CursorAdapter	mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_chat, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        init();
        initData();
        initListener();
        super.onActivityCreated(savedInstanceState);
    }

    private void init() {
        registerContentObserver();
    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.listView_chat);
    }

    private void initData() {
        setOrNotifyAdapter();
    }

    private void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c = mAdapter.getCursor();
                c.moveToPosition(position);
                // 拿到jid(账号)-->发送消息的时候需要
                String account = c.getString(c.getColumnIndex(SmsOpenHelper.SmsTable.SESSION_ACCOUNT));
                // 拿到nickName-->显示效果
                String nickname = getNickNameByAccount(account);

                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("sourceId","0");//1主要用于标记 contacts界面,chat界面为0
                intent.putExtra(ChatActivity.CLICKACCOUNT, account);
                intent.putExtra(ChatActivity.CLICKNICKNAME, nickname);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        unRegisterContentObserver();
        super.onDestroy();
    }

    /**
     * 设置或者更新adapter
     */
    private void setOrNotifyAdapter() {
        // 判断adapter是否存在
        if (mAdapter != null) {
            // 刷新adapter就行了
            mAdapter.getCursor().requery();
            return;
        }
        ThreadUtils.runInThread(new Runnable() {
            @Override
            public void run() {
                // 对应查询记录
                final Cursor c =
                        getActivity().getContentResolver().query(SmsProvider.URI_SESSION, null, null,
                                new String[] { IMService.mCurAccout, IMService.mCurAccout }, null);

                // 假如没有数据的时候
                if (c.getCount() <= 0) {
                    return;
                }
                // 设置adapter,然后显示数据
                ThreadUtils.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         BaseAdapter    -->getView
                         |-CursorAdapter
                         */
                        mAdapter = new CursorAdapter(getActivity(), c) {
                            // 如果convertView==null,返回一个具体的根视图
                            @Override
                            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                                View view = View.inflate(context, R.layout.item_chat, null);
                                return view;
                            }

                            // 设置数据显示数据
                            @Override
                            public void bindView(View view, Context context, Cursor cursor) {
                                ImageView ivHead = (ImageView) view.findViewById(R.id.head);
                                TextView tvBody = (TextView) view.findViewById(R.id.message);
                                TextView tvNickName = (TextView) view.findViewById(R.id.nickname);

                                String message = c.getString(c.getColumnIndex(SmsOpenHelper.SmsTable.MESSAGE));
                                String acccount = c.getString(c.getColumnIndex(SmsOpenHelper.SmsTable.SESSION_ACCOUNT));

                                String nickName = getNickNameByAccount(acccount);

                                // acccount 但是在聊天记录表(sms)里面没有保存别名信息,只有(Contact表里面有)
                                tvBody.setText(message);
                                tvNickName.setText(nickName);
                            }
                        };

                        mListView.setAdapter(mAdapter);
                    }
                });
            }
        });
    }

    public String getNickNameByAccount(String account) {
        String nickName = "";
        Cursor c =
                getActivity().getContentResolver().query(ContactsProvider.URI_CONTACT, null,
                        ContactsOpenHelper.ContactTable.ACCOUNT + "=?", new String[] { account }, null);
        if (c.getCount() > 0) {// 有数据
            c.moveToFirst();
            nickName = c.getString(c.getColumnIndex(ContactsOpenHelper.ContactTable.NICKNAME));
        }
        return nickName;
    }

    /*=============== 监听数据库记录的改变 ===============*/

    MyContentObserver	mMyContentObserver	= new MyContentObserver(new Handler());

    /**
     * 注册监听
     */
    public void registerContentObserver() {
        // content://xxxx/contact
        // content://xxxx/contact/i
        getActivity().getContentResolver().registerContentObserver(SmsProvider.URI_SMS, true, mMyContentObserver);
    }

    public void unRegisterContentObserver() {
        getActivity().getContentResolver().unregisterContentObserver(mMyContentObserver);
    }

    /**
     * 反注册监听
     */

    class MyContentObserver extends ContentObserver {

        public MyContentObserver(Handler handler) {
            super(handler);
        }

        /**
         * 如果数据库数据改变会在这个方法收到通知
         */
        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            // 更新adapter或者刷新adapter
            setOrNotifyAdapter();
        }
    }



}
