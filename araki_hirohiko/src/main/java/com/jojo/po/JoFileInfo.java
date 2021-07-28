package com.jojo.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 这是MyBatis Generator自动生成的Model Class.
 * 对应的数据表是 : jojo_file_info
 * @author lenovo
 * @date 2021-05-27 10:23:18
 */
public class JoFileInfo implements Serializable {
    /**
     *
     */
    private Long fileid;

    /**
     *
     */
    private String filedes;

    /**
     *
     */
    private String fileinfo;

    /**
     *
     */
    private String filecategory;

    /**
     *
     */
    private String filecovers;

    /**
     *
     */
    private Date createdate;

    /**
     *
     */
    private Date modifydate;

    /**
     *
     */
    private Integer createuser;

    /**
     *
     */
    private Integer modifyuser;

    private static final long serialVersionUID = 1L;

    public Long getFileid() {
        return fileid;
    }

    public void setFileid(Long fileid) {
        this.fileid = fileid;
    }

    public String getFiledes() {
        return filedes;
    }

    public void setFiledes(String filedes) {
        this.filedes = filedes == null ? null : filedes.trim();
    }

    public String getFileinfo() {
        return fileinfo;
    }

    public void setFileinfo(String fileinfo) {
        this.fileinfo = fileinfo == null ? null : fileinfo.trim();
    }

    public String getFilecategory() {
        return filecategory;
    }

    public void setFilecategory(String filecategory) {
        this.filecategory = filecategory == null ? null : filecategory.trim();
    }

    public String getFilecovers() {
        return filecovers;
    }

    public void setFilecovers(String filecovers) {
        this.filecovers = filecovers == null ? null : filecovers.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public Integer getCreateuser() {
        return createuser;
    }

    public void setCreateuser(Integer createuser) {
        this.createuser = createuser;
    }

    public Integer getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(Integer modifyuser) {
        this.modifyuser = modifyuser;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fileid=").append(fileid);
        sb.append(", filedes=").append(filedes);
        sb.append(", fileinfo=").append(fileinfo);
        sb.append(", filecategory=").append(filecategory);
        sb.append(", filecovers=").append(filecovers);
        sb.append(", createdate=").append(createdate);
        sb.append(", modifydate=").append(modifydate);
        sb.append(", createuser=").append(createuser);
        sb.append(", modifyuser=").append(modifyuser);
        sb.append("]");
        return sb.toString();
    }
}