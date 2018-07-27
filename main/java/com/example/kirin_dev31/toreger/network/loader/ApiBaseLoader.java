package com.example.kirin_dev31.toreger.network.loader;

import android.content.Context;

import com.example.kirin_dev31.toreger.views.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class ApiBaseLoader extends BaseAsyncTaskLoader{
    // GET通信
    protected static final String GET = "GET";
    // POST通信
    protected static final String POST = "POST";

    public ApiBaseLoader(Context context) {
        super(context);
    }

    /**
     * GET通信
     * @param url
     * @return json
     * @throws IOException
     */
    protected String apiRequest(String url) throws IOException {
        HttpURLConnection connection = null;

        URL connectionUrl = new URL(url);
        connection = (HttpURLConnection)connectionUrl.openConnection();
        connection.setRequestMethod(GET);

        // Http通信
        connection.connect();

        return getJson(connection);
    }

    /**
     * post通信
     * @param url API
     * @param formParam パラメーター
     * @return json
     * @throws IOException
     */
    protected String apiRequest(String url, String formParam) throws IOException {
        // 変数を初期化
        OutputStreamWriter out = null;
        try {
            URL connectionUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)connectionUrl.openConnection();

            // 通信の設定
            connection.setRequestMethod(POST);
            connection.setDoOutput(true);
//            connection.setRequestProperty("Content-Type", strContentType);

            // パラメーターの設定
            out = new OutputStreamWriter(connection.getOutputStream());
            out.write(formParam);

            // Http通信
            connection.connect();

            return getJson(connection);
        } finally {
            out.close();
        }
    }

    /**
     * GET・POST共通処理
     * @param con
     * @return
     * @throws IOException
     */
    private String getJson (HttpURLConnection con) throws IOException {
        // 変数の初期化
        BufferedReader br = null;
        StringBuilder json = new StringBuilder();
        try {
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // ステータスが200の場合
                br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), Constants.FILE.UTF_8));
                String line = null;
                while ((line = br.readLine()) != null) {
                    json.append(line);
                }
            } else {
                // 成功以外の場合
                throw new IOException();
            }
        } finally {
            br.close();
        }
        return json.toString();
    }
}
