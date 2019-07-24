package murraco.model;

public enum LoginType {

    USERNAME_PASSWD(1, "用户名密码"),
    PHONE_CODE(2, "手机验证码"),
    OPEN_ACCOUNT(3, "微信openid"),
    ;

    private Integer code;
    private String desc;

    LoginType(Integer code, String desc){
        this.code=code;
        this.desc=desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
