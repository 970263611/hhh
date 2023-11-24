package com.dahuaboke.hhh.http.okhttp;

import com.dahuaboke.hhh.Request;
import com.dahuaboke.hhh.http.HttpRequestFactory;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author: dahua
 * date: 2023/11/24 14:09
 */
public class OkHttpRequestFactory extends HttpRequestFactory {

    @Override
    public Request createRequest(String url, String contentType, String param) {
        MediaType mediaType = MediaType.get(contentType);
        RequestBody requestBody = RequestBody.create(param, mediaType);
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        okhttp3.Request request = builder.post(requestBody).url(url).build();
        return new Request(request);
    }
}
