package cn.com.oceansoft.sys.resource.controller;

import cn.com.oceansoft.base.entity.BaseReqEntity;
import cn.com.oceansoft.sys.resource.service.IResourceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by 念梓  on 2017/6/9.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
@Controller
@RequestMapping("sys/resource")
public class ResourceController {

    private static  final Logger log = LogManager.getLogger(ResourceController.class);


    @Resource
    private IResourceService resourceService;

    @RequestMapping("index")
    public String index(){
        return "sys/resource/index";
    }

    @RequestMapping("querypage")
    @ResponseBody
    public Object queryPage(@RequestBody BaseReqEntity baseReqEntity){

        return null;
    }

}
