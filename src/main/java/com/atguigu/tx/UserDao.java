package com.atguigu.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author luzc
 * @date 2021/3/9 16:47
 * @desc
 */

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(){
        String sql = "insert into user_info (login_name) values(?) ";

        String substring = UUID.randomUUID().toString().substring(0, 5);

        jdbcTemplate.update(sql,substring);
    }
}
