package cn.com.oceansoft.sys.role.dao.impl;

import cn.com.oceansoft.base.entity.BasePageReqEntity;
import cn.com.oceansoft.sys.role.dao.IRoleDao;
import cn.com.oceansoft.sys.role.model.RoleInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
        this.getSqlSession().insert("RoleInfo.save",obj);
    }

    @Override
    public void deleteById(String uid) {

    }

    @Override
    public void deleteById(int uid) {
        this.getSqlSession().delete("RoleInfo.deleteById",uid);
    }

    @Override
    public void update(RoleInfo roleInfo) {
        this.getSqlSession().update("RoleInfo.update",roleInfo);
    }

    @Override
    public int getTotalCount(RoleInfo roleInfo) {
        return this.getSqlSession().selectOne("RoleInfo.getTotalCount",roleInfo);
    }

    @Override
    public List<RoleInfo> queryPage(BasePageReqEntity<RoleInfo> param) {
        return this.getSqlSession().selectList("RoleInfo.queryPage",param);
    }

    @Override
    public void batchSaveRoleVsResource(List<Map<String, Object>> list) {
        this.getSqlSession().insert("RoleInfo.batchSaveRoleVsResource",list);
    }

    @Override
    public void deleteRoleVsResourceByRoleId(int id) {

    }

    @Override
    public List<RoleInfo> queryAllRole(){
        return this.getSqlSession().selectList("RoleInfo.queryAllRole");
    }
}
