package com.yuanchun.service.inter;

import com.yuanchun.po.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {


    User findByUserName(String userName);

    void save(User user);

    List<User> findLikeName(String userName);

    Page<User> findNoCriteria(Integer page, Integer size) ;

    Page<User> findCriteria(Integer page, Integer size, User user);
}
