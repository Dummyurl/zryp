package com.shunmai.zryp.bean.userinfo;

import com.shunmai.zryp.bean.TResponse;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/10/9.
 */

public class AddressListBean extends TResponse<List<AddressListBean.DataBean>> {



    public static class DataBean {
        /**
         * id : 5120
         * md5key : 26f534a672c38bd4f9e2c1c6c5f5338a
         * addr : 山东省青岛市莱西市山东省青岛莱西市姜山镇后庞村
         * postcode : 266600
         * username : 赵克忠
         * mobile : 15263039780
         */

        private int id;
        private String md5key;
        private String addr;
        private String postcode;
        private String username;
        private String mobile;
        private int isDefault;
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMd5key() {
            return md5key;
        }

        public void setMd5key(String md5key) {
            this.md5key = md5key;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }
    }
}
