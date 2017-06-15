package cn.com.oceansoft.sys.user.model;

import cn.com.oceansoft.base.entity.BaseReqEntity;

/**
 * Created by ty on 2017/6/15.
 */
public class ReqUserInfoEntity extends BaseReqEntity {


    private String deptCode;

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
}
