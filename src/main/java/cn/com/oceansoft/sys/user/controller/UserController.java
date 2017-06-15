package cn.com.oceansoft.sys.user.controller;

import cn.com.oceansoft.base.entity.BasePageResultEntity;
import cn.com.oceansoft.base.entity.BaseReqEntity;
import cn.com.oceansoft.base.util.UuidUtils;
import cn.com.oceansoft.sys.user.model.ReqUserInfoEntity;
import cn.com.oceansoft.sys.user.model.UserInfo;
import cn.com.oceansoft.sys.user.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
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
}
