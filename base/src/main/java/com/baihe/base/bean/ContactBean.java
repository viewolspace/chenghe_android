package com.baihe.base.bean;

/**
 * @author : sklyand
 * @email :
 * @time : 2020/5/22 15:59
 * @describe ：
 */
public class ContactBean {

    /**
     * qq : 2206388328
     * message : ok
     * showMsg : 商务合作请联系QQ：2206388328
     * status : 0000
     */

    private String qq;
    private String message;
    private String showMsg;
    private String status;

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getShowMsg() {
        return showMsg;
    }

    public void setShowMsg(String showMsg) {
        this.showMsg = showMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
