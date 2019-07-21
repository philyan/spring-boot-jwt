package murraco.model;

public enum CodeStatus {
    SEND(0,"已发送"),
    VERIFIED(1,"验证成功"),
    EXPIRE(2,"失效"),
    ;
    private int code;
    private String desc;
    CodeStatus(int code, String desc){
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
