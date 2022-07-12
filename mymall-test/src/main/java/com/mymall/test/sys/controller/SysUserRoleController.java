package com.mymall.test.sys.controller;

import com.mymall.framework.utils.Result;
import com.mymall.test.sys.entity.SysUserRole;
import com.mymall.test.sys.service.SysUserRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lcc
 * @since 2022-06-24
 * @version v1.0
 */
@RestController
@RequestMapping("/sys/api/v1/sys-user-role")
public class SysUserRoleController {

    @Resource
    private SysUserRoleService sysUserRoleService;

    /**
    * 查询分页数据
    */
    @GetMapping("/list")
    public Result listByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor) {
        return Result.success(sysUserRoleService.listSysUserRolesByPage(page, pageSize,factor));
    }


    /**
    * 根据id查询
    */
    @GetMapping(value = { "/", "/{id}" })
    public Result getById(@PathVariable("id") Integer id) {
        return Result.success(sysUserRoleService.getSysUserRoleById(id));
    }

    /**
    * 新增
    */
    @RequestMapping(method = RequestMethod.POST)
    public Result insert(@RequestBody SysUserRole sysUserRole) {
        return Result.success(sysUserRoleService.insertSysUserRole(sysUserRole));
    }

    /**
    * 删除
    */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        return Result.success(sysUserRoleService.deleteSysUserRoleById(id));
    }

    /**
    * 修改
    */
    @RequestMapping(method = RequestMethod.PUT)
    public Result updateById(@RequestBody SysUserRole sysUserRole) {
        return Result.success(sysUserRoleService.updateSysUserRole(sysUserRole));
    }
}
