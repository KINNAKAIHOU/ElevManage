package com.scau.zwp.elevmanage.controller;

import com.scau.zwp.elevmanage.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/2/21
 */

@RestController
@Api(tags = "主页对象功能接口")
public class IndexController {

    @Resource
    private UserService userService;

    /**
     * 跳转主页
     */
    @RequestMapping({"/", "/index"})
    public String index(HttpSession session) {
        System.out.println(session);
        return "index";
    }

//    @RequestMapping("bye")
//    public String bye(){
//        return "redirect:/logout";
//    }
}
