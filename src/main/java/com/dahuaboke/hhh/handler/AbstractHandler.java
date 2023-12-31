package com.dahuaboke.hhh.handler;

import com.dahuaboke.hhh.Callback;
import com.dahuaboke.hhh.RequestFactory;
import com.dahuaboke.hhh.SocketClient;
import com.dahuaboke.hhh.SocketContext;
import com.dahuaboke.hhh.adapter.SocketAdapter;
import com.dahuaboke.hhh.adapter.SocketAdapterChain;
import com.dahuaboke.hhh.adapter.http.HttpAdapter;
import com.dahuaboke.hhh.consts.HhhConst;
import com.dahuaboke.hhh.utils.SpringUtils;

import java.lang.reflect.Method;

/**
 * author: dahua
 * date: 2023/11/28 18:00
 */
public abstract class AbstractHandler implements RequestHandler {

    @Override
    public void handler(SocketContext socketContext) {
        Callback callback = socketContext.getCallback();
        RequestFactory requestFactory = SpringUtils.getBean(RequestFactory.class);
        String url = calculatingUrl(socketContext);
        socketContext.setUrl(url);
        com.dahuaboke.hhh.Request request = requestFactory.createRequest(socketContext);
        request.setCallback(callback);
        SocketClient socketClient = SpringUtils.getBean(SocketClient.class);
        socketClient.sendRequest(request);
    }

    @Override
    public String calculatingUrl(SocketContext socketContext) {
        String url = getUrl(socketContext);
        Method method = socketContext.getMethod();
        SocketAdapterChain socketAdapterChain = SpringUtils.getBean(SocketAdapterChain.class);
        for (SocketAdapter socketAdapter : socketAdapterChain.getAdapters()) {
            if (socketAdapter.match(method)) {
                socketContext.setUseSocketAdapter(socketAdapter);
                break;
            }
        }
        SocketAdapter useSocketAdapter = socketContext.getUseSocketAdapter();
        if (useSocketAdapter instanceof HttpAdapter) {
            if (!url.startsWith(HhhConst.HTTP_PREFIX) && !url.startsWith(HhhConst.HTTPS_PREFIX)) {
                if (socketContext.isEnableHttps()) {
                    url = HhhConst.HTTPS_PREFIX + url;
                } else {
                    url = HhhConst.HTTP_PREFIX + url;
                }
            }
            if (!url.endsWith("/")) {
                url += "/";
            }
            String uri = useSocketAdapter.getUri(method);
            if (uri.startsWith("/")) {
                uri = uri.replaceFirst("/", "");
            }
            url += uri;
        }
        return url;
    }

    protected abstract String getUrl(SocketContext socketContext);
}
