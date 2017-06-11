package cn.com.oceansoft.sys.resource.dao.impl;

import cn.com.oceansoft.base.entity.BasePageReqEntity;
import cn.com.oceansoft.sys.resource.dao.IResourceDao;
import cn.com.oceansoft.sys.resource.model.ResourceInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ty on 2017/6/11.
 */
@Repository
public class ResourceDaoImpl extends SqlSessionDaoSupport implements IResourceDao {

    @Resource
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public ResourceInfo queryObjectById(String id) {
        return null;
    }

    @Override
    public ResourceInfo queryObjectById(int id) {
        return null;
    }

    @Override
    public List<ResourceInfo> queryAll() {
        return null;
    }

    @Override
    public void save(ResourceInfo obj) {

    }

    @Override
    public void deleteById(String uid) {

    }

    @Override
    public void deleteById(int uid) {

    }

    @Override
    public void update(ResourceInfo resourceInfo) {

    }

    @Override
    public int getTotalCount(ResourceInfo resourceInfo) {
        return 0;
    }

    @Override
    public List<ResourceInfo> queryPage(BasePageReqEntity<ResourceInfo> param) {
        return null;
    }
}
