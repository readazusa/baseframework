package cn.com.oceansoft.sys.role.service;

import cn.com.oceansoft.base.entity.BasePageResultEntity;
import cn.com.oceansoft.base.entity.BaseReqEntity;
import cn.com.oceansoft.base.service.IService;
import cn.com.oceansoft.sys.role.model.RoleInfo;

/**
 * Created by ty on 2017/6/11.
 */
public interface IRoleService extends IService<RoleInfo> {

    public BasePageResultEntity queryPage(BaseReqEntity baseReqEntity);


}
