package com.yuanchun.service.inter;

import com.yuanchun.po.User;

import java.util.List;

public interface UserService {


    User findByUserName(String userName);

    void save(User user);

    List<User> findLikeName(String userName);
}
