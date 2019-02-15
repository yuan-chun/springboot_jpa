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
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void updateSome() {
        Optional<User> osc = userRepository.findById(10L);
        //利用 Optional 执行更新操作
        osc.get().setUserName("test2");
        osc.get().setHeight(100);

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

    /**
     * 条件查询
     * @param user
     * @return
     */
    @Override
    public List<User> findAll(User user) {
        //在此方法上，再构造除Pageable对象，就可以实现按条件查询的分页  接口JpaSpecificationExecutor
        //单个规则 Specification 定义两个条件使用 and 组合
        // select * from test_user where (user_name like "%userName%" ) and id > id
        return userRepository.findAll((root, query, cb) -> {
            //root.get("username")表示获取username这个字段名称,like表示执行like查询,%zt%表示值
            Predicate p1 = cb.like(root.get("userName"), "%"+user.getUserName()+"%");
            Predicate p2 = cb.greaterThan(root.get("id"), user.getId());
            //将两个查询条件联合起来之后返回Predicate对象,username模糊查询，id>3
            return cb.and(p1, p2);
        });
    }

    @Override
    public List<User> findAll2(User user) {
        //在此方法上，再构造除Pageable对象，就可以实现按条件查询的分页  接口JpaSpecificationExecutor
        //多个规则 Specification 定义各自条件使用 and 组合 —> (Specification1) and (Specification2)

        //第一个Specification定义了两个or的组合
        Specification<User> s1 = (root, query, cb) -> {
            Predicate p1 = cb.equal(root.get("userName"), user.getUserName());
            Predicate p2 = cb.greaterThan(root.get("id"), user.getId());
            return cb.and(p1,p2);
        };

        //第二个Specification定义了两个or的组合
        Specification<User> s2 = (root, query, cb) -> {
            Predicate p1 = cb.equal(root.get("deleteFlag"), user.getDeleteFlag());
            Predicate p2 = cb.like(root.get("password"), "%"+user.getPassword()+"%");
            return cb.or(p1, p2);
        };

        //通过Specifications将两个Specification连接起来，第一个条件加where，第二个是and
//        SELECT
//        user0_.id AS id1_0_,
//                user0_.create_time AS create_t2_0_,
//        user0_.delete_flag AS delete_f3_0_,
//                user0_.height AS height4_0_,
//        user0_.modify_time AS modify_t5_0_,
//                user0_.password AS password6_0_,
//        user0_.sex AS sex7_0_,
//                user0_.user_name AS user_nam8_0_
//        FROM
//        test_user user0_
//        WHERE user0_.user_name = ?
//        AND user0_.id > 2
//        AND (
//                user0_.delete_flag = 0
//                OR user0_.password LIKE ?
//        )
        List<User> sUserList = userRepository.findAll(Specifications.where(s2).and(s2));
        //分页
        Pageable pageable = new PageRequest(0, 1, Sort.Direction.ASC, "id");
        Page<User> userPage = userRepository.findAll(Specifications.where(s1).and(s2),pageable);
        System.out.println("userPage.map    "+userPage.map(user1 -> user1.getUserName()));
        return sUserList;
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
                System.out.println("user.getId()" + user.getId());
                Predicate predicate = cb.equal(root.get("id").as(Long.class), user.getId());
                list.add(predicate);
            }
            if (user.getUserName() != null && !"".equals(user.getUserName())) {
                System.out.println("user.getUserName()" + user.getUserName());
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
