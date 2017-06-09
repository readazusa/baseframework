package cn.com.oceansoft.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 念梓  on 2017/6/8.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
@Controller
public class InitController {

    @RequestMapping("login")
    public String login(){
        return "application/login";
    }

    @RequestMapping("index")
    public String index(){
        return "application/index";
    }

}
