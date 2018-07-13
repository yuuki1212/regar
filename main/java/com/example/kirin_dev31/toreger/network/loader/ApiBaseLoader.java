package com.example.kirin_dev31.toreger.network.loader;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class ApiBaseLoader extends BaseAsyncTaskLoader{

    // 文字コード(UTF-8)
    protected static final String UTF_8 = "UTF-8";
    // GET通信
    protected static final String GET = "GET";
    // POST通信
    protected static final String POST = "POST";

    public ApiBaseLoader(Context context) {
        super(context);
    }

    /**
     *
     * @param url
     * @param method 通信メソッド
     * @param encoding 文字コード
     * @return json
     * @throws Exception
     */
    protected String getJson(String url, String method, String encoding) throws Exception {
        StringBuilder result = new StringBuilder();
        BufferedReader bufferedReader = null;
        HttpURLConnection connection = null;
        try {
            URL connectionUrl = new URL(url);
            connection = (HttpURLConnection)connectionUrl.openConnection();

            bufferedReader =
                    new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = null;

            while((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        } finally {
            bufferedReader.close();
        }
        return result.toString();
    }

    /**
     * json生成
     * @param object
     * @return エラーメッセージ
     */
    private String createJsom(Object object[]) {
        return null;
    }
}
