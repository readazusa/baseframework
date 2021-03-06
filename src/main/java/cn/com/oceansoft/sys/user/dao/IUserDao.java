package cn.com.oceansoft.sys.user.dao;

import cn.com.oceansoft.base.dao.IDao;
import cn.com.oceansoft.sys.user.model.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by ty on 2017/6/11.
 */
public interface IUserDao extends IDao<UserInfo> {

    public void batchSaveUserVsRole(List<Map<String,Object>> list);


    public void deleteUserVsRoleByUserId(int userId);

    /**
     * 获取用户关联的角色id
     * @param userId
     * @return
     */
    public List<Integer> queryUserVsRoleIdByUserId(int userId);
}
