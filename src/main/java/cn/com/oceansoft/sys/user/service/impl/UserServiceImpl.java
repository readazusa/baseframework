package cn.com.oceansoft.sys.user.service.impl;

import cn.com.oceansoft.base.entity.BasePageReqEntity;
import cn.com.oceansoft.sys.user.dao.IUserDao;
import cn.com.oceansoft.sys.user.model.UserInfo;
import cn.com.oceansoft.sys.user.service.IUserService;
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
        return 0;
    }

    @Override
    public List<UserInfo> queryPage(BasePageReqEntity<UserInfo> param) {
        return null;
    }
}
