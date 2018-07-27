package com.example.kirin_dev31.toreger.network.loader;

import android.content.Context;
import android.os.Bundle;

import com.example.kirin_dev31.toreger.models.User;
import com.example.kirin_dev31.toreger.views.Constants;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class LoginLoader extends ApiBaseLoader {

    public LoginLoader(Context context, Bundle args) {
        super(context);
    }

    @Override
    public User loadInBackground() {
        try {
            // ユーザー情報の取得
            String result = apiRequest(Constants.API.GET_USER_INFO);
            // Objectに変換
            JSONObject json = new JSONObject(result);
            return (User)json.get("");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
