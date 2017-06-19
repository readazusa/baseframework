package cn.com.oceansoft.sys.role.model;

import cn.com.oceansoft.base.entity.BaseInfo;

import java.util.List;

/**
 * Created by 念梓  on 2017/6/8.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
public class RoleInfo extends BaseInfo{

    private String code;

    private List<String> res;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getRes() {
        return res;
    }

    public void setRes(List<String> res) {
        this.res = res;
    }
}
