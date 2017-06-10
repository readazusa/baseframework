package cn.com.oceansoft.sys.dept.controller;

import cn.com.oceansoft.base.util.UuidUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
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
@RequestMapping("sys/dept")
public class DeptController {

    private static  final Logger log = LogManager.getLogger(DeptController.class);


    @RequestMapping("index")
    public String index(){
        return "sys/dept/index";
    }


    @RequestMapping("depttree")
    @ResponseBody
    public Object deptTree(HttpServletRequest request){
        log.debug("接收前台的tree请求");

        Map<String,Object> map = new HashMap<>();

        List<Map<String,Object>> list = new ArrayList<>();

        for(int i=0;i<5;i++){
            Map<String,Object> map1 = new HashMap<>();
            map1.put("id",UuidUtils.getUpperUuid());
            map1.put("text","jstree"+i);
            map1.put("children",true);
            list.add(map1);
        }

        map.put("id",UuidUtils.getUpperUuid());
        map.put("text","新区城管局");
        map.put("children",list);


        return map;
    }




}
