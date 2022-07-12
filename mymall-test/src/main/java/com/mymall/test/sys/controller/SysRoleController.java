package com.mymall.test.sys.controller;

import com.mymall.framework.utils.Result;
import com.mymall.test.sys.entity.SysRole;
import com.mymall.test.sys.service.SysRoleService;
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
@RequestMapping("/sys/api/v1/sys-role")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    /**
    * 查询分页数据
    */
    @GetMapping("/list")
    public Result listByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor) {
        return Result.success(sysRoleService.listSysRolesByPage(page, pageSize,factor));
    }


    /**
    * 根据id查询
    */
    @GetMapping(value = { "/", "/{id}" })
    public Result getById(@PathVariable("id") Integer id) {
        return Result.success(sysRoleService.getSysRoleById(id));
    }

    /**
    * 新增
    */
    @RequestMapping(method = RequestMethod.POST)
    public Result insert(@RequestBody SysRole sysRole) {
        return Result.success(sysRoleService.insertSysRole(sysRole));
    }

    /**
    * 删除
    */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        return Result.success(sysRoleService.deleteSysRoleById(id));
    }

    /**
    * 修改
    */
    @RequestMapping(method = RequestMethod.PUT)
    public Result updateById(@RequestBody SysRole sysRole) {
        return Result.success(sysRoleService.updateSysRole(sysRole));
    }
}
