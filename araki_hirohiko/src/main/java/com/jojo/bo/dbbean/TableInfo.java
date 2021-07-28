package com.jojo.bo.dbbean;

public class TableInfo {

    private String tableName;

    private String comments;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "tableName='" + tableName + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
