package cn.com.oceansoft.sys.dept.service;

import cn.com.oceansoft.base.service.IService;
import cn.com.oceansoft.sys.dept.model.DeptInfo;
import cn.com.oceansoft.sys.dept.model.ReqDeptInfoEntity;

import java.util.List;

/**
 * Created by ty on 2017/6/11.
 */
public interface IDeptService extends IService<DeptInfo> {

    public List<DeptInfo> queryDeptInfosByParentCode(ReqDeptInfoEntity reqDeptInfoEntity);

    public Object loadDeptByParentCode(String parentCode);


    public String createCodeByParentCode(String parentCode);

}
