package com.kim.security.aries.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kim.security.aries.model.Ariespermission;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

@Mapper
public interface AriespermissionMapper extends BaseMapper<Ariespermission> {
    List<Ariespermission> getUsersAllPermission(Integer userid);

    List<Ariespermission> getAllMenus();

    List<Ariespermission> getAllMenusChild(Integer parentId);

    Ariespermission getMenuParent(Integer parentId);

    List<Ariespermission> initMenuData(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    List<Ariespermission> initMenuDataWithSearch(
            @Param("currentPage") Integer currentPage,
            @Param("pageSize") Integer pageSize,
            @Param("search") String search
    );
    Integer selectCountWithSearch( @Param("search") String search);

    List<Ariespermission> initMenuParentData();

    void eiditorMenu(Ariespermission ariespermission);

    List<Ariespermission> getRolePermission(Integer roleid);

    List<Ariespermission> getAllPermission();

}