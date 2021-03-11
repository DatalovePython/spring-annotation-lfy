package com.atguigu.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author luzc
 * @date 2021/3/9 16:47
 * @desc
 */

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void insertUser(){
        userDao.insert();

        // other dao

        System.out.println("插入完成");

        int i=  10/0;
    }
}
