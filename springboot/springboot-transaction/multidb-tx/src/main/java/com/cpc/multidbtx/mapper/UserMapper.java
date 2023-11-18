package com.cpc.multidbtx.mapper;

import com.cpc.multidbtx.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends IBaseMapper<User> {

}
