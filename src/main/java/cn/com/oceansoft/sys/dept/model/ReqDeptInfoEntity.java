package cn.com.oceansoft.sys.dept.model;

import cn.com.oceansoft.base.entity.BaseReqEntity;

/**
 * Created by ty on 2017/6/23.
 */
public class ReqDeptInfoEntity extends BaseReqEntity {


    private String parentCode;


    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
