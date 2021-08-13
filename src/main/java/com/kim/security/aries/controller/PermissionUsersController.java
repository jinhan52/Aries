package com.kim.security.aries.controller;

import cn.hutool.json.JSONObject;
import com.kim.security.aries.common.DataResult;
import com.kim.security.aries.services.UsermanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("permission/users")
public class PermissionUsersController {
    @Autowired
    UsermanagerService usermanagerService;

    @GetMapping("initUsersData")
    public DataResult initUsersData(
            @RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
            @RequestParam(value = "search",required = false,defaultValue = "") String search
    ){
        return usermanagerService.initUsersData(currentPage,pageSize,search);
    }

    @GetMapping("initRoleData")
    public DataResult initRoleData(){
        return usermanagerService.initRoleData();
    }

    @PostMapping("addNewUser")
    public DataResult addNewUser(@RequestBody JSONObject jsonObject){
        return usermanagerService.addNewUser(jsonObject);
    }

    @PostMapping("updateUser")
    public DataResult updateUser(@RequestBody JSONObject jsonObject){
        return usermanagerService.updateUser(jsonObject);
    }

    @PostMapping("deleteUser")
    public DataResult deleteUser(@RequestBody JSONObject jsonObject){
        return usermanagerService.deleteUser(jsonObject);
    }

}
