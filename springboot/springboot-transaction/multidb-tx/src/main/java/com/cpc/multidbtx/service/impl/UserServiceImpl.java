package com.cpc.multidbtx.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpc.multidbtx.domain.User;
import com.cpc.multidbtx.mapper.UserMapper;
import com.cpc.multidbtx.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
