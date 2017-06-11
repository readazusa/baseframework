package cn.com.oceansoft.sys.resource.service.impl;

import cn.com.oceansoft.base.entity.BasePageReqEntity;
import cn.com.oceansoft.sys.resource.dao.IResourceDao;
import cn.com.oceansoft.sys.resource.model.ResourceInfo;
import cn.com.oceansoft.sys.resource.service.IResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
