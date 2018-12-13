package com.yuanchun.repo;

import com.yuanchun.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> , JpaSpecificationExecutor<User> {

    /*
     * Jpa命名规范，查询
     */
    User findUserByUserName(String userName);
    /*
     * (non-Javadoc)新增用户
     * @see org.springframework.data.repository.CrudRepository#save(S)
     */
    User save(User user2);

    //And --- 等价于 SQL 中的 and 关键字，比如 findByHeightAndSex(int height,char sex)；
    public List<User> findByHeightAndSex(int height,char sex);

    // Or --- 等价于 SQL 中的 or 关键字，比如 findByHeightOrSex(int height,char sex)；
    public List<User> findByHeightOrSex(int height,char sex);

    //Between --- 等价于 SQL 中的 between 关键字，比如 findByHeightBetween(int min, int max)；
    public List<User> findByHeightBetween(int min,int max);

    //LessThan --- 等价于 SQL 中的 "<"，比如 findByHeightLessThan(int max)；
    public List<User> findByHeightLessThan(int max);

    //GreaterThan --- 等价于 SQL 中的">"，比如 findByHeightGreaterThan(int min)；
    public List<User> findByHeightGreaterThan(int min);

    //IsNull --- 等价于 SQL 中的 "is null"，比如 findByNameIsNull()；
    public List<User> findByUserNameIsNull();

    //IsNotNull --- 等价于 SQL 中的 "is not null"，比如 findByUserNameIsNotNull()；
    public List<User> findByUserNameIsNotNull();

    //NotNull --- 与 IsNotNull 等价；
    public List<User> findByUserNameNotNull();

    //Like --- 等价于 SQL 中的 "like"，比如 findByUserNameLike(String UserName);
    public List<User> findByUserNameLike(String UserName);

    //NotLike --- 等价于 SQL 中的 "not like"，比如 findByUserNameNotLike(String UserName)；
    public List<User> findByUserNameNotLike(String UserName);

    //OrderBy --- 等价于 SQL 中的 "order by"，比如 findByUserNameNotNullOrderByHeightAsc()；
    public List<User>findByUserNameNotNullOrderByHeightAsc();

    //Not --- 等价于 SQL 中的 "！ ="，比如 findByUserNameNot(String UserName)；
    public List<User> findByUserNameNot(String UserName);

    //In --- 等价于 SQL 中的 "in"，比如 findByUserNameIN(String UserName);
    public List<User> findByUserNameIn(String UserName);

    //NotIn --- 等价于 SQL 中的 "not in"，比如 findByUserNameNotIN(String UserName);
    public List<User> findByUserNameNotIn(String UserName);

    //利用原生的SQL进行删除操作
    @Query(value = "select * from  test_user where user_name=?1 ", nativeQuery = true)
    @Modifying
    public List<User> selectByName(String name);

}
