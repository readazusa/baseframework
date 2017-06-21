package cn.com.oceansoft.sys.user.service;

import cn.com.oceansoft.base.entity.BasePageReqEntity;
import cn.com.oceansoft.base.entity.BasePageResultEntity;
import cn.com.oceansoft.base.service.IService;
import cn.com.oceansoft.sys.user.model.ReqUserInfoEntity;
import cn.com.oceansoft.sys.user.model.UserInfo;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by ty on 2017/6/11.
 */
public interface IUserService extends IService<UserInfo> {

    public BasePageResultEntity queryPage(ReqUserInfoEntity reqUserInfoEntity);


    /**
     * 获取已经获取的角色 和 剩余的角色信息
     * @param userId
     * @return
     */
    public Map<String,Object> loadRoleAndHasRoleInfos(int userId);



}
