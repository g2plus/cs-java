package top.arhi.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.arhi.mapper.UserMapper;
import top.arhi.model.pojo.User;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}
