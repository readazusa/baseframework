package cn.com.oceansoft.sys.dept.controller;

import cn.com.oceansoft.base.common.Result;
import cn.com.oceansoft.base.entity.BasePageResultEntity;
import cn.com.oceansoft.sys.dept.model.DeptInfo;
import cn.com.oceansoft.sys.dept.model.ReqDeptInfoEntity;
import cn.com.oceansoft.sys.dept.service.IDeptService;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("sys/dept")
public class DeptController {

    private static final Logger log = LogManager.getLogger(DeptController.class);

    @Resource
    private IDeptService deptService;

    @RequestMapping("index")
    public String index() {
        return "sys/dept/index";
    }


    @RequestMapping("loaddepttreebyparent")
    @ResponseBody
    public Object loadDeptTreeByParent(@RequestParam(value = "parentcode") String parentCode) {
        log.debug("parentcode： {}", parentCode);
        return deptService.loadDeptByParentCode(parentCode);
    }


    @RequestMapping("loaddeptbyparentcode")
    @ResponseBody
    public Object loadDeptByParentCode(@RequestBody ReqDeptInfoEntity reqDeptInfoEntity) {
        List<DeptInfo> deptInfos = deptService.queryDeptInfosByParentCode(reqDeptInfoEntity);
        BasePageResultEntity basePageResultEntity = new BasePageResultEntity<DeptInfo>();
        basePageResultEntity.setRows(deptInfos);
        basePageResultEntity.setTotal(deptInfos.size());
        return basePageResultEntity;
    }

    @RequestMapping("depttree")
    @ResponseBody
    public Object deptTree(HttpServletRequest request) {
        log.debug("接收前台的tree请求");
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @RequestMapping("newpage")
    public String newPage(@RequestParam(value ="parentcode") String parentCode, ModelMap model) {
        model.put("parentCode",parentCode);
        model.put("code",deptService.createCodeByParentCode(parentCode));
        return "sys/dept/new";
    }

    @RequestMapping("add")
    @ResponseBody
    public Object doAdd(DeptInfo deptInfo) {
        Result result = new Result();
        try{
            deptService.save(deptInfo);
        }catch (Exception ex){
           log.error("保存部门信息失败: {}",ex);
            result.setCode("0001");
            result.setMsg(ex.getMessage());
        }
        return result;
    }

    @RequestMapping("editpage")
    public String editPage(int id, ModelMap model) {
        DeptInfo deptInfo= deptService.queryObjectById(id);
        model.put("deptInfo",deptInfo);
        return "sys/dept/edit";
    }

    @RequestMapping("update")
    @ResponseBody
    public Object doUpdate(DeptInfo deptInfo) {
        Result result = new Result();
        try{
            deptService.update(deptInfo);
        }catch (Exception ex){
            log.error("更新部门信息失败,失败信息: ",ex);
            result.setCode("0001");
            result.setMsg(ex.getMessage());
        }
        return result;
    }

    @RequestMapping("viewpage")
    public String viewPage(int id,ModelMap model) {
        DeptInfo deptInfo= deptService.queryObjectById(id);
        model.put("deptInfo",deptInfo);
        return "sys/dept/view";
    }

    @RequestMapping("remove")
    @ResponseBody
    public Object remove(int id) {
        Result result = new Result();
        try{
            deptService.deleteById(id);
        }catch (Exception ex){
            result.setCode("0001");
            result.setMsg(ex.getMessage());
        }

        return result;
    }


    @RequestMapping("tree")
    public String tree(){
        return "sys/dept/tree";
    }
}
