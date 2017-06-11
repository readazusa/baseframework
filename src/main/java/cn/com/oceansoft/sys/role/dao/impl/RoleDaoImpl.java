package cn.com.oceansoft.sys.role.dao.impl;

import cn.com.oceansoft.base.entity.BasePageReqEntity;
import cn.com.oceansoft.sys.role.dao.IRoleDao;
import cn.com.oceansoft.sys.role.model.RoleInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ty on 2017/6/11.
 */
@Repository
public class RoleDaoImpl extends SqlSessionDaoSupport implements IRoleDao {

    @Resource
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

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
