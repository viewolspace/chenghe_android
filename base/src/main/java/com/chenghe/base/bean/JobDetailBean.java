package com.chenghe.base.bean;

/**
 * @author : sklyand
 * @email :
 * @time : 2019/7/25 10:17
 * @describe ：
 */
public class JobDetailBean {


    /**
     * isJoin : 0
     * result : {"browseNum":0,"cTime":1564023701000,"categoryId":"000000010001","categoryName":"保洁员","companyId":1,"contact":"112121212","contactType":1,"content":"<p><span>高级产品经理<\/span><br><span>职位要求：<\/span><br><span>1、计算机或相关专业，本科及以上学历；<\/span><br><span>2、具有5年以上的大型企业ERP建设经验，有现代服务、电商公司经验者优先，熟悉ERP的各种需求优先；<\/span><br><span>3、对技术市场具有一定的敏锐度，善于与业内同行沟通交流，能够及时掌握市场发展动态，对公司技术发展能提供决策性的建议<\/span><br><span>4、能够接受新鲜事物，善于探索和接受新的管理理念，不拘泥于传统方法；<\/span><br><span>5、具有3年以上的团队管理经验。<\/span><\/p>","copyNum":0,"cycle":1,"eTime":1721876489000,"id":11,"joinNum":0,"lable":"日结,长期,男女不限","mTime":1564214630000,"num":20,"pic":"http://112.126.97.144:8081/part-time/20190726/2019072611400553697933.png","recommend":1,"sTime":1563332484000,"salary":100,"status":0,"title":"招聘10人","topNum":0,"workAddress":"北京北京","workTime":"2-10点"}
     * company : {"des":"啥都送","id":1,"logo":"http://112.126.97.144:8081/part-time/20190726/20190726234450874a08e6.png","name":"饿了么","phone":"18899996666","qq":"11111234324","star":4,"wx":"92348732"}
     * message : ok
     * status : 0000
     */

    private String isJoin;
    private ResultBean result;
    private CompanyBean company;
    private String message;
    private String status;

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

    public CompanyBean getCompany() {
        return company;
    }

    public void setCompany(CompanyBean company) {
        this.company = company;
    }

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

    public static class ResultBean {
        /**
         * browseNum : 0
         * cTime : 1564023701000
         * categoryId : 000000010001
         * categoryName : 保洁员
         * companyId : 1
         * contact : 112121212
         * contactType : 1
         * content : <p><span>高级产品经理</span><br><span>职位要求：</span><br><span>1、计算机或相关专业，本科及以上学历；</span><br><span>2、具有5年以上的大型企业ERP建设经验，有现代服务、电商公司经验者优先，熟悉ERP的各种需求优先；</span><br><span>3、对技术市场具有一定的敏锐度，善于与业内同行沟通交流，能够及时掌握市场发展动态，对公司技术发展能提供决策性的建议</span><br><span>4、能够接受新鲜事物，善于探索和接受新的管理理念，不拘泥于传统方法；</span><br><span>5、具有3年以上的团队管理经验。</span></p>
         * copyNum : 0
         * cycle : 1
         * eTime : 1721876489000
         * id : 11
         * joinNum : 0
         * lable : 日结,长期,男女不限
         * mTime : 1564214630000
         * num : 20
         * pic : http://112.126.97.144:8081/part-time/20190726/2019072611400553697933.png
         * recommend : 1
         * sTime : 1563332484000
         * salary : 100
         * status : 0
         * title : 招聘10人
         * topNum : 0
         * workAddress : 北京北京
         * workTime : 2-10点
         */

        private int browseNum;
        private long cTime;
        private String categoryId;
        private String categoryName;
        private int companyId;
        private String contact;
        private int contactType;
        private String content;
        private int copyNum;
        private int cycle;
        private long eTime;
        private int id;
        private int joinNum;
        private String lable;
        private long mTime;
        private int num;
        private String pic;
        private int recommend;
        private long sTime;
        private int salary;
        private int status;
        private String title;
        private int topNum;
        private String workAddress;
        private String workTime;
        private int verify;

        public long getcTime() {
            return cTime;
        }

        public void setcTime(long cTime) {
            this.cTime = cTime;
        }

        public long geteTime() {
            return eTime;
        }

        public void seteTime(long eTime) {
            this.eTime = eTime;
        }

        public long getmTime() {
            return mTime;
        }

        public void setmTime(long mTime) {
            this.mTime = mTime;
        }

        public long getsTime() {
            return sTime;
        }

        public void setsTime(long sTime) {
            this.sTime = sTime;
        }

        public int getVerify() {
            return verify;
        }

        public void setVerify(int verify) {
            this.verify = verify;
        }

        public int getBrowseNum() {
            return browseNum;
        }

        public void setBrowseNum(int browseNum) {
            this.browseNum = browseNum;
        }

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

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public int getContactType() {
            return contactType;
        }

        public void setContactType(int contactType) {
            this.contactType = contactType;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCopyNum() {
            return copyNum;
        }

        public void setCopyNum(int copyNum) {
            this.copyNum = copyNum;
        }

        public int getCycle() {
            return cycle;
        }

        public void setCycle(int cycle) {
            this.cycle = cycle;
        }

        public long getETime() {
            return eTime;
        }

        public void setETime(long eTime) {
            this.eTime = eTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getJoinNum() {
            return joinNum;
        }

        public void setJoinNum(int joinNum) {
            this.joinNum = joinNum;
        }

        public String getLable() {
            return lable;
        }

        public void setLable(String lable) {
            this.lable = lable;
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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getRecommend() {
            return recommend;
        }

        public void setRecommend(int recommend) {
            this.recommend = recommend;
        }

        public long getSTime() {
            return sTime;
        }

        public void setSTime(long sTime) {
            this.sTime = sTime;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
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

        public int getTopNum() {
            return topNum;
        }

        public void setTopNum(int topNum) {
            this.topNum = topNum;
        }

        public String getWorkAddress() {
            return workAddress;
        }

        public void setWorkAddress(String workAddress) {
            this.workAddress = workAddress;
        }

        public String getWorkTime() {
            return workTime;
        }

        public void setWorkTime(String workTime) {
            this.workTime = workTime;
        }
    }

    public static class CompanyBean {
        /**
         * des : 啥都送
         * id : 1
         * logo : http://112.126.97.144:8081/part-time/20190726/20190726234450874a08e6.png
         * name : 饿了么
         * phone : 18899996666
         * qq : 11111234324
         * star : 4
         * wx : 92348732
         */

        private String des;
        private int id;
        private String logo;
        private String name;
        private String phone;
        private String qq;
        private int star;
        private String wx;

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public String getWx() {
            return wx;
        }

        public void setWx(String wx) {
            this.wx = wx;
        }
    }
}
