package com.cpc.multidbtx.mapper;

import com.cpc.multidbtx.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountMapper extends IBaseMapper<Account> {

}
