package com.mymall.test.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mymall.test.sys.entity.SysUserRole;

/**
* <p>
*  服务类
* </p>
*
* @author lcc
* @since 2022-06-24
*/
public interface SysUserRoleService {



    /**
    * 分页查询SysUserRole
    *
    * @param page     当前页数
    * @param pageSize 页的大小
    * @param factor  搜索关键词
    * @return 返回mybatis-plus的Page对象,其中records字段为符合条件的查询结果
    * @author lcc
    * @since 2022-06-24
    */
    Page<SysUserRole> listSysUserRolesByPage(int page, int pageSize, String factor);

    /**
    * 根据id查询SysUserRole
    *
    * @param id 需要查询的SysUserRole的id
    * @return 返回对应id的SysUserRole对象
    * @author lcc
    * @since 2022-06-24
    */
    SysUserRole getSysUserRoleById(int id);

    /**
    * 插入SysUserRole
    *
    * @param sysUserRole 需要插入的SysUserRole对象
    * @return 返回插入成功之后SysUserRole对象的id
    * @author lcc
    * @since 2022-06-24
    */
    long insertSysUserRole(SysUserRole sysUserRole);

    /**
    * 根据id删除SysUserRole
    *
    * @param id 需要删除的SysUserRole对象的id
    * @return 返回被删除的SysUserRole对象的id
    * @author lcc
    * @since 2022-06-24
    */
    int deleteSysUserRoleById(int id);

    /**
    * 根据id更新SysUserRole
    *
    * @param sysUserRole 需要更新的SysUserRole对象
    * @return 返回被更新的SysUserRole对象的id
    * @author lcc
    * @since 2022-06-24
    */
    long updateSysUserRole(SysUserRole sysUserRole);

}
