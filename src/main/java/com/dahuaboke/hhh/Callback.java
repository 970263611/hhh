package com.dahuaboke.hhh;

import java.io.InputStream;

/**
 * author: dahua
 * date: 2023/11/24 15:13
 */
public interface Callback {

    void onSuccess(InputStream is);

    void onFailure(Exception e);
}
