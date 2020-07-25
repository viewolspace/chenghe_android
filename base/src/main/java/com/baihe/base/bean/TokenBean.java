package com.baihe.base.bean;

/**
 * @author : sklyand
 * @email :
 * @time : 2019/7/25 11:37
 * @describe ：
 */
public class TokenBean {

    /**
     * message : ok
     * status : 0000
     * token : 18e1a486-8944-4a27-93b3-e5da587a0997
     */

    private String message;
    private String status;
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
