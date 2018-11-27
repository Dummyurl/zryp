package com.shunmai.zryp.bean.userinfo;


import com.ysy.commonlib.base.TResponse;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/10/9.
 */

public class AddressListBean extends TResponse<List<AddressListBean.DataBean>> {



    public static class DataBean {
        /**
         * id : 5449
         * md5key :
         * addr : 北京,朝阳区,四环到五环之间
         * postcode : 000000
         * username : 董荣
         * mobile : 18210054154
         * isDefault : 0
         * userId : 15604
         * isOutAddress : 0
         * provinceCode : 1
         * cityCode : 72
         * countryCode : 2839
         * townCode : 0
         * detailAddress : 鸿博家园二期C期
         */

        private int id;
        private String md5key;
        private String addr;
        private String postcode;
        private String username;
        private String mobile;
        private int isDefault;
        private int userId;
        private int isOutAddress;
        private int provinceCode;
        private int cityCode;
        private int countryCode;
        private int townCode;
        private String detailAddress;

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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getIsOutAddress() {
            return isOutAddress;
        }

        public void setIsOutAddress(int isOutAddress) {
            this.isOutAddress = isOutAddress;
        }

        public int getProvinceCode() {
            return provinceCode;
        }

        public void setProvinceCode(int provinceCode) {
            this.provinceCode = provinceCode;
        }

        public int getCityCode() {
            return cityCode;
        }

        public void setCityCode(int cityCode) {
            this.cityCode = cityCode;
        }

        public int getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(int countryCode) {
            this.countryCode = countryCode;
        }

        public int getTownCode() {
            return townCode;
        }

        public void setTownCode(int townCode) {
            this.townCode = townCode;
        }

        public String getDetailAddress() {
            return detailAddress;
        }

        public void setDetailAddress(String detailAddress) {
            this.detailAddress = detailAddress;
        }
    }
}
