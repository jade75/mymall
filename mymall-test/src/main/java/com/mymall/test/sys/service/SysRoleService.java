package com.mymall.test.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mymall.test.sys.entity.SysRole;

/**
* <p>
*  服务类
* </p>
*
* @author lcc
* @since 2022-06-24
*/
public interface SysRoleService {

    /**
    * 分页查询SysRole
    *
    * @param page     当前页数
    * @param pageSize 页的大小
    * @param factor  搜索关键词
    * @return 返回mybatis-plus的Page对象,其中records字段为符合条件的查询结果
    * @author lcc
    * @since 2022-06-24
    */
    Page<SysRole> listSysRolesByPage(int page, int pageSize, String factor);

    /**
    * 根据id查询SysRole
    *
    * @param id 需要查询的SysRole的id
    * @return 返回对应id的SysRole对象
    * @author lcc
    * @since 2022-06-24
    */
    SysRole getSysRoleById(int id);

    /**
    * 插入SysRole
    *
    * @param sysRole 需要插入的SysRole对象
    * @return 返回插入成功之后SysRole对象的id
    * @author lcc
    * @since 2022-06-24
    */
    long insertSysRole(SysRole sysRole);

    /**
    * 根据id删除SysRole
    *
    * @param id 需要删除的SysRole对象的id
    * @return 返回被删除的SysRole对象的id
    * @author lcc
    * @since 2022-06-24
    */
    int deleteSysRoleById(int id);

    /**
    * 根据id更新SysRole
    *
    * @param sysRole 需要更新的SysRole对象
    * @return 返回被更新的SysRole对象的id
    * @author lcc
    * @since 2022-06-24
    */
    long updateSysRole(SysRole sysRole);

}
