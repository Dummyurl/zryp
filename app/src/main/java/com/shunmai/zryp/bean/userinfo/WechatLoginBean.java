package com.shunmai.zryp.bean.userinfo;

import com.google.gson.annotations.SerializedName;
import com.shunmai.zryp.bean.TResponse;

/**
 * Created by yushengyang.
 * Date: 2018/10/17.
 */

public class WechatLoginBean extends TResponse<WechatLoginBean.DataBean> {


    /**
     * data : {"id":134105,"channelId":0,"mobile":"18511257379","userType":0,"nickname":"昵称","unionId":"","sysIdString":14765816939679744}
     */



    public static class DataBean {
        /**
         * id : 134105
         * channelId : 0
         * mobile : 18511257379
         * userType : 0
         * nickname : 昵称
         * unionId :
         * sysIdString : 14765816939679744
         */

        private int id;
        private int channelId;
        private String mobile;
        private String logonAccount;
        private int userType;
        private String nickname;
        private String unionId;
        private long sysIdString;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getChannelId() {
            return channelId;
        }

        public void setChannelId(int channelId) {
            this.channelId = channelId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUnionId() {
            return unionId;
        }

        public void setUnionId(String unionId) {
            this.unionId = unionId;
        }

        public long getSysIdString() {
            return sysIdString;
        }

        public void setSysIdString(long sysIdString) {
            this.sysIdString = sysIdString;
        }

        public String getLogonAccount() {
            return logonAccount;
        }

        public void setLogonAccount(String logonAccount) {
            this.logonAccount = logonAccount;
        }
    }
}
