package com.atguigu.service.impl;

import com.atguigu.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author luzc
 * @date 2021/4/29 10:03
 * @desc
 */
@Service
public class TestServiceAImpl implements TestService {
    @Override
    public String getString() {
        return "A";
    }
}
