package cn.com.oceansoft.sys.user.controller;

import cn.com.oceansoft.base.common.Result;
import cn.com.oceansoft.base.entity.BasePageResultEntity;
import cn.com.oceansoft.base.entity.BaseReqEntity;
import cn.com.oceansoft.base.util.UuidUtils;
import cn.com.oceansoft.sys.role.service.IRoleService;
import cn.com.oceansoft.sys.user.model.ReqUserInfoEntity;
import cn.com.oceansoft.sys.user.model.UserInfo;
import cn.com.oceansoft.sys.user.service.IUserService;
import com.sun.corba.se.impl.oa.NullServantImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by 念梓  on 2017/6/9.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
@Controller
@RequestMapping("sys/user")
public class UserController {

    private static final Logger log = LogManager.getLogger(UserController.class);

    @Resource
    private IUserService userService;

    @Resource
    private IRoleService roleService;

    @RequestMapping("index")
    public String index(){
        return "sys/user/index";
    }

    @RequestMapping("querypage")
    @ResponseBody
    public Object queryPage(@RequestBody ReqUserInfoEntity reqUserInfoEntity, HttpServletRequest request){
        log.debug("分页发起了请求");
        BasePageResultEntity<UserInfo> basePageResultEntity = userService.queryPage(reqUserInfoEntity);
        return basePageResultEntity;
    }


    @RequestMapping("newpage")
    public String newPage(String deptcode, String deptname, ModelMap model){
        model.put("deptcode",deptcode);
        model.put("deptname",deptname);
        model.put("roles",roleService.getAllRole());
        return "sys/user/new";
    }


    @RequestMapping("editpage")
    public String editPage(int id){
        return "sys/user/edit";
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(UserInfo userInfo){
        log.debug("新增用户");
        Result result = new Result();
        try{
            userService.save(userInfo);
        }catch (Exception ex){
            log.error("保存用户信息失败: ",ex);
            result.setCode("0001");
            result.setMsg(ex.getMessage());
        }
        return result;
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(UserInfo userInfo){
        return null;
    }


    @RequestMapping("delete")
    @ResponseBody
    public Object delete(int id){
        Result result = new Result();


        return null;
    }


    @RequestMapping("view")
    public String view(){
        return null;
    }




}
