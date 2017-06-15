package cn.com.oceansoft.sys.user.service.impl;

import cn.com.oceansoft.base.entity.BasePageReqEntity;
import cn.com.oceansoft.base.entity.BasePageResultEntity;
import cn.com.oceansoft.sys.user.dao.IUserDao;
import cn.com.oceansoft.sys.user.model.ReqUserInfoEntity;
import cn.com.oceansoft.sys.user.model.UserInfo;
import cn.com.oceansoft.sys.user.service.IUserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ty on 2017/6/11.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public UserInfo queryObjectById(String id) {
        return null;
    }

    @Override
    public UserInfo queryObjectById(int id) {
        return null;
    }

    @Override
    public List<UserInfo> queryAll() {
        return null;
    }

    @Override
    public void save(UserInfo obj) {

    }

    @Override
    public void deleteById(String uid) {

    }

    @Override
    public void deleteById(int uid) {

    }

    @Override
    public void update(UserInfo userInfo) {

    }

    @Override
    public int getTotalCount(UserInfo userInfo) {
        return userDao.getTotalCount(userInfo);

    }

    @Override
    public List<UserInfo> queryPage(BasePageReqEntity<UserInfo> param) {
        return userDao.queryPage(param);
    }


    @Override
    public BasePageResultEntity queryPage(ReqUserInfoEntity reqUserInfoEntity) {
        UserInfo userInfo = new UserInfo();
        userInfo.setDeptCode(reqUserInfoEntity.getDeptCode());
        BasePageReqEntity<UserInfo> basePageReqEntity = new BasePageReqEntity<>();
        basePageReqEntity.setObj(userInfo);
        basePageReqEntity.setLimit(reqUserInfoEntity.getLimit());
        basePageReqEntity.setOffset(reqUserInfoEntity.getOffset());
        List<UserInfo> list = queryPage(basePageReqEntity);
        int totalCount = getTotalCount(userInfo);
        BasePageResultEntity<UserInfo> basePageResultEntity = new BasePageResultEntity<>();
        basePageResultEntity.setTotal(totalCount);
        basePageResultEntity.setRows(list);
        return basePageResultEntity;
    }
}
