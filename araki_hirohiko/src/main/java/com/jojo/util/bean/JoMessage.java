package com.jojo.util.bean;

import com.jojo.util.bean.enmus.JoMessageEnum;

public class JoMessage {

    private JoMessageEnum message;

    private String code;

    private Boolean success;

    private String msg;

    public JoMessage(JoMessageEnum message) {
        this.code = message.getCode();
        this.msg = message.getName();
    }

    public JoMessage() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
