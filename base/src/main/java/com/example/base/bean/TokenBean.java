package com.example.base.bean;

/**
 * @author : sklyand
 * @email :
 * @time : 2019/7/25 11:37
 * @describe ：
 */
public class TokenBean {

    /**
     * status : string
     * message : string
     * token : string
     */

    private String status;
    private String message;
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
