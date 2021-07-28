package com.jojo.util.bean;


public class ResultData {

    private Object data;

    private JoMessage message;

    public JoMessage getMessage() {
        return message;
    }

    public void setMessage(JoMessage message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
