package cn.com.oceansoft.sys.dept.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 念梓  on 2017/6/9.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
@RequestMapping("sys/dept")
public class DeptController {

    @RequestMapping("index")
    public String index(){
        return "sys/dept/index";
    }
}
