package cn.com.oceansoft.sys.role.service.impl;

import cn.com.oceansoft.base.entity.BasePageReqEntity;
import cn.com.oceansoft.base.entity.BasePageResultEntity;
import cn.com.oceansoft.base.entity.BaseReqEntity;
import cn.com.oceansoft.base.util.IdWorkerUtils;
import cn.com.oceansoft.sys.role.dao.IRoleDao;
import cn.com.oceansoft.sys.role.model.RoleInfo;
import cn.com.oceansoft.sys.role.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(RoleInfo obj) {
        obj.setUid(IdWorkerUtils.instance.getId());
        obj.setCreateTime(new Date());
        obj.setUpdateTime(new Date());
        roleDao.save(obj);
        Date now = new Date();
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (obj.getRes() != null && obj.getRes().size() > 0) {
            for (String code : obj.getRes()) {
                Map<String, Object> map = new HashMap<>();
                map.put("roleId", obj.getId());
                map.put("resId", code);
                map.put("createTime", now);
                mapList.add(map);
            }
            roleDao.batchSaveRoleVsResource(mapList);
        }
    }

    @Override
    public void deleteById(String uid) {

    }

    @Override
    public void deleteById(int uid) {
        roleDao.deleteById(uid);
    }

    @Override
    public void update(RoleInfo roleInfo) {
        roleDao.update(roleInfo);
    }

    @Override
    public int getTotalCount(RoleInfo roleInfo) {
        return roleDao.getTotalCount(roleInfo);
    }

    @Override
    public List<RoleInfo> queryPage(BasePageReqEntity<RoleInfo> param) {
        return roleDao.queryPage(param);
    }


    @Override
    public BasePageResultEntity queryPage(BaseReqEntity baseReqEntity) {
        RoleInfo roleInfo = new RoleInfo();
        BasePageReqEntity basePageReqEntity = new BasePageReqEntity();
        basePageReqEntity.setObj(roleInfo);
        basePageReqEntity.setLimit(baseReqEntity.getLimit());
        basePageReqEntity.setOffset(baseReqEntity.getOffset());
        List<RoleInfo> roleInfos = queryPage(basePageReqEntity);
        int totalCount = getTotalCount(roleInfo);
        BasePageResultEntity basePageResultEntity = new BasePageResultEntity();
        basePageResultEntity.setTotal(totalCount);
        basePageResultEntity.setRows(roleInfos);
        return basePageResultEntity;
    }


    @Override
    public List<RoleInfo> getAllRole() {
        return roleDao.queryAllRole();
    }
}
