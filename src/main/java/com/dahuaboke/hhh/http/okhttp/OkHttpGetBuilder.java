package com.dahuaboke.hhh.http.okhttp;

import com.dahuaboke.hhh.Request;
import com.dahuaboke.hhh.http.HttpGetBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * author: dahua
 * date: 2023/11/27 16:45
 */
@Component
public class OkHttpGetBuilder extends HttpGetBuilder {

    @Override
    public Request buildGetRequest(String url, Map<String, String> headers) {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        if (headers != null) {
            headers.forEach((k, v) -> {
                builder.addHeader(k, v);
            });
        }
        okhttp3.Request request = builder.get().url(url).build();
        return new Request(request);
    }
}
