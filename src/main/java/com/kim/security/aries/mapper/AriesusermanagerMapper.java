package com.kim.security.aries.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kim.security.aries.model.Ariesusermanager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AriesusermanagerMapper extends BaseMapper<Ariesusermanager> {

    Ariesusermanager authLogin(@Param("phone") String phone,@Param("password") String password);

    List<Ariesusermanager> initUsersData(@Param("currentPage")Integer currentPage,
                                         @Param("pageSize") Integer pageSize,
                                         @Param("search") String search);
    Integer initUsersCount(@Param("search") String search);

    void insertNewUser(Ariesusermanager ariesusermanager);

    void insertUserRoles(@Param("userId") Integer userid, @Param("roles") String[] roles);

    void deleteUserRole(@Param("userId") Integer userid);
}