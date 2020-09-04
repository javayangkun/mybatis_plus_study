package com.mp.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mp.study.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
