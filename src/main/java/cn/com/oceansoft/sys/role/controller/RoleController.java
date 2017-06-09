package cn.com.oceansoft.sys.role.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 念梓  on 2017/6/9.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
@Controller
@RequestMapping("sys/role")
public class RoleController {

    @RequestMapping("index")
    public String index(){
        return "sys/role/index";
    }

}
