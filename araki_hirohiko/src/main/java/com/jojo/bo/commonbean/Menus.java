package com.jojo.bo.commonbean;

import java.util.List;

public class Menus {

    private String key;

    private String name;

    private String path;

    private String icon;

    private String content;

    private List<Menus> subMenus;

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getIcon() {
        return icon;
    }

    public String getContent() {
        return content;
    }

    public List<Menus> getSubMenus() {
        return subMenus;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSubMenus(List<Menus> subMenus) {
        this.subMenus = subMenus;
    }
}
