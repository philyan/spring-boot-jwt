package murraco.model;

public enum CodeType {
    PHONE(1,"手机验证码"),
    MAIL(2,"邮箱验证码"),
    ;
    private int code;
    private String desc;
    CodeType(int code, String desc){
        this.code=code;
        this.desc=desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
