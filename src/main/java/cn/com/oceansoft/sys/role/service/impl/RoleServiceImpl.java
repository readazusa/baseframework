package cn.com.oceansoft.sys.role.service.impl;

import cn.com.oceansoft.base.entity.BasePageReqEntity;
import cn.com.oceansoft.base.entity.BasePageResultEntity;
import cn.com.oceansoft.base.entity.BaseReqEntity;
import cn.com.oceansoft.base.util.IdWorkerUtils;
import cn.com.oceansoft.sys.resource.dao.IResourceDao;
import cn.com.oceansoft.sys.resource.model.ResourceInfo;
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

    @Resource
    private IResourceDao resourceDao;


    @Override
    public RoleInfo queryObjectById(String id) {
        return null;
    }

    @Override
    public RoleInfo queryObjectById(int id) {
        return roleDao.queryObjectById(id);
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

        List<Map<String, Object>> mapList =laodRoleResList(obj.getId(),obj.getRes());

        roleDao.batchSaveRoleVsResource(mapList);
    }

    private List<Map<String,Object>> laodRoleResList(int roleId,List<String> resIds){
        Date now = new Date();
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (resIds != null && resIds.size() > 0) {
            for (String code : resIds) {
                Map<String, Object> map = new HashMap<>();
                map.put("roleId",roleId);
                map.put("resId", code);
                map.put("createTime", now);
                mapList.add(map);
            }
        }
        return mapList;
    }



    @Override
    public void deleteById(String uid) {

    }

    @Transactional
    @Override
    public void deleteById(int uid) {
        roleDao.deleteById(uid);
        roleDao.deleteRoleVsResourceByRoleId(uid);
    }

    @Override
    public void update(RoleInfo roleInfo) {
        roleInfo.setUpdateTime(new Date());
        roleDao.update(roleInfo);
        roleDao.deleteRoleVsResourceByRoleId(roleInfo.getId());
        List<Map<String, Object>> mapList =laodRoleResList(roleInfo.getId(),roleInfo.getRes());
        roleDao.batchSaveRoleVsResource(mapList);
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
        roleInfo.setSearch(baseReqEntity.getSearch());
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

    @Override
    public List<ResourceInfo> queryCheckAndAllResourceInfos(int roleId) {
        List<ResourceInfo> resourceInfoList = resourceDao.queryAllRes();
        List<Integer> hasResourceIds = roleDao.queryResourceIdsByRoleId(roleId);


        for(ResourceInfo resourceInfo : resourceInfoList){
            if(hasResourceIds.contains(resourceInfo.getId())){
                resourceInfo.setChecked(true);
            }
        }
        return resourceInfoList;
    }
}
