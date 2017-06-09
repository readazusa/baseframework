package cn.com.oceansoft.base.shiro.dao;



import cn.com.oceansoft.sys.resource.model.ResourceInfo;
import cn.com.oceansoft.sys.role.model.RoleInfo;
import cn.com.oceansoft.sys.user.model.UserInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Map;

/**
 * net.sunmingchun.www.shiro.dao
 * Created by smc
 * date on 2016/2/28.
 * Email:sunmch@163.com
 * 用户认证与授权dao
 */
//@Repository
public class AuthorizationDao extends SqlSessionDaoSupport {

//    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public UserInfo getValidateUser(Map<String,String> map){
        return this.getSqlSession().selectOne("ShiroAuth.validateUser",map);
    }

    public List<RoleInfo> queryAuthRolesByUserId(String userId){
        return this.getSqlSession().selectList("ShiroAuth.queryAuthRolesByUserId",userId);
    }

    public List<ResourceInfo> queryAuthResourcesByUserId(String userId){
        return  this.getSqlSession().selectList("ShiroAuth.queryAuthResourcesByUserId",userId);
    }


}
