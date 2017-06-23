package cn.com.oceansoft.sys.resource.controller;

import cn.com.oceansoft.base.common.Result;
import cn.com.oceansoft.base.entity.BaseReqEntity;
import cn.com.oceansoft.base.util.ValidateCodeUtils;
import cn.com.oceansoft.sys.resource.model.ResourceInfo;
import cn.com.oceansoft.sys.resource.service.IResourceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
        return resourceService.queryPage(baseReqEntity);
    }

    @RequestMapping("newpage")
    public String newPage(){
        return "sys/resource/new";
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(ResourceInfo resourceInfo){
        Result result = new Result();
        try {
            resourceService.save(resourceInfo);
        }catch (Exception ex){
            log.error("保存权限信息失败: {}",ex);
            result.setCode("0001");
            result.setMsg(ex.getMessage());
        }
        return result;
    }


    @RequestMapping("edit")
    public String edit(int id, ModelMap model){
        ResourceInfo resourceInfo = resourceService.queryObjectById(id);
        model.put("resource",resourceInfo);

        return "sys/resource/edit";
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(ResourceInfo resourceInfo){
        Result result = new Result();
        try{
            resourceService.update(resourceInfo);
        }catch (Exception ex){
            log.error("更新权限失败: {}",ex);
            result.setCode("0001");
            result.setMsg(ex.getMessage());
        }
        return result;
    }

    @RequestMapping("view")
    public String view(int id,ModelMap model){
        ResourceInfo resourceInfo = resourceService.queryObjectById(id);
        model.put("resource",resourceInfo);
        return "sys/resource/view";
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object detete(int id){
        Result result = new Result();
        try{
            resourceService.deleteById(id);
        }catch (Exception ex){
            log.error("删除权限信息错误: ",ex);
            result.setCode("0001");
            result.setMsg(ex.getMessage());
        }
        return result;
    }


}
