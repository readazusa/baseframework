package cn.com.oceansoft.sys.resource.service.impl;

import cn.com.oceansoft.base.common.Result;
import cn.com.oceansoft.base.entity.BasePageReqEntity;
import cn.com.oceansoft.base.entity.BasePageResultEntity;
import cn.com.oceansoft.base.entity.BaseReqEntity;
import cn.com.oceansoft.base.util.IdWorkerUtils;
import cn.com.oceansoft.sys.resource.dao.IResourceDao;
import cn.com.oceansoft.sys.resource.model.ResourceInfo;
import cn.com.oceansoft.sys.resource.service.IResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by ty on 2017/6/11.
 */
@Service
public class ResourceServiceImpl implements IResourceService {

    @Resource
    private IResourceDao resourceDao;


    @Override
    public ResourceInfo queryObjectById(String id) {
        return null;
    }

    @Override
    public ResourceInfo queryObjectById(int id) {
        return resourceDao.queryObjectById(id);
    }

    @Override
    public List<ResourceInfo> queryAll() {
        return null;
    }

    @Override
    public void save(ResourceInfo obj) {
        obj.setUid(IdWorkerUtils.instance.getId());
        obj.setCreateTime(new Date());
        obj.setUpdateTime(new Date());
        resourceDao.save(obj);
    }

    @Override
    public void deleteById(String uid) {

    }

    @Override
    public void deleteById(int uid) {
        resourceDao.deleteById(uid);
    }

    @Override
    public void update(ResourceInfo resourceInfo) {
        resourceInfo.setUpdateTime(new Date());
        resourceDao.update(resourceInfo);
    }

    @Override
    public int getTotalCount(ResourceInfo resourceInfo) {
        return resourceDao.getTotalCount(resourceInfo);
    }

    @Override
    public List<ResourceInfo> queryPage(BasePageReqEntity<ResourceInfo> param) {
        return resourceDao.queryPage(param);
    }

    @Override
    public BasePageResultEntity queryPage(BaseReqEntity baseReqEntity) {
        ResourceInfo resourceInfo = new ResourceInfo();
        BasePageReqEntity basePageReqEntity = new BasePageReqEntity();
        basePageReqEntity.setObj(resourceInfo);
        basePageReqEntity.setOffset(baseReqEntity.getOffset());
        basePageReqEntity.setLimit(baseReqEntity.getLimit());
        List<ResourceInfo> resourceInfoList = queryPage(basePageReqEntity);
        int totalCount = getTotalCount(resourceInfo);
        BasePageResultEntity basePageResultEntity = new BasePageResultEntity();
        basePageResultEntity.setRows(resourceInfoList);
        basePageResultEntity.setTotal(totalCount);
        return basePageResultEntity;
    }

    @Override
    public List<ResourceInfo> queryAllRes() {
        return resourceDao.queryAllRes();
    }




}
