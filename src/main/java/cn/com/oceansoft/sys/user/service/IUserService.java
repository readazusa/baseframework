package cn.com.oceansoft.sys.user.service;

import cn.com.oceansoft.base.entity.BasePageReqEntity;
import cn.com.oceansoft.base.entity.BasePageResultEntity;
import cn.com.oceansoft.base.service.IService;
import cn.com.oceansoft.sys.user.model.ReqUserInfoEntity;
import cn.com.oceansoft.sys.user.model.UserInfo;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by ty on 2017/6/11.
 */
public interface IUserService extends IService<UserInfo> {

    public BasePageResultEntity queryPage(ReqUserInfoEntity reqUserInfoEntity);

}
