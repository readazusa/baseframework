package cn.com.oceansoft.sys.user.service.impl;

import cn.com.oceansoft.base.entity.BasePageReqEntity;
import cn.com.oceansoft.base.entity.BasePageResultEntity;
import cn.com.oceansoft.base.util.IdWorkerUtils;
import cn.com.oceansoft.sys.user.dao.IUserDao;
import cn.com.oceansoft.sys.user.model.ReqUserInfoEntity;
import cn.com.oceansoft.sys.user.model.UserInfo;
import cn.com.oceansoft.sys.user.service.IUserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.mail.MailParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by ty on 2017/6/11.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public UserInfo queryObjectById(String id) {
        return null;
    }

    @Override
    public UserInfo queryObjectById(int id) {
        return null;
    }

    @Override
    public List<UserInfo> queryAll() {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(UserInfo obj) {
        obj.setUid(IdWorkerUtils.instance.getId());
        obj.setCreateTime(new Date());
        obj.setUpdateTime(new Date());
        userDao.save(obj);
        String[] roleIds = obj.getRoleIds().split(",");
        List<Map<String,Object>> mapList = new ArrayList<>();
        Date now = new Date();
        if(roleIds != null && roleIds.length>0){
            for(String roleId : roleIds){
                Map<String,Object> map = new HashMap<>();
                map.put("userId",obj.getId());
                map.put("roleId",roleId);
                map.put("createTime",now);
                mapList.add(map);
            }
            userDao.batchSaveUserVsRole(mapList);
        }
    }

    @Override
    public void deleteById(String uid) {

    }

    @Override
    public void deleteById(int uid) {

    }

    @Override
    public void update(UserInfo userInfo) {

    }

    @Override
    public int getTotalCount(UserInfo userInfo) {
        return userDao.getTotalCount(userInfo);

    }

    @Override
    public List<UserInfo> queryPage(BasePageReqEntity<UserInfo> param) {
        return userDao.queryPage(param);
    }


    @Override
    public BasePageResultEntity queryPage(ReqUserInfoEntity reqUserInfoEntity) {
        UserInfo userInfo = new UserInfo();
        userInfo.setDeptCode(reqUserInfoEntity.getDeptCode());
        BasePageReqEntity<UserInfo> basePageReqEntity = new BasePageReqEntity<>();
        basePageReqEntity.setObj(userInfo);
        basePageReqEntity.setLimit(reqUserInfoEntity.getLimit());
        basePageReqEntity.setOffset(reqUserInfoEntity.getOffset());
        List<UserInfo> list = queryPage(basePageReqEntity);
        int totalCount = getTotalCount(userInfo);
        BasePageResultEntity<UserInfo> basePageResultEntity = new BasePageResultEntity<>();
        basePageResultEntity.setTotal(totalCount);
        basePageResultEntity.setRows(list);
        return basePageResultEntity;
    }
}
