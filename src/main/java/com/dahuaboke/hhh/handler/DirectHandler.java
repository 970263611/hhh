package com.dahuaboke.hhh.handler;

import com.dahuaboke.hhh.Callback;
import com.dahuaboke.hhh.RequestFactory;
import com.dahuaboke.hhh.SocketClient;
import com.dahuaboke.hhh.SocketContext;
import com.dahuaboke.hhh.adapter.SocketAdapter;
import com.dahuaboke.hhh.consts.HhhConst;
import com.dahuaboke.hhh.utils.SpringUtils;

import java.lang.reflect.Method;

/**
 * author: dahua
 * date: 2023/11/20 17:20
 */
public class DirectHandler implements RequestHandler {

    @Override
    public void handler(SocketContext socketContext) {
        Callback callback = socketContext.getCallback();
        String param = socketContext.getParam();
        RequestFactory requestFactory = SpringUtils.getBean(RequestFactory.class);
        String url = calculatingUrl(socketContext);
        com.dahuaboke.hhh.Request request = requestFactory.createRequest(url, socketContext.getContentType(), param);
        request.setCallback(callback);
        SocketClient socketClient = SpringUtils.getBean(SocketClient.class);
        socketClient.sendRequest(request);
    }

    @Override
    public String calculatingUrl(SocketContext socketContext) {
        String url = socketContext.getUrl();
        if (!url.startsWith(HhhConst.HTTP_PREFIX)) {
            url = HhhConst.HTTP_PREFIX + url;
        }
        if (!url.endsWith("/")) {
            url += "/";
        }
        for (SocketAdapter socketAdapter : SocketContext.getSocketAdapters()) {
            Method method = socketContext.getMethod();
            if (socketAdapter.match(method)) {
                String uri = socketAdapter.getUri(method);
                if (uri.startsWith("/")) {
                    uri = uri.replaceFirst("/", "");
                }
                url += uri;
                break;
            }
        }
        return url;
    }
}
