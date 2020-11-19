package com.pandas.guardianshipassistant.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pandas.guardianshipassistant.R;
import com.pandas.guardianshipassistant.config.ConstantConfig;
import com.pandas.guardianshipassistant.ui.service.IMService;
import com.pandas.guardianshipassistant.ui.service.PushService;
import com.pandas.guardianshipassistant.utils.ThreadUtils;
import com.pandas.guardianshipassistant.utils.ToastUtils;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;

import java.net.InetAddress;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    public static final String SERVICENAME = ConstantConfig.DOMAIN;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.login_account)
    EditText loginAccount;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.login_password)
    EditText loginPassword;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.button_register)
    TextView buttonRegister;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.button_login)
    TextView buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initLoginView();
        initRegisterView();
    }

    private void initLoginView() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                判断账户是否为空
                final String account=loginAccount.getText().toString();
                if(TextUtils.isEmpty(account)){
                    loginAccount.setError("用户名不能为空");
                    return ;
                }

//                判断密码是否为空
                final String  password=loginPassword.getText().toString();
                if(TextUtils.isEmpty(password)){
                    loginPassword.setError("密码不能为空");
                    return ;
                }
                //从服务器上面拿到密码，验证，如果服务器上面没有账户密码，跳转到register页面去
                final String  HOST=ConstantConfig.HOST;
                final  int  PORT=ConstantConfig.PORT;
//                final String  domain="192.168.133.2";
                ThreadUtils.runInThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            InetAddress addr = InetAddress.getLocalHost();
                            System.out.println("Local HostAddress: "+addr.getHostAddress());
                            String hostname = addr.getHostName();

                            System.out.println("Local host name: "+hostname);
//                            System.out.println(InetAddress.get);
                        }catch(Exception e){
                            e.printStackTrace();
                        }

                        try{
                            System.out.println("1.创建连接配置对象");
                            //1.创建连接配置对象
                            ConnectionConfiguration config = new ConnectionConfiguration(HOST,PORT);
                            System.out.println("1.创建连接配置对象成功");
                            //额外配置（方便我们开发的时候使用，上线之后可以改回来）
                            config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);//明文传输
                            config.setDebuggerEnabled(true);//开启调试模式，方便我们查看具体内容

                            //2.开始创建连接对象
                            XMPPConnection conn = new XMPPConnection(config);


//                            System.out.println("2.开始创建连接对象成功");
//                            System.out.println("account:"+account+"  password:"+password);
                            //开始连接
                            System.out.println("开始连接");
                            conn.connect();

                            //保存当前连接，方便后面contacts使用。
                            IMService.conn=conn;

                            //3.连接成功，开始登陆

                            conn.login(account, password);
//                            System.out.println("登陆成功----");

                            ToastUtils.showToastSafe(getApplicationContext(),"登陆成功");
                            //成功之后并转接到MainActivity

                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();//这个到底是需不需要了，注意一下


                            //保存当前连接，方便后面contacts使用。
                            IMService.conn=conn;
                            // admin-->admin@192.168.133.2
                            IMService.mCurAccout=account+"@"+ConstantConfig.DOMAIN;

                            //启动IMService
                            Intent imSrvice = new Intent(LoginActivity.this, IMService.class);
                            startService(imSrvice);

                            //启动PUSHService
                            Intent pushService = new Intent(LoginActivity.this, PushService.class);
                            startService(pushService);

                            System.out.println("--------loginActivity----------");
                        }catch(Exception e){
                            e.printStackTrace();
                            ToastUtils.showToastSafe(getApplicationContext(),"登陆失败");
                        }
                    }
                });


//                //校验并转接到MainActivity
//
//                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
//                startActivity(intent);
//                finish();
            }

        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initRegisterView(){
//        System.out.println( "注册");
//        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
//        startActivity(intent);


    }
}
