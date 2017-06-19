package cn.com.oceansoft.sys.role.dao;

import cn.com.oceansoft.base.dao.IDao;
import cn.com.oceansoft.sys.role.model.RoleInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by ty on 2017/6/11.
 */
public interface IRoleDao extends IDao<RoleInfo> {

    /**
     * 批量保存角色用户信息
     * @param list
     */
    public void batchSaveRoleVsResource(List<Map<String,Object>> list);


    public void deleteRoleVsResourceByRoleId(int id);
}
