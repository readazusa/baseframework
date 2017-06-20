package cn.com.oceansoft.sys.user.dao.impl;

import cn.com.oceansoft.base.entity.BasePageReqEntity;
import cn.com.oceansoft.sys.user.dao.IUserDao;
import cn.com.oceansoft.sys.user.model.UserInfo;
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
public class UserDaoImpl extends SqlSessionDaoSupport implements IUserDao {


    @Resource
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

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
        this.getSqlSession().insert("UserInfo.save", obj);
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
        return this.getSqlSession().selectOne("UserInfo.getTotalCount", userInfo);
    }

    @Override
    public List<UserInfo> queryPage(BasePageReqEntity<UserInfo> param) {
        return this.getSqlSession().selectList("UserInfo.queryPage", param);
    }


    @Override
    public void batchSaveUserVsRole(List<Map<String, Object>> list) {
        this.getSqlSession().insert("UserInfo.batchSaveUserVsRole", list);
    }


    @Override
    public void deleteUserVsRoleByUserId(int userId) {

    }
}
