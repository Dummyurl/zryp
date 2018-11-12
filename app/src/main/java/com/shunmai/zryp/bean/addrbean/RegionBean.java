package com.shunmai.zryp.bean.addrbean;

/**
 * Created by yushengyang.
 * Date: 2018/11/8.
 */

public class RegionBean {

    /**
     * id : 101
     * level : 0
     * name : 北京市
     * parentId : 100
     * regionType : 1
     */

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
