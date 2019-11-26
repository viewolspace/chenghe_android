package com.ch.base.bean;

/**
 * @author : sklyand
 * @email :
 * @time : 2019/7/25 11:10
 * @describe ：
 */
public class CopyPartTimeBean {

    /**
     * status : string
     * message : string
     * isJoin : string
     * result : {"id":0,"companyId":0,"recommend":0,"categoryId":"string","categoryName":"string","topNum":0,"title":"string","salary":0,"cycle":0,"lable":"string","contactType":0,"contact":"string","content":"string","num":0,"workTime":"string","workAddress":"string","status":0,"getsTime":"2019-07-25T01:07:42.632Z","geteTime":"2019-07-25T01:07:42.632Z","browseNum":0,"copyNum":0,"joinNum":0,"getcTime":"2019-07-25T01:07:42.632Z","getmTime":"2019-07-25T01:07:42.632Z"}
     */

    private String status;
    private String message;
    private String isJoin;
    private ResultBean result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(String isJoin) {
        this.isJoin = isJoin;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 0
         * companyId : 0
         * recommend : 0
         * categoryId : string
         * categoryName : string
         * topNum : 0
         * title : string
         * salary : 0
         * cycle : 0
         * lable : string
         * contactType : 0
         * contact : string
         * content : string
         * num : 0
         * workTime : string
         * workAddress : string
         * status : 0
         * getsTime : 2019-07-25T01:07:42.632Z
         * geteTime : 2019-07-25T01:07:42.632Z
         * browseNum : 0
         * copyNum : 0
         * joinNum : 0
         * getcTime : 2019-07-25T01:07:42.632Z
         * getmTime : 2019-07-25T01:07:42.632Z
         */

        private int id;
        private int companyId;
        private int recommend;
        private String categoryId;
        private String categoryName;
        private int topNum;
        private String title;
        private int salary;
        private int cycle;
        private String lable;
        private int contactType;
        private String contact;
        private String content;
        private int num;
        private String workTime;
        private String workAddress;
        private int status;
        private String getsTime;
        private String geteTime;
        private int browseNum;
        private int copyNum;
        private int joinNum;
        private String getcTime;
        private String getmTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public int getRecommend() {
            return recommend;
        }

        public void setRecommend(int recommend) {
            this.recommend = recommend;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public int getTopNum() {
            return topNum;
        }

        public void setTopNum(int topNum) {
            this.topNum = topNum;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public int getCycle() {
            return cycle;
        }

        public void setCycle(int cycle) {
            this.cycle = cycle;
        }

        public String getLable() {
            return lable;
        }

        public void setLable(String lable) {
            this.lable = lable;
        }

        public int getContactType() {
            return contactType;
        }

        public void setContactType(int contactType) {
            this.contactType = contactType;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getWorkTime() {
            return workTime;
        }

        public void setWorkTime(String workTime) {
            this.workTime = workTime;
        }

        public String getWorkAddress() {
            return workAddress;
        }

        public void setWorkAddress(String workAddress) {
            this.workAddress = workAddress;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getGetsTime() {
            return getsTime;
        }

        public void setGetsTime(String getsTime) {
            this.getsTime = getsTime;
        }

        public String getGeteTime() {
            return geteTime;
        }

        public void setGeteTime(String geteTime) {
            this.geteTime = geteTime;
        }

        public int getBrowseNum() {
            return browseNum;
        }

        public void setBrowseNum(int browseNum) {
            this.browseNum = browseNum;
        }

        public int getCopyNum() {
            return copyNum;
        }

        public void setCopyNum(int copyNum) {
            this.copyNum = copyNum;
        }

        public int getJoinNum() {
            return joinNum;
        }

        public void setJoinNum(int joinNum) {
            this.joinNum = joinNum;
        }

        public String getGetcTime() {
            return getcTime;
        }

        public void setGetcTime(String getcTime) {
            this.getcTime = getcTime;
        }

        public String getGetmTime() {
            return getmTime;
        }

        public void setGetmTime(String getmTime) {
            this.getmTime = getmTime;
        }
    }
}
