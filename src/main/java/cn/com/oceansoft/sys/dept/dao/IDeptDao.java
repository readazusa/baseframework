package cn.com.oceansoft.sys.dept.dao;

import cn.com.oceansoft.base.dao.IDao;
import cn.com.oceansoft.sys.dept.model.DeptInfo;

import java.util.List;

/**
 * Created by ty on 2017/6/11.
 */
public interface IDeptDao extends IDao<DeptInfo> {

    public List<DeptInfo> queryDeptInfosByParentCode(String parentCode);


    public int queryDeptCountByParentCode(String parentCode);


    /**
     * 根据部门父编码得到当前的部门编码
     * @param parentCode
     * @return
     */
    public String getCodeByParentCode(String parentCode);



    public List<DeptInfo> queryAllDeptInfos(DeptInfo deptInfo);
}
