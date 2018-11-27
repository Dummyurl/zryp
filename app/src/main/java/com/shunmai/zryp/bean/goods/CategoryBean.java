package com.shunmai.zryp.bean.goods;

import com.ysy.commonlib.base.TResponse;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/9/20.
 */

public class CategoryBean extends TResponse<List<CategoryBean.DataBean>> {

    public static class DataBean {
        /**
         * pid : 11090961178431000
         * name : 医药保健
         * sort : 6000
         * sysId : 11090961178431136
         * pic :
         * level : 1
         * sonItem : [{"pid":11090961178431136,"name":"滋补品","sort":5800,"sysId":11090961178431138,"pic":"http://pic0.gzcfe.net/brand/2018/0810/4863521711901964386","level":2,"sonItem":null},{"pid":11090961178431136,"name":"隐形眼镜","sort":5800,"sysId":11090961178431141,"pic":"http://pic0.gzcfe.net/brand/2018/0810/5070289278150905335","level":2,"sonItem":null},{"pid":11090961178431136,"name":"保健品","sort":5800,"sysId":11090961178431137,"pic":"http://pic0.gzcfe.net/brand/2018/0810/5077205352357234515","level":2,"sonItem":null},{"pid":11090961178431136,"name":"医疗器械","sort":5800,"sysId":11090961178431140,"pic":"http://pic0.gzcfe.net/brand/2018/0810/5405739746561672905","level":2,"sonItem":null},{"pid":11090961178431136,"name":"计生情趣","sort":5800,"sysId":11090961178431143,"pic":"http://pic0.gzcfe.net/brand/2018/0810/5136806289268108757","level":2,"sonItem":null},{"pid":11090961178431136,"name":"医药","sort":5800,"sysId":11090961178431139,"pic":"http://pic0.gzcfe.net/brand/2018/0810/5665897157272919467","level":2,"sonItem":null},{"pid":11090961178431136,"name":"护理护具","sort":5800,"sysId":11090961178431142,"pic":"http://pic0.gzcfe.net/brand/2018/0810/5047544469323218363","level":2,"sonItem":null}]
         */

        private long pid;
        private String name;
        private int sort;
        private long sysId;
        private String pic;
        private int level;
        private List<SonItemBean> sonItem;

        public long getPid() {
            return pid;
        }

        public void setPid(long pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public long getSysId() {
            return sysId;
        }

        public void setSysId(long sysId) {
            this.sysId = sysId;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public List<SonItemBean> getSonItem() {
            return sonItem;
        }

        public void setSonItem(List<SonItemBean> sonItem) {
            this.sonItem = sonItem;
        }

        public static class SonItemBean {
            /**
             * pid : 11090961178431136
             * name : 滋补品
             * sort : 5800
             * sysId : 11090961178431138
             * pic : http://pic0.gzcfe.net/brand/2018/0810/4863521711901964386
             * level : 2
             * sonItem : null
             */

            private long pid;
            private String name;
            private int sort;
            private long sysId;
            private String pic;
            private int level;
            private Object sonItem;

            public long getPid() {
                return pid;
            }

            public void setPid(long pid) {
                this.pid = pid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public long getSysId() {
                return sysId;
            }

            public void setSysId(long sysId) {
                this.sysId = sysId;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public Object getSonItem() {
                return sonItem;
            }

            public void setSonItem(Object sonItem) {
                this.sonItem = sonItem;
            }
        }
    }
}
