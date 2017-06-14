
package cn.com.oceansoft.base.common;



/**
 * 返回信息结果表
 */
public final class Result {
    public  static  String SUCCESS="0000";

    public static String ERROR="1111";

    private String code=SUCCESS;

    public Object data = null;

    private String msg="成功";

    public Result(){

    }
    public Result(String code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public Result(String code,Object obj){
        this.code=code;
        this.data=obj;
    }

    public Result(String code,String msg,Object obj){
        this(code,msg);
        this.data=obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
