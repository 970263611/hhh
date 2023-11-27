package com.dahuaboke.hhh.http.okhttp;

import com.dahuaboke.hhh.Request;
import com.dahuaboke.hhh.http.HttpPutBuilder;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * author: dahua
 * date: 2023/11/27 16:45
 */
@Component
public class OkHttpPutBuilder extends HttpPutBuilder {

    @Override
    public Request buildPutRequest(String url, Map<String, String> headers, String param, String contentType) {
        MediaType mediaType = MediaType.get(contentType);
        RequestBody requestBody = RequestBody.create(param, mediaType);
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        if (headers != null) {
            headers.forEach((k, v) -> {
                builder.addHeader(k, v);
            });
        }
        okhttp3.Request request = builder.put(requestBody).url(url).build();
        return new Request(request);
    }
}