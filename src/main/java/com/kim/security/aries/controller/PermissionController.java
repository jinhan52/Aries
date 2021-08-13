package com.kim.security.aries.controller;

import cn.hutool.json.JSONObject;
import com.kim.security.aries.common.DataResult;
import com.kim.security.aries.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @GetMapping("initMenuData")
    public DataResult initMenuData(
            @RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize){
        return permissionService.initMenuData(currentPage,pageSize);
    }

    @GetMapping("initMenuDataWithSearch")
    public DataResult initMenuDataWithSearch(
            @RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
            @RequestParam(value = "search",required = false) String search){
        return permissionService.initMenuDataWithSearch(currentPage,pageSize,search);
    }


    @GetMapping("initMenuParentData")
    public DataResult initMenuParentData(){
        return permissionService.initMenuParentData();
    }

    @PostMapping("addNewMenu")
    public DataResult addNewMenu(@RequestBody JSONObject jsonObject){
        return permissionService.addNewMenu(jsonObject);
    }

    @PostMapping("eiditorMenu")
    public DataResult eiditorMenu(@RequestBody JSONObject jsonObject){
        return permissionService.eiditorMenu(jsonObject);
    }
    @PostMapping("deleteMenu")
    public DataResult deleteMenu(@RequestBody JSONObject jsonObject){
        return permissionService.deleteMenu(jsonObject);
    }
}
