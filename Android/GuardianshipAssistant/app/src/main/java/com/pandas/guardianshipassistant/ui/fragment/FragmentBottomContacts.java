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
import com.pandas.guardianshipassistant.ui.provider.ContactsProvider;
import com.pandas.guardianshipassistant.utils.ThreadUtils;

import butterknife.BindView;

public class FragmentBottomContacts extends Fragment {

    @BindView(R.id.listView_contacts)
    ListView listViewContacts;

    private CursorAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        init();
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bottom_contacts, container, false);
        initView(view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData();
        initListener();
        super.onActivityCreated(savedInstanceState);
    }

    private void initListener() {
        listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("position:"+position);
                Cursor cursor = mAdapter.getCursor();
                cursor.moveToPosition(position);
                //拿到jid（账号）--》发送消息的时候需要
                String account = cursor.getString(cursor.getColumnIndex(ContactsOpenHelper.ContactTable.ACCOUNT));
                //拿到nickname--》  聊天界面上方显示用
                String nickname = cursor.getString(cursor.getColumnIndex(ContactsOpenHelper.ContactTable.NICKNAME));

                System.out.println("-----------2--------------");

                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("sourceId","1");//1主要用于标记 contacts界面,chat界面为0
                intent.putExtra(ChatActivity.CLICKACCOUNT,account);
                intent.putExtra(ChatActivity.CLICKNICKNAME,nickname);
                System.out.println("---------3-----------");
                startActivity(intent);
                System.out.println("-----------4---------");

            }
        });
    }

    private void init() {
        registerContentProvider();
    }

    /**
     * 初始化view
     * @param view
     */
    private void initView(View view) {
        //这句话一定需要的
        listViewContacts=(ListView) view.findViewById(R.id.listView_contacts);
    }



    /**
     * 初始化
     */
    private void initData() {

                /**
                 * 设置或者更新adapter
                 */
                setOrUpdateApdater();

    }
    /**
     * 设置或者更新adapter
     */
    private void setOrUpdateApdater() {
        //判断是否存在
        if(mAdapter!=null){
//            存在这直接刷新
            mAdapter.getCursor().requery();
            return ;
        }
        //对应查询开启线程

        ThreadUtils.runInThread(new Runnable() {
            @Override
            public void run() {
                //                System.out.println("---------------查询数据-----------------------");
                // 对应查询记录
                final Cursor c = getActivity().getContentResolver().query(ContactsProvider.URI_CONTACT, null, null, null, null, null);
                //假如不存在数据的时候
                if(c.getCount()<=0){
                    return ;
                }


//        int count = c.getColumnCount();//一共多少列  7列
//                System.out.println("-----------------查看数据库数据-----------------------");
//                while(c.moveToNext()){
//                    //循环打印列
//                    System.out.println("数据：");
//                    for(int i=0;i<count;i++){
//                        System.out.print(c.getString(i)+"   ");
//                    }
//                    System.out.println();
//                }
//
//                System.out.println("-----------------查看数据库数据结束----------------------");


                // 数据已经同步到数据库里面了，  设置Adapter，然后listview显示数据
                ThreadUtils.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
//                        System.out.println("-----------------CursorAdapter 开始----------------------");
                        mAdapter = new CursorAdapter(getActivity(), c) {
                            // 如果convertView==null，返回一个具体的根视图
                            @Override
                            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                                View view=View.inflate(context,R.layout.item_contact,null);
                                return view;
//                                TextView tv = new TextView(context);
////                                System.out.println("-----------------newView  进入----------------------");
//                                return tv;

                            }

                            //设置数据显示数据
                            @Override
                            public void bindView(View view, Context context, Cursor cursor) {

//                                System.out.println("-----------------bindView  进入----------------------");

//                                TextView tv = (TextView) view;
//                                String account = cursor.getString(c.getColumnIndex(ContactsOpenHelper.ContactTable.ACCOUNT));
//                                tv.setText(account);

                                ImageView ivHead = (ImageView)view.findViewById(R.id.head);
                                TextView tvAccount = (TextView)view.findViewById(R.id.account);
                                TextView tvNickName = (TextView)view.findViewById(R.id.nickname);
                                String account = cursor.getString(c.getColumnIndex(ContactsOpenHelper.ContactTable.ACCOUNT));
                                String nickName = cursor.getString(c.getColumnIndex(ContactsOpenHelper.ContactTable.NICKNAME));
                                tvAccount.setText(account);
                                tvNickName.setText(nickName);

                            }
                        };
                        //界面显示数据
                        System.out.println("----------setAdapter");

                        listViewContacts.setAdapter(mAdapter);
                    }
                });

            }
        });

    }


    @Override
    public void onDestroy() {
        //按照常理fragment销毁了，我们不应该继续监听，

        // 但是司机室需要一直监听对应roster的改变，
        // 所以应该将联系人监听和同步放到service去
        unRegisterContentProvider();

        super.onDestroy();
    }




    /*-----------------------监听数据库记录发生变化----------------------------*/
    MyContentObserver mMyContentObserver=new MyContentObserver(new Handler());

    /**
     * 注册监听
     */
    public void registerContentProvider(){
        //content://XXXXXX//contact
        //content://XXXXXX//contact/i
        getActivity().getContentResolver().registerContentObserver(ContactsProvider.URI_CONTACT,true,mMyContentObserver);
    }

    /**
     * 反注册监听
     */
    public void unRegisterContentProvider(){
        getActivity().getContentResolver().unregisterContentObserver(mMyContentObserver);
    }



    /**
     * 监听数据库的改变
     */
    class MyContentObserver extends ContentObserver{

        public MyContentObserver(Handler handler){
            super(handler);
        }

        /**
         * 如果数据库发生改变会通知此方法来更新页面
         */
        @Override
        public void onChange(boolean selfChange, @Nullable Uri uri) {
            super.onChange(selfChange, uri);
            //更新adapter或者刷新adapter

            setOrUpdateApdater();

        }
    }
}
