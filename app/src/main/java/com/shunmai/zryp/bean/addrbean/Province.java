package com.shunmai.zryp.bean.addrbean;

/**
 * Created by smartTop on 2016/10/19.
 * 省份的实体类
 */

public class Province {
    private int id;
    private int level;
    private String name;
    private int parentId;
    private int regionType;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getRegionType() {
        return regionType;
    }

    public void setRegionType(int regionType) {
        this.regionType = regionType;
    }
}
