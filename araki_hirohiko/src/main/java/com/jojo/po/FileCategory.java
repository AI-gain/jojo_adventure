package com.jojo.po;

import java.io.Serializable;

/**
 * 这是MyBatis Generator自动生成的Model Class.
 * 对应的数据表是 : bm_file_category
 * @author lenovo
 * @date 2021-05-27 21:25:23
 */
public class FileCategory implements Serializable {
    /**
     *
     */
    private String bm;

    /**
     *
     */
    private String mc;

    private static final long serialVersionUID = 1L;

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm == null ? null : bm.trim();
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc == null ? null : mc.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bm=").append(bm);
        sb.append(", mc=").append(mc);
        sb.append("]");
        return sb.toString();
    }
}
