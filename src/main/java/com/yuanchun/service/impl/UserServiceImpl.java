package com.yuanchun.service.impl;

import com.yuanchun.po.User;
import com.yuanchun.repo.UserRepository;
import com.yuanchun.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
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
    public List<User> findLikeName(String userName) {
        List<User> list = userRepository.findByUserNameLike(userName);
        list.addAll(userRepository.selectByName(userName));
        return list;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Page<User> findNoCriteria(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findCriteria(Integer page, Integer size, final User user) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        Specification<User> spec = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if (user.getId() != null && !"".equals(user.getId().toString())) {
                System.out.println("user.getId()"+user.getId());
                Predicate predicate = cb.equal(root.get("id").as(Long.class), user.getId());
                list.add(predicate);
            }
            if (user.getUserName() != null && !"".equals(user.getUserName())) {
                System.out.println("user.getUserName()"+user.getUserName());
                Predicate predicate = cb.equal(root.get("userName").as(String.class), user.getUserName());
                list.add(predicate);
            }

            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };
        Page<User> pageList = userRepository.findAll(spec, pageable);
        return pageList;
    }


}
