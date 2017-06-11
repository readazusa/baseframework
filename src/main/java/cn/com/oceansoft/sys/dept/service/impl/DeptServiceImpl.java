package cn.com.oceansoft.sys.dept.service.impl;

import cn.com.oceansoft.base.entity.BasePageReqEntity;
import cn.com.oceansoft.sys.dept.dao.IDeptDao;
import cn.com.oceansoft.sys.dept.model.DeptInfo;
import cn.com.oceansoft.sys.dept.service.IDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ty on 2017/6/11.
 */
@Service
public class DeptServiceImpl implements IDeptService {

    @Resource
    private IDeptDao deptDao;

    @Override
    public DeptInfo queryObjectById(String id) {
        return null;
    }

    @Override
    public DeptInfo queryObjectById(int id) {
        return null;
    }

    @Override
    public List<DeptInfo> queryAll() {
        return null;
    }

    @Override
    public void save(DeptInfo obj) {

    }

    @Override
    public void deleteById(String uid) {

    }

    @Override
    public void deleteById(int uid) {

    }

    @Override
    public void update(DeptInfo deptInfo) {

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
        return null;
    }
}
