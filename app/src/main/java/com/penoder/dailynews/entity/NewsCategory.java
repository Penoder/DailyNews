package com.penoder.dailynews.entity;

import java.util.List;

/**
 * 新闻种类
 *
 * @author Penoder
 * @date 18-4-19.
 */
public class NewsCategory {

    /**
     * {
     * "success": false,
     * "code": "SW-00994",
     * "msg": "鉴权过期",
     * "requestId": "364863eadce8475c914beefd30cde6c8",
     * "data": null
     * }
     */

    /**
     * success : true
     * code :
     * msg :
     * requestId : 9f22330ffa8140eabd21b3c4ee0fc779
     * data : {
     *      "categories":[
     *          {"name":"时政","alias":"Politics"},
     *          {"name":"军事","alias":"Military"},
     *          {"name":"财经","alias":"Finance"},
     *          ......
     *      ]
     * }
     */

    private boolean success;
    private String code;
    private String msg;
    private String requestId;
    private DataEntity data;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getRequestId() {
        return requestId;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * name : 时政
         * alias : Politics
         */

        private List<CategoriesEntity> categories;

        public void setCategories(List<CategoriesEntity> categories) {
            this.categories = categories;
        }

        public List<CategoriesEntity> getCategories() {
            return categories;
        }

        public static class CategoriesEntity {
            private String name;
            private String alias;

            public void setName(String name) {
                this.name = name;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public String getName() {
                return name;
            }

            public String getAlias() {
                return alias;
            }
        }
    }
}
