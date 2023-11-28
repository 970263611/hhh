package com.dahuaboke.hhh.http.okhttp;

import com.dahuaboke.hhh.Request;
import com.dahuaboke.hhh.exception.ClientTypeErrorException;
import com.dahuaboke.hhh.exception.RequestException;
import com.dahuaboke.hhh.http.HttpClient;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * author: dahua
 * date: 2023/11/24 14:20
 */
public class OkHttpClient extends HttpClient {

    private okhttp3.OkHttpClient client;

    @PostConstruct
    public void init() {
        client = new okhttp3.OkHttpClient
                .Builder()
                .connectTimeout(httpConnectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(httpReadTimeout, TimeUnit.MILLISECONDS)
                .build();
    }

    @Override
    public void sendRequest(Request request) {
        Object builder = request.getRequest();
        if (builder instanceof okhttp3.Request) {
            okhttp3.Request param = (okhttp3.Request) builder;
            client.newCall(param).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    request.getCallback().onFailure(e);
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) {
                    if (response.code() != 200) {
                        request.getCallback().onFailure(new RequestException(response.toString()));
                    } else {
                        request.getCallback().onSuccess(response.body().byteStream());
                    }
                }
            });
        } else {
            throw new ClientTypeErrorException();
        }
    }
}
