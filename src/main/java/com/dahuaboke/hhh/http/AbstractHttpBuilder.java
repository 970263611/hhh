package com.dahuaboke.hhh.http;

import com.dahuaboke.hhh.Request;
import com.dahuaboke.hhh.SocketContext;
import com.dahuaboke.hhh.codec.CodecConverter;
import com.dahuaboke.hhh.exception.NeedRequestParamException;
import com.dahuaboke.hhh.hook.Hook;
import com.dahuaboke.hhh.hook.HookChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

/**
 * author: dahua
 * date: 2023/11/28 9:49
 */
public abstract class AbstractHttpBuilder implements HttpBuilder {

    private static final String PREFIX = "?";
    private static final String EQUALS = "=";
    private static final String AND = "&";

    @Autowired
    protected CodecConverter codecConverter;
    @Autowired
    protected HookChain hookChain;

    @Override
    public Request build(SocketContext socketContext) {
        buildParam(socketContext);
        aroundBuild(socketContext);
        return buildHttp(socketContext);
    }

    private void aroundBuild(SocketContext socketContext) {
        List<Hook> hooks = hookChain.getHookChain();
        for (Hook hook : hooks) {
            hook.beforeSendRequest(socketContext);
        }
    }

    private void buildParam(SocketContext socketContext) {
        Method method = socketContext.getMethod();
        Parameter[] parameters = method.getParameters();
        Object[] params = socketContext.getParams();
        if (parameters.length == 0 || params == null) {
            return;
        }
        Assert.isTrue(parameters.length == params.length, "params length error");
        StringBuffer stringBuffer = new StringBuffer(PREFIX);
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object param = params[i];
            RequestBody requestBody = parameter.getAnnotation(RequestBody.class);
            if (requestBody != null) {
                /**
                 * RequestBody注解只能使用一次，所以这里不处理多个的情况，如果有多个获取第一个
                 */
                String body = socketContext.getBody();
                if (StringUtils.isEmpty(body)) {
                    socketContext.setBody(codecConverter.encode(param));
                }
            } else {
                RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
                String key;
                if (requestParam != null) {
                    key = requestParam.value();
                } else {
                    /**
                     * jdk1.8 通过指定-parameters参数之后可以获取到
                     */
                    if (parameter.isNamePresent()) {
                        key = parameter.getName();
                    } else {
                        /**
                         * springmvc通过asm可以获取参数名称，但是局限于非接口类
                         */
                        throw new NeedRequestParamException("params [" + i + "] need @RequestParam annotation");
                    }
                }
                stringBuffer.append(key);
                stringBuffer.append(EQUALS);
                stringBuffer.append(codecConverter.encode(param));
                stringBuffer.append(AND);
            }
        }
        String param = stringBuffer.substring(0, stringBuffer.length() - 1);
        param = param.equals(PREFIX) ? null : param;
        String url = socketContext.getUrl();
        socketContext.setUrl(url + param);
    }

    protected abstract Request buildHttp(SocketContext socketContext);
}
