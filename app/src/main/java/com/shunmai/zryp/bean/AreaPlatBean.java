package com.shunmai.zryp.bean;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/11/6.
 */

public class AreaPlatBean {

    /**
     * id : 101
     * name : 北京市
     * level : 1
     * parent_id : 100
     * areas : [{"id":101001,"name":"北京市","level":2,"parent_id":101,"areas":[{"id":101001001,"name":"东城区","level":3,"parent_id":101001},{"id":101001002,"name":"西城区","level":3,"parent_id":101001},{"id":101001005,"name":"朝阳区","level":3,"parent_id":101001},{"id":101001006,"name":"丰台区","level":3,"parent_id":101001},{"id":101001007,"name":"石景山区","level":3,"parent_id":101001},{"id":101001008,"name":"海淀区","level":3,"parent_id":101001},{"id":101001009,"name":"门头沟区","level":3,"parent_id":101001},{"id":101001010,"name":"房山区","level":3,"parent_id":101001},{"id":101001011,"name":"通州区","level":3,"parent_id":101001},{"id":101001012,"name":"顺义区","level":3,"parent_id":101001},{"id":101001013,"name":"昌平区","level":3,"parent_id":101001},{"id":101001014,"name":"大兴区","level":3,"parent_id":101001},{"id":101001015,"name":"怀柔区","level":3,"parent_id":101001},{"id":101001016,"name":"平谷区","level":3,"parent_id":101001},{"id":101001017,"name":"密云县","level":3,"parent_id":101001},{"id":101001018,"name":"延庆县","level":3,"parent_id":101001}]}]
     */

    private int id;
    private String name;
    private int level;
    private int parent_id;
    private List<AreasBeanX> areas;

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

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public List<AreasBeanX> getAreas() {
        return areas;
    }

    public void setAreas(List<AreasBeanX> areas) {
        this.areas = areas;
    }

    public static class AreasBeanX {
        /**
         * id : 101001
         * name : 北京市
         * level : 2
         * parent_id : 101
         * areas : [{"id":101001001,"name":"东城区","level":3,"parent_id":101001},{"id":101001002,"name":"西城区","level":3,"parent_id":101001},{"id":101001005,"name":"朝阳区","level":3,"parent_id":101001},{"id":101001006,"name":"丰台区","level":3,"parent_id":101001},{"id":101001007,"name":"石景山区","level":3,"parent_id":101001},{"id":101001008,"name":"海淀区","level":3,"parent_id":101001},{"id":101001009,"name":"门头沟区","level":3,"parent_id":101001},{"id":101001010,"name":"房山区","level":3,"parent_id":101001},{"id":101001011,"name":"通州区","level":3,"parent_id":101001},{"id":101001012,"name":"顺义区","level":3,"parent_id":101001},{"id":101001013,"name":"昌平区","level":3,"parent_id":101001},{"id":101001014,"name":"大兴区","level":3,"parent_id":101001},{"id":101001015,"name":"怀柔区","level":3,"parent_id":101001},{"id":101001016,"name":"平谷区","level":3,"parent_id":101001},{"id":101001017,"name":"密云县","level":3,"parent_id":101001},{"id":101001018,"name":"延庆县","level":3,"parent_id":101001}]
         */

        private int id;
        private String name;
        private int level;
        private int parent_id;
        private List<AreasBean> areas;

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

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public List<AreasBean> getAreas() {
            return areas;
        }

        public void setAreas(List<AreasBean> areas) {
            this.areas = areas;
        }

        public static class AreasBean {
            /**
             * id : 101001001
             * name : 东城区
             * level : 3
             * parent_id : 101001
             */

            private int id;
            private String name;
            private int level;
            private int parent_id;

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

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
            }
        }
    }
}
