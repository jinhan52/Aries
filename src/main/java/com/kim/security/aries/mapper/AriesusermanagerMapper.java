package com.kim.security.aries.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kim.security.aries.model.Ariesusermanager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AriesusermanagerMapper extends BaseMapper<Ariesusermanager> {

    Ariesusermanager authLogin(@Param("phone") String phone,@Param("password") String password);
}