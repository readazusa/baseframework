package cn.com.oceansoft.base.shiro.realm;


import cn.com.oceansoft.base.service.cache.BaseCacheService;
import cn.com.oceansoft.sys.resource.model.ResourceInfo;
import cn.com.oceansoft.sys.role.model.RoleInfo;
import cn.com.oceansoft.sys.user.model.UserInfo;
import cn.com.oceansoft.base.shiro.dao.AuthorizationDao;
import cn.com.oceansoft.base.shiro.token.MyUsernamePasswordToken;
import cn.com.oceansoft.base.util.CodeConstantUtils;
import cn.com.oceansoft.base.util.DESUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.util.*;

/**
 * net.sunmingchun.www.shiro.realm
 * Created by smc
 * date on 2016/2/25.
 * Email:sunmch@163.com
 */
public class MyAuthorizationRealm extends AuthorizingRealm {

    private static final Logger log = LogManager.getLogger(MyAuthorizationRealm.class);

    private static final String ROLE_KEY = "role_";

    private static final String RES_KEY = "res_";

    @Resource
    private AuthorizationDao authorizationDao;

    @Resource
    private BaseCacheService baseCacheService;


    private Map<String, Collection<String>> userRolesMap = new HashMap<>();  //key表示方式:role_userid

    private Map<String, Collection<String>> userResMap = new HashMap<>();  //key表示: res_userid

    /**
     * 全部的授权走这个方法判断数据
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.debug("开始获取权限认证");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
        String ress = baseCacheService.get(RES_KEY + userInfo.getId());
        String roles = baseCacheService.get(ROLE_KEY + userInfo.getId());
        if (StringUtils.isBlank(ress)) {
            insertResMap(userInfo);
            baseCacheService.set(RES_KEY + userInfo.getId(), JSON.toJSONString(userResMap.get(RES_KEY + userInfo.getId())));
        } else {
            if(userResMap.get(RES_KEY + userInfo.getId())== null || userResMap.get(RES_KEY + userInfo.getId()).size()<=0){
                userResMap.put(RES_KEY + userInfo.getId(), JSON.parseArray(ress,String.class));
            }
        }
        if (StringUtils.isBlank(roles)) {
            insertRoleMap(userInfo);
            baseCacheService.set(ROLE_KEY + userInfo.getId(), JSON.toJSONString(userRolesMap.get(ROLE_KEY + userInfo.getId())));
        } else {
            if(userRolesMap.get(userInfo.getId()) == null || userRolesMap.get(userInfo.getId()).size()<=0){
                userRolesMap.put(ROLE_KEY + userInfo.getId(), JSON.parseArray(roles,String.class));
            }
        }
        simpleAuthorizationInfo.addRoles(userRolesMap.get(ROLE_KEY + userInfo.getId()));
        simpleAuthorizationInfo.addStringPermissions(userResMap.get(RES_KEY + userInfo.getId()));
        return simpleAuthorizationInfo;
    }

    private void insertRoleMap(UserInfo userInfo) {
        if(userRolesMap.get(userInfo.getId()) == null || userRolesMap.get(userInfo.getId()).size()<=0){
            Map<String, Collection<String>> roleMap = getCollectionRoles(userInfo.getId());
            Collection<String> collection = new ArrayList<>();
            collection.addAll(roleMap.get("roleNames"));
            collection.addAll(roleMap.get("roleCodes"));
            userRolesMap.put(ROLE_KEY + userInfo.getId(), collection);
        }
    }

    private void insertResMap(UserInfo userInfo) {
        if(userResMap.get(RES_KEY + userInfo.getId())== null || userResMap.get(RES_KEY + userInfo.getId()).size()<=0){
            Map<String, Collection<String>> resMap = getCollectionRes(userInfo.getId());
            Collection<String> collection = new ArrayList<>();
            collection.addAll(resMap.get("resNames"));
            collection.addAll(resMap.get("resCodes"));
            userResMap.put(RES_KEY + userInfo.getId(), collection);
        }
    }

    private Map<String, Collection<String>> getCollectionRoles(int userId) {
        Map<String, Collection<String>> targetRoleMap = new HashMap<>();
        List<RoleInfo> rolePOList = authorizationDao.queryAuthRolesByUserId(userId);
        List<String> roleCodes = new ArrayList<>(10);
        List<String> roleNames = new ArrayList<>(10);
        if (rolePOList != null && rolePOList.size() > 0) {
            for (RoleInfo roleInfo : rolePOList) {
                roleCodes.add(roleInfo.getCode());
                roleNames.add(roleInfo.getName());
            }
        }
        targetRoleMap.put("roleNames", roleNames);
        targetRoleMap.put("roleCodes", roleCodes);
        return targetRoleMap;
    }

    private Map<String, Collection<String>> getCollectionRes(int userId) {
        List<ResourceInfo> resourcePOList = authorizationDao.queryAuthResourcesByUserId(userId);
        Map<String, Collection<String>> targetResMap = new HashMap<>();
        List<String> resCodeList = new ArrayList<>(50);
        List<String> resNameList = new ArrayList<>(50);
        if (null != resourcePOList && resourcePOList.size() > 0) {
            for (ResourceInfo resourceInfo : resourcePOList) {
                resCodeList.add(resourceInfo.getCode());
                resNameList.add(resourceInfo.getName());
            }
        }
        targetResMap.put("resNames", resNameList);
        targetResMap.put("resCodes", resCodeList);
        return targetResMap;
    }


    /**
     * 认证用户的真实性
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        MyUsernamePasswordToken usernamePasswordToken = (MyUsernamePasswordToken) token;
        String password = new String(usernamePasswordToken.getPassword());
        String username = usernamePasswordToken.getUsername();
        String validatecode = usernamePasswordToken.getValidatecode();
        Session session = getSession();
        String sessionValidateCode = (String) session.getAttribute(CodeConstantUtils.VALIDATE_CODE);
        if (validatecode == null) {
            session.setAttribute("error", "验证码不能为空");
            throw new AuthenticationException("验证码不能为空");
        } else {
            if (!validatecode.equalsIgnoreCase(sessionValidateCode)) {
                session.setAttribute("error", "您输入的验证码不正确");
                throw new AuthenticationException("验证码不能为空");
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("condition", username);
        map.put("password", DESUtils.encrypt(password));
        UserInfo userInfo = authorizationDao.getValidateUser(map);
        if (null == userInfo) {
            session.setAttribute("error", "用户名或密码不正确");
            throw new AuthenticationException("用户名或密码不正确");
        }
        clearCache(userInfo);
        setUserSession(userInfo);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userInfo, password, username);
        return simpleAuthenticationInfo;
    }

    private void setUserSession(UserInfo userInfo) {
        Session session = getSession();
        session.setAttribute(CodeConstantUtils.USER_INFO, userInfo);
    }

    public Session getSession() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return session;
    }


    private void clearCache(UserInfo userInfo){
        userResMap.remove(RES_KEY + userInfo.getId());
        userRolesMap.remove(ROLE_KEY + userInfo.getId());
        baseCacheService.clear(RES_KEY + userInfo.getId());
        baseCacheService.clear(ROLE_KEY + userInfo.getId());
    }
}


