package com.github.rodlibs.spinnersearch;

/**
 * Created by rodd on 02/06/18.
 */

public class Item {

    private String id;
    private String group;
    private String subitem;


    public Item(String group, String subitem) {
        this.group = group;
        this.subitem = subitem;
    }


    public Item(String id, String group, String subitem) {
        this.id = id;
        this.group = group;
        this.subitem = subitem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSubitem() {
        return subitem;
    }

    public void setSubitem(String subitem) {
        this.subitem = subitem;
    }
}