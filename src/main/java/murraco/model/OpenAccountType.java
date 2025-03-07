package murraco.model;

public enum OpenAccountType {
    WECHAT(1,"微信"),
    ALIPAY(2,"支付宝"),
    WEIBO(3,"微博"),
    ;
    private int code;
    private String desc;

    OpenAccountType(int code, String desc){
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
