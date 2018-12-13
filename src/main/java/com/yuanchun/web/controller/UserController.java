package com.yuanchun.web.controller;

import com.yuanchun.po.User;
import com.yuanchun.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findByUserName")
    @ResponseBody
    public User findByUserName(@RequestBody User user){
        return userService.findByUserName(user.getUserName());
    }

    @PutMapping("/save")
    @ResponseBody
    public void saveUser(@RequestBody User user){
        userService.save(user);
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        // 设置跳转的视图 默认映射到 src/main/resources/templates/{viewName}.html
        view.setViewName("index");
        // 设置属性
        view.addObject("title", "我的第一个WEB页面");
        view.addObject("desc", "欢迎进入Web系统");
        User author = new User();
        author.setHeight(184);
        author.setUserName("11111111111@qq.com");
        author.setPassword("1234");
        view.addObject("author", author);
        return view;
    }

    @GetMapping("/index1")
    public String index1(HttpServletRequest request) {
        // TODO 与上面的写法不同，但是结果一致。
        // 设置属性
        request.setAttribute("title", "我的第一个WEB页面");
        request.setAttribute("desc", "欢迎进入Luis-web 系统");
        User author = new User();
        author.setHeight(184);
        author.setUserName("11111111111@qq.com");
        author.setPassword("1234");
        request.setAttribute("author", author);
        // 返回的 index 默认映射到 src/main/resources/templates/xxxx.html
        return "index";
    }

}
