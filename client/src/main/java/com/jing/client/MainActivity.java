package com.jing.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.jing.aidl.IMoocAIDL;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button mBtn;
    private EditText editNum1;
    private EditText editNum2;
    private EditText editNum3;
    IMoocAIDL iMoocAIDL;
    private ServiceConnection coon = new ServiceConnection() {
        //绑定上服务的时候，执行
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //拿到了远程的服务
            iMoocAIDL = IMoocAIDL.Stub.asInterface(service);
        }
        //断开服务的时候,
        @Override
        public void onServiceDisconnected(ComponentName name) {
            iMoocAIDL = null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        bindService();//软件启动绑定服务
    }

    private void initData() {
        mBtn = (Button) findViewById(R.id.btn_num);
        editNum1 = (EditText) findViewById(R.id.editNum1);
        editNum2 = (EditText) findViewById(R.id.editNum2);
        editNum3 = (EditText) findViewById(R.id.editNum3);
        mBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //点击的时候进行计算
        int num1 = Integer.parseInt(editNum1.getText().toString());
        int num2 = Integer.parseInt(editNum2.getText().toString());
        try {
            int res = iMoocAIDL.add(num1,num2);
            editNum3.setText(res+"");
        } catch (RemoteException e) {
            e.printStackTrace();
            editNum3.setText("错误了");
        }

    }

    private void bindService() {
        //绑定服务，获取服务端
        Intent intent = new Intent("com.jing.server.IRemoteService");//使用action 明文访问
        //intent.setPackage("com.jing.server");
        //intent.setAction("com.jing.server.IRemoteService");
        //明文标识,显示绑定,5.0后
        //intent.setComponent(new ComponentName("com.jing.server","com.jing.server.IRemoteService"));
        bindService(intent, coon, Context.BIND_AUTO_CREATE);
    }

    //销毁的时候要解绑定

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(coon);
    }
}
