package com.example.kirin_dev31.toreger.network.loader;

import android.content.Context;

import com.example.kirin_dev31.toreger.views.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoginLoader extends ApiBaseLoader {

    public LoginLoader(Context context) {
        super(context);
    }

    @Override
    public Object loadInBackground() {
        try {
            // ユーザー情報の取得
            String result = getJson(Constants.URL.GET_USER_INFO, POST, UTF_8);
            // Objectに変換
            return new JSONObject(result);

//            if ((boolean) json.get("success")) {
//                // 成功した場合は
//                User user = (User)json.get("result");
//            }
//            String message = (String)json.get("message");
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
