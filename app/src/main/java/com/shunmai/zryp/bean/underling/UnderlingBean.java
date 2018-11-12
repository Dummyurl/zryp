package com.shunmai.zryp.bean.underling;

import com.shunmai.zryp.bean.TResponse;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/10/11.
 */

public class UnderlingBean extends TResponse<List<UnderlingBean.DataBean>> {

    public static class DataBean {
        /**
         * cardId : 0
         * nickname : Smile
         * mobile : 311460686949
         * count : 0
         * userType : 2
         * userId : 75681
         * realname : 刘培华
         */

        private String cardId;
        private String nickname;
        private String mobile;
        private int count;
        private int userType;
        private int userId;
        private String realname;
        private String pic;

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
