package cn.com.oceansoft.sys.resource.model;

import cn.com.oceansoft.base.entity.BaseInfo;

/**
 * Created by 念梓  on 2017/6/8.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
public class ResourceInfo extends BaseInfo{

    private String code;


    private boolean checked;   //权限是否选择


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
