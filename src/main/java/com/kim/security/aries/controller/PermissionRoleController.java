package com.kim.security.aries.controller;

import cn.hutool.json.JSONObject;
import com.kim.security.aries.common.DataResult;
import com.kim.security.aries.services.AriesrolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("permission/role")
public class PermissionRoleController {
    @Autowired
    AriesrolesService ariesrolesService;

    @GetMapping("getAllRoles")
    public DataResult getAllRoles(
            @RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
            @RequestParam(value = "search",required = false,defaultValue = "") String search
    ){
        return ariesrolesService.getAllRoles(currentPage,pageSize,search);
    }

    @GetMapping("getAllPermission")
    public DataResult getAllPermission(){
        return ariesrolesService.getAllPermission();
    }

    @PostMapping("addRolePermission")
    public DataResult addRolePermission(@RequestBody JSONObject jsonObject){
        return ariesrolesService.addRolePermission(jsonObject);
    }

    @PostMapping("updateRolePermission")
    public DataResult updateRolePermission(@RequestBody JSONObject jsonObject){
        return ariesrolesService.updateRolePermission(jsonObject);
    }

    @PostMapping("deleteRolePermission")
    public DataResult deleteRolePermission(@RequestBody JSONObject jsonObject){
        return ariesrolesService.deleteRolePermission(jsonObject);
    }
}
