package cn.com.oceansoft.sys.user.controller;

import cn.com.oceansoft.base.entity.BasePageResultEntity;
import cn.com.oceansoft.base.entity.BaseReqEntity;
import cn.com.oceansoft.base.util.UuidUtils;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("index")
    public String index(){
        return "sys/user/index";
    }

    @RequestMapping("querypage")
    @ResponseBody
    public Object queryPage(@RequestBody BaseReqEntity baseReqEntity, HttpServletRequest request){
        log.debug("分页发起了请求");
//        log.debug("offset: {}",request.getParameter("offset"));
//        log.debug("limit:{}",request.getParameter("limit"));
//        log.debug("name: {}",request.getParameter("name"));
        log.debug("baseReqEntity的基本数据: {}",baseReqEntity);
        List<Map> list = new ArrayList<>();
        for(int i=0;i<20;i++){
            Map<String,String> map = new HashMap<>();
            map.put("id", UuidUtils.getUpperUuid());
            map.put("name","你好");
            map.put("price","价格");
            list.add(map);
        }
        BasePageResultEntity basePageResultEntity = new BasePageResultEntity<Map>();
        basePageResultEntity.setRows(list);
        basePageResultEntity.setTotal(20);
        basePageResultEntity.setOffset(baseReqEntity.getOffset());
        return basePageResultEntity;
    }
}
