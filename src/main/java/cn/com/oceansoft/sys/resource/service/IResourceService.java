package cn.com.oceansoft.sys.resource.service;

import cn.com.oceansoft.base.common.Result;
import cn.com.oceansoft.base.entity.BasePageResultEntity;
import cn.com.oceansoft.base.entity.BaseReqEntity;
import cn.com.oceansoft.base.service.IService;
import cn.com.oceansoft.sys.resource.model.ResourceInfo;

import java.util.List;

/**
 * Created by ty on 2017/6/11.
 */
public interface IResourceService extends IService<ResourceInfo> {

    public BasePageResultEntity queryPage(BaseReqEntity baseReqEntity);

    public List<ResourceInfo> queryAllRes();



}
