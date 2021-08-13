package com.kim.security.aries.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kim.security.aries.model.Usermanager;
import com.kim.security.aries.model.Usermanager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsermanagerMapper extends BaseMapper<Usermanager> {

    Usermanager authLogin(@Param("phone") String phone, @Param("password") String password);

    List<Usermanager> initUsersData(@Param("currentPage")Integer currentPage,
                                         @Param("pageSize") Integer pageSize,
                                         @Param("search") String search);
    Integer initUsersCount(@Param("search") String search);

    void insertNewUser(Usermanager Usermanager);

    void insertUserRoles(@Param("userId") Integer userid, @Param("roles") String[] roles);

    void deleteUserRole(@Param("userId") Integer userid);
}