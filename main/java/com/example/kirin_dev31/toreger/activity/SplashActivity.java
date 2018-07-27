package com.example.kirin_dev31.toreger.activity;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;

import com.example.kirin_dev31.toreger.R;
import com.example.kirin_dev31.toreger.models.User;
import com.example.kirin_dev31.toreger.network.loader.LoginLoader;
import com.example.kirin_dev31.toreger.views.Constants;


public class SplashActivity extends Activity{

    @Override
    public void onCreate(final Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.splash_view);

        Bundle args = new Bundle();

        args.putString("LOGIN_ID", "");

        getLoaderManager().initLoader(Constants.LOADER_ID.LOADER_LOGIN_ID, args, mCallBack);
    }

    private final LoaderManager.LoaderCallbacks<User> mCallBack = new LoaderManager.LoaderCallbacks<User>() {
        @Override
        public Loader<User> onCreateLoader(int id, Bundle args) {
            return new LoginLoader(SplashActivity.this, args);
        }

        @Override
        public void onLoadFinished(Loader<User> loader, User user) {
            // ローダーの破棄
            getLoaderManager().destroyLoader(loader.getId());
            Intent intent = null;

            if (user == null || user.getId() == -1) {
                // ユーザーが取得できなかった場合
                intent = new Intent(getApplicationContext(), LoginActivity.class);
            } else {
                // ログイン処理が完了
                intent = new Intent(getApplicationContext(), MainActivity.class);
            }
            // アクティビティの起動
            startActivity(intent);

            SplashActivity.this.finish();
        }

        @Override
        public void onLoaderReset(Loader<User> loader) {

        }
    };
}
