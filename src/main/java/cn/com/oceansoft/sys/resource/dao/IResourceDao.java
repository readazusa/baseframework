package cn.com.oceansoft.sys.resource.dao;

import cn.com.oceansoft.base.common.Result;
import cn.com.oceansoft.base.dao.IDao;
import cn.com.oceansoft.sys.resource.model.ResourceInfo;

import java.util.List;

/**
 * Created by ty on 2017/6/11.
 */
public interface IResourceDao  extends IDao<ResourceInfo>{

    public List<ResourceInfo> queryAllRes();

}
