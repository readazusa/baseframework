package cn.com.oceansoft.sys.dept.dao.impl;

import cn.com.oceansoft.base.entity.BasePageReqEntity;
import cn.com.oceansoft.sys.dept.dao.IDeptDao;
import cn.com.oceansoft.sys.dept.model.DeptInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ty on 2017/6/11.
 */
@Repository
public class DeptDaoImpl extends SqlSessionDaoSupport  implements IDeptDao {

    @Resource
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public DeptInfo queryObjectById(String id) {
        return null;
    }

    @Override
    public DeptInfo queryObjectById(int id) {
        return this.getSqlSession().selectOne("DeptInfo.queryObjectById",id);
    }

    @Override
    public List<DeptInfo> queryAll() {
        return null;
    }

    @Override
    public void save(DeptInfo obj) {
            this.getSqlSession().insert("DeptInfo.save",obj);
    }

    @Override
    public void deleteById(String uid) {

    }

    @Override
    public void deleteById(int uid) {
            this.getSqlSession().delete("DeptInfo.deleleById",uid);
    }

    @Override
    public void update(DeptInfo deptInfo) {
        this.getSqlSession().update("DeptInfo.update",deptInfo);
    }

    @Override
    public int getTotalCount(DeptInfo deptInfo) {
        return 0;
    }


    @Override
    public List<DeptInfo> queryPage(BasePageReqEntity<DeptInfo> param) {
        return null;
    }

    @Override
    public List<DeptInfo> queryDeptInfosByParentCode(String parentCode) {
        return this.getSqlSession().selectList("DeptInfo.queryDeptInfosByParentCode",parentCode);
    }

    @Override
    public int queryDeptCountByParentCode(String parentCode) {
        return this.getSqlSession().selectOne("DeptInfo.queryDeptCountByParentCode",parentCode);
    }


    @Override
    public String getCodeByParentCode(String parentCode) {
        return this.getSqlSession().selectOne("DeptInfo.getCodeByParentCode",parentCode);
    }

    @Override
    public List<DeptInfo> queryAllDeptInfos(DeptInfo deptInfo) {
        return this.getSqlSession().selectList("DeptInfo.queryAllDeptInfos",deptInfo);
    }
}
