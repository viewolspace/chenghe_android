package com.example.base.bean;

import java.util.List;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/25 09:30
 * @describe ：
 */
public class CommonAdBean {

    /**
     * result : [{"cTime":1563898715000,"categoryId":"000000020001","id":1,"imageUrl":"http://112.126.97.144:8081/part-time/20190726/2019072614250354557d4b.png","mTime":1564122305000,"num":1,"status":0,"title":"只要凑足5人即可成团","url":"http://www.baidu.com"},{"cTime":1563898733000,"categoryId":"000000020001","id":2,"imageUrl":"http://112.126.97.144:8081/part-time/20190726/2019072614244385339720.png","mTime":1564122285000,"num":2,"status":0,"title":"没礼貌也传染?英国选手学霍顿拒与孙杨合影","url":"http://www.baidu.com"},{"cTime":1563898745000,"categoryId":"000000020001","id":3,"imageUrl":"http://112.126.97.144:8081/part-time/20190726/2019072614242561240584.png","mTime":1564122268000,"num":3,"status":0,"title":"深海勇士\u201d探秘海底世界：发现海底\u201c珊瑚坟场\u201d","url":"http://www.baidu.com"}]
     * message : ok
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * cTime : 1563898715000
         * categoryId : 000000020001
         * id : 1
         * imageUrl : http://112.126.97.144:8081/part-time/20190726/2019072614250354557d4b.png
         * mTime : 1564122305000
         * num : 1
         * status : 0
         * title : 只要凑足5人即可成团
         * url : http://www.baidu.com
         */

        private long cTime;
        private String categoryId;
        private int id;
        private String imageUrl;
        private long mTime;
        private int num;
        private int status;
        private String title;
        private String url;

        public long getCTime() {
            return cTime;
        }

        public void setCTime(long cTime) {
            this.cTime = cTime;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public long getMTime() {
            return mTime;
        }

        public void setMTime(long mTime) {
            this.mTime = mTime;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
