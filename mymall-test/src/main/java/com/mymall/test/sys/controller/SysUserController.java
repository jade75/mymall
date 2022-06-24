package com.mymall.test.sys.controller;

import com.mymall.framework.utils.Result;
import org.springframework.web.bind.annotation.*;
import com.mymall.test.sys.service.SysUserService;
import com.mymall.test.sys.entity.SysUser;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lcc
 * @since 2022-06-23
 * @version v1.0
 */
@RestController
@RequestMapping("/Tsys/api/v1/sys-user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
    * 查询分页数据
    */
    @RequestMapping(method = RequestMethod.GET)
    public Result listByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor) {
        return Result.success(sysUserService.listSysUsersByPage(page, pageSize,factor));
    }

    @GetMapping("/page")
    public Result listByPage2() {
        Map<String, Object> param=new HashMap<>();
        param.put("username", "admin");
        param.put("page", "1");
        return Result.success(sysUserService.queryPage(param));
    }


    @GetMapping("/page2")
    public Result listByPage3() {

        return Result.success(sysUserService.queryPageSql("admin",null));
    }

    /**
    * 根据id查询
    */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        return Result.success(sysUserService.getSysUserById(id));
    }

    /**
    * 新增
    */
    @RequestMapping(method = RequestMethod.POST)
    public Result insert(@RequestBody SysUser sysUser) {
        return Result.success(sysUserService.insertSysUser(sysUser));
    }

    /**
    * 删除
    */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        return Result.success(sysUserService.deleteSysUserById(id));
    }

    /**
    * 修改
    */
    @RequestMapping(method = RequestMethod.PUT)
    public Result updateById(@RequestBody SysUser sysUser) {
        return Result.success(sysUserService.updateSysUser(sysUser));
    }
}
