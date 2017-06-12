package cn.com.oceansoft.sys.dept.controller;

import cn.com.oceansoft.base.entity.BasePageResultEntity;
import cn.com.oceansoft.sys.dept.model.DeptInfo;
import cn.com.oceansoft.sys.dept.service.IDeptService;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
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
    public Object loadDeptByParentCode(@RequestBody JSONObject jsonObject) {
        List<DeptInfo> deptInfos = deptService.queryDeptInfosByParentCode(jsonObject.get("parentcode").toString());
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
    public String newPage(String parentCode) {
        return null;
    }

    @RequestMapping("add")
    @ResponseBody
    public Object doAdd(DeptInfo deptInfo) {
        return null;
    }

    @RequestMapping("editpage")
    public String editPage(int id) {
        return null;
    }

    @RequestMapping("update")
    @ResponseBody
    public Object doUpdate(DeptInfo deptInfo) {
        return null;
    }

    @RequestMapping("viewpage")
    public String viewPage(int id) {
        return null;
    }

    @RequestMapping("remove")
    @ResponseBody
    public Object remove(int id) {
        return null;
    }


}
