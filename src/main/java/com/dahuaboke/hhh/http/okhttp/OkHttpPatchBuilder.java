package com.dahuaboke.hhh.http.okhttp;

import com.dahuaboke.hhh.Request;
import com.dahuaboke.hhh.http.HttpPatchBuilder;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * author: dahua
 * date: 2023/11/27 16:45
 */
@Component
public class OkHttpPatchBuilder extends HttpPatchBuilder {

    @Override
    public Request buildPatchRequest(String url, Map<String, String> headers, String body, String contentType) {
        MediaType mediaType = MediaType.get(contentType);
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        if (headers != null) {
            headers.forEach((k, v) -> {
                builder.addHeader(k, v);
            });
        }
        RequestBody requestBody = RequestBody.create(body, mediaType);
        okhttp3.Request request = builder.patch(requestBody).url(url).build();
        return new Request(request);
    }
}
