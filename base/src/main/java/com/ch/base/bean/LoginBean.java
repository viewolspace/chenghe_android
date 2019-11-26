package com.ch.base.bean;

/**
 * Created by zdy On 2019/7/26.
 */
public class LoginBean {

    /**
     * status : string
     * message : string
     * result : {"userId":0,"phone":"string","pwd":"string","nickName":"string","idfa":"string","headPic":"string","getcTime":"2019-07-26T11:04:39.312Z","getmTime":"2019-07-26T11:04:39.313Z","realName":"string","sex":0,"birthday":"2019-07-26T11:04:39.313Z","exp":"string","des":"string"}
     */

    private String status;
    private String message;
    private UserInfo result;

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

    public UserInfo getResult() {
        return result;
    }

    public void setResult(UserInfo result) {
        this.result = result;
    }

    public static class UserInfo {
        /**
         * userId : 0
         * phone : string
         * pwd : string
         * nickName : string
         * idfa : string
         * headPic : string
         * getcTime : 2019-07-26T11:04:39.312Z
         * getmTime : 2019-07-26T11:04:39.313Z
         * realName : string
         * sex : 0
         * birthday : 2019-07-26T11:04:39.313Z
         * exp : string
         * des : string
         */

        private int userId;
        private String phone;
        private String pwd;
        private String nickName;
        private String idfa;
        private String headPic;
        private String getcTime;
        private String getmTime;
        private String realName;
        private int sex;
        private String birthday;
        private String exp;
        private String des;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getIdfa() {
            return idfa;
        }

        public void setIdfa(String idfa) {
            this.idfa = idfa;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
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

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getExp() {
            return exp;
        }

        public void setExp(String exp) {
            this.exp = exp;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }
}
