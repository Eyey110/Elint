package com.qisejin.elint;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

/**
 * No Description
 * <p>
 * Created by 21:27 2018/6/7.
 * Email:46499102@qq.com
 *
 * @author Eyey
 */

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread().start();
        Message message = new Message();
        Log.d("test","test");
        System.out.print("test");
    }
}
