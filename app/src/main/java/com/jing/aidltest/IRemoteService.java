package com.jing.aidltest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by Jing on 16/2/24.
 */
public class IRemoteService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }
    private IBinder iBinder = new IMoocAIDL.Stub(){

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int add(int num1, int num2) throws RemoteException {
            Log.d("TAG","收到了远程的请求"+(num1+num2));
            return num1+num2;
        }
    };


}
