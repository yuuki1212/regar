package com.example.kirin_dev31.toreger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class SplashActivity extends Activity {

    Handler mHandler = new Handler();

    public void onCreate(final Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.splash_view);

        // 2秒したらMainActivityを呼び出してSplashActivityを終了する
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // MainActivityを呼び出す
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                // アクティビティの起動(MainActivity)
                startActivity(intent);

                SplashActivity.this.finish();
            }
        }, 1 * 1000); // 2秒後に実行
    }
}
