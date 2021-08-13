package com.kim.security.aries.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kim.security.aries.model.Ariesroles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AriesrolesMapper extends BaseMapper<Ariesroles> {

    List<Ariesroles> getAllRoles(
            @Param("currentPage")Integer currentPage,
            @Param("pageSize") Integer pageSize,
            @Param("search") String search);

    Integer getRolesCount(@Param("search") String search);

    Integer insertRoleWithId(Ariesroles roles);

    void insertRolePermission(@Param("roleid") Integer roleid ,@Param("permissions") String[] permissions);

    void removeAllPermission(Integer roleid);

    List<Ariesroles> getRoleListByUserId(Integer userid);
}