package com.ch.base.bean;

/**
 * @author : sklyand
 * @email :
 * @time : 2019/7/25 11:38
 * @describe ：
 */
public class PhoneCodeBean {

    /**
     * status : string
     * message : string
     * rand : string
     */

    private String status;
    private String message;
    private String rand;

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

    public String getRand() {
        return rand;
    }

    public void setRand(String rand) {
        this.rand = rand;
    }
}
