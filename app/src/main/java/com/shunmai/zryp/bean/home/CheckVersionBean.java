package com.shunmai.zryp.bean.home;

/**
 * Created by yushengyang.
 * Date: 2018/11/7.
 */

public class CheckVersionBean {

    /**
     * data : {"sysIsEnable":1,"vid":1,"vdevice":"android","vversion":"0","visenforce":true,"vchannelcode":"xiaomi","vchannelname":"小米"}
     */


    private int sysIsEnable;
    private int vid;
    private String vdevice;
    private String vversion;
    private boolean visenforce;
    private String vchannelcode;
    private String vchannelname;
    private String vdownloadUrl;
    private String vcomment;

    public int getSysIsEnable() {
        return sysIsEnable;
    }

    public void setSysIsEnable(int sysIsEnable) {
        this.sysIsEnable = sysIsEnable;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getVdevice() {
        return vdevice;
    }

    public void setVdevice(String vdevice) {
        this.vdevice = vdevice;
    }

    public String getVversion() {
        return vversion;
    }

    public void setVversion(String vversion) {
        this.vversion = vversion;
    }

    public boolean isVisenforce() {
        return visenforce;
    }

    public void setVisenforce(boolean visenforce) {
        this.visenforce = visenforce;
    }

    public String getVchannelcode() {
        return vchannelcode;
    }

    public void setVchannelcode(String vchannelcode) {
        this.vchannelcode = vchannelcode;
    }

    public String getVchannelname() {
        return vchannelname;
    }

    public void setVchannelname(String vchannelname) {
        this.vchannelname = vchannelname;
    }

    public String getVdownloadUrl() {
        return vdownloadUrl;
    }

    public void setVdownloadUrl(String vdownloadUrl) {
        this.vdownloadUrl = vdownloadUrl;
    }

    public String getVcomment() {
        return vcomment;
    }

    public void setVcomment(String vcomment) {
        this.vcomment = vcomment;
    }
}
