package com.kim.security.aries.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kim.security.aries.model.Permission;
import com.kim.security.aries.model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> getUsersAllPermission(Integer userid);

    List<Permission> getAllMenus();

    List<Permission> getAllMenusChild(Integer parentId);

    Permission getMenuParent(Integer parentId);

    List<Permission> initMenuData(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    List<Permission> initMenuDataWithSearch(
            @Param("currentPage") Integer currentPage,
            @Param("pageSize") Integer pageSize,
            @Param("search") String search
    );
    Integer selectCountWithSearch( @Param("search") String search);

    List<Permission> initMenuParentData();

    void eiditorMenu(Permission Permission);

    List<Permission> getRolePermission(Integer roleid);

    List<Permission> getAllPermission();
}