package cn.com.oceansoft.sys.dept.service.impl;

import cn.com.oceansoft.base.entity.BasePageReqEntity;
import cn.com.oceansoft.sys.dept.dao.IDeptDao;
import cn.com.oceansoft.sys.dept.model.DeptInfo;
import cn.com.oceansoft.sys.dept.service.IDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ty on 2017/6/11.
 */
@Service
public class DeptServiceImpl implements IDeptService {

//    private static final String JSTREE_ROOT = "#";

    private static final String ROOT = "root";


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

    @Override
    public Object loadDeptByParentCode(String parentCode) {
        if (ROOT.equals(parentCode)) {
            Map<String, Object> rootMap = new HashMap<>();
            List<DeptInfo> deptInfos = deptDao.queryDeptInfosByParentCode(ROOT);
            DeptInfo deptInfo = deptInfos.get(0);
            rootMap.put("id", deptInfo.getCode());
            rootMap.put("text", deptInfo.getName());
            List<DeptInfo> childerDeptInfos = deptDao.queryDeptInfosByParentCode(deptInfo.getCode());
            List<Map<String, Object>> childrenDeptInfoTree = loadChildrenDeptInfoTree(childerDeptInfos);
            rootMap.put("children",childrenDeptInfoTree);
            return rootMap;
        } else {
            List<DeptInfo> childerDeptInfos = deptDao.queryDeptInfosByParentCode(parentCode);
            List<Map<String, Object>> childrenDeptInfoList = loadChildrenDeptInfoTree(childerDeptInfos);
            return childrenDeptInfoList;
        }
    }

    private List<Map<String,Object>> loadChildrenDeptInfoTree( List<DeptInfo> childerDeptInfos){
        List<Map<String, Object>> childrenDeptInfoTree = new ArrayList<>();
        if (childerDeptInfos != null && childerDeptInfos.size() > 0) {
            childerDeptInfos.forEach(e -> {
                Map<String,Object> map = new HashMap<>();
                map.put("id",e.getCode());
                map.put("text",e.getName());
                int childrenCount = deptDao.queryDeptCountByParentCode(e.getCode());
                if(childrenCount>0){
                    map.put("children",true);
                }else{
                    map.put("children",false);
                }
                childrenDeptInfoTree.add(map);
            });
        }
        return childrenDeptInfoTree;
    }





}
