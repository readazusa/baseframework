package cn.com.oceansoft.sys.role.controller;

import cn.com.oceansoft.base.common.Result;
import cn.com.oceansoft.base.entity.BaseReqEntity;
import cn.com.oceansoft.sys.role.model.RoleInfo;
import cn.com.oceansoft.sys.role.service.IRoleService;
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
@RequestMapping("sys/role")
public class RoleController {

    private static final Logger log = LogManager.getLogger(RoleController.class);

    @Resource
    private IRoleService roleService;

    @RequestMapping("index")
    public String index() {
        return "sys/role/index";
    }


    @RequestMapping("querypage")
    @ResponseBody
    public Object queryPage(@RequestBody BaseReqEntity baseReqEntity) {
        return roleService.queryPage(baseReqEntity);
    }

    @RequestMapping("newpage")
    public String newPage(ModelMap model) {
        return "sys/role/new";
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(RoleInfo roleInfo) {
        Result result = new Result();
        try {
            roleService.save(roleInfo);
        } catch (Exception ex) {
            log.error("保存角色失败: ", ex);
            result.setMsg(ex.getMessage());
            result.setCode("0001");
        }
        return result;
    }

    @RequestMapping("edit")
    public String edit(int id, ModelMap model) {
        return "sys/role/edit";
    }


    public Object update(RoleInfo roleInfo) {
        Result result = new Result();
        try {
            roleService.update(roleInfo);
        } catch (Exception ex) {
            result.setCode("0001");
            result.setMsg(ex.getMessage());
        }
        return result;
    }


    @RequestMapping("view")
    public String view(int id, ModelMap model) {
        return "sys/role/view";
    }


    @RequestMapping("delete")
    @ResponseBody
    public Object delete(int id) {
        Result result = new Result();
        try {
            roleService.deleteById(id);
        } catch (Exception ex) {
            log.error("删除角色失败: ", ex);
        }
        return result;
    }

}
