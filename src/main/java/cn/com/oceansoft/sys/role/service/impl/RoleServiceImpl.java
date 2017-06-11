package cn.com.oceansoft.sys.role.service.impl;

import cn.com.oceansoft.base.entity.BasePageReqEntity;
import cn.com.oceansoft.sys.role.dao.IRoleDao;
import cn.com.oceansoft.sys.role.model.RoleInfo;
import cn.com.oceansoft.sys.role.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ty on 2017/6/11.
 */
@Service
public class RoleServiceImpl implements IRoleService {


    @Resource
    private IRoleDao roleDao;

    @Override
    public RoleInfo queryObjectById(String id) {
        return null;
    }

    @Override
    public RoleInfo queryObjectById(int id) {
        return null;
    }

    @Override
    public List<RoleInfo> queryAll() {
        return null;
    }

    @Override
    public void save(RoleInfo obj) {

    }

    @Override
    public void deleteById(String uid) {

    }

    @Override
    public void deleteById(int uid) {

    }

    @Override
    public void update(RoleInfo roleInfo) {

    }

    @Override
    public int getTotalCount(RoleInfo roleInfo) {
        return 0;
    }

    @Override
    public List<RoleInfo> queryPage(BasePageReqEntity<RoleInfo> param) {
        return null;
    }
}
