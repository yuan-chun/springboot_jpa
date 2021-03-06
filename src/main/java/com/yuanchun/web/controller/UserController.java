package com.yuanchun.web.controller;

import com.yuanchun.po.User;
import com.yuanchun.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findByUserName")
    @ResponseBody
    public User findByUserName( String userName){
        System.out.println("findByUserName user name"+userName);
        return userService.findByUserName(userName);
    }

    @GetMapping("/updateSome")
    @ResponseBody
    public void updateSome( String userName){
        userService.updateSome();
    }

    @GetMapping("/findLikeName")
    @ResponseBody
    public List<User> findLikeName(String userName){
        System.out.println("findByUserName user name"+userName);
        return userService.findLikeName(userName);
    }



    // 绑定变量名字和属性，参数封装进类
    @InitBinder("user")
    public void initBinderUser(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    @GetMapping("/findByPage")
    @ResponseBody
    public Page<User> findByPage(Integer page, Integer size,  @ModelAttribute("user")  User user){
        System.out.println("findByUserName user name="+user.getUserName());
        return userService.findCriteria(page,size,user);
//        return userService.findNoCriteria(page,size);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public List<User> findAll(User user){
        System.out.println("findByUserName user name="+user.getUserName());
//        return userService.findAll(user);
        return userService.findAll2(user);
    }


    @PutMapping("/save")
    @ResponseBody
    public User saveUser(@RequestBody User user){
        System.out.println("user name"+user.getUserName());
        userService.save(user);
        return user;
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
