package com.jojo.util.bean.enmus;

public enum JoMessageEnum {
    /**
     * SUCCESS
     * ERROR
     * WARNING
     */
    SUCCESS("000000", "请求成功！"), ERROR("999999", "请求失败！"), WARNING("555555", "请求存在风险！"),
    TOKEN_MISSING("000400", "请求token缺失！"), TOKEN_INVALIDATION("000401", "请求token失效！");

    private String code;

    private String name;

    private JoMessageEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
