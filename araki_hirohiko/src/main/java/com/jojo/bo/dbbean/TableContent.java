package com.jojo.bo.dbbean;

public class TableContent {

    private int key;

    private String name;

    private String dataType;

    private String isNull;

    private String isKey;

    private String explain;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    public String getIsKey() {
        return isKey;
    }

    public void setIsKey(String isKey) {
        this.isKey = isKey;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    @Override
    public String toString() {
        return "TableContent{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", dataType='" + dataType + '\'' +
                ", isNull='" + isNull + '\'' +
                ", isKey='" + isKey + '\'' +
                ", explain='" + explain + '\'' +
                '}';
    }
}
