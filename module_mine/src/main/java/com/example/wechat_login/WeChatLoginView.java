package com.example.wechat_login;

import com.example.mvp.IView;

public interface WeChatLoginView extends IView {
    void readed();

    void noRead();

    void getCodeSuccess();
}
