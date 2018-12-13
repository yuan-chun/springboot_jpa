package com.yuanchun.service.impl;

import com.yuanchun.po.User;
import com.yuanchun.repo.UserRepository;
import com.yuanchun.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
