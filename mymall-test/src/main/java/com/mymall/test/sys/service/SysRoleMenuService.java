package com.mymall.test.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mymall.test.sys.entity.SysRoleMenu;

/**
* <p>
*  服务类
* </p>
*
* @author lcc
* @since 2022-06-24
*/
public interface SysRoleMenuService {

    /**
    * 分页查询SysRoleMenu
    *
    * @param page     当前页数
    * @param pageSize 页的大小
    * @param factor  搜索关键词
    * @return 返回mybatis-plus的Page对象,其中records字段为符合条件的查询结果
    * @author lcc
    * @since 2022-06-24
    */
    Page<SysRoleMenu> listSysRoleMenusByPage(int page, int pageSize, String factor);

    /**
    * 根据id查询SysRoleMenu
    *
    * @param id 需要查询的SysRoleMenu的id
    * @return 返回对应id的SysRoleMenu对象
    * @author lcc
    * @since 2022-06-24
    */
    SysRoleMenu getSysRoleMenuById(int id);

    /**
    * 插入SysRoleMenu
    *
    * @param sysRoleMenu 需要插入的SysRoleMenu对象
    * @return 返回插入成功之后SysRoleMenu对象的id
    * @author lcc
    * @since 2022-06-24
    */
    long insertSysRoleMenu(SysRoleMenu sysRoleMenu);

    /**
    * 根据id删除SysRoleMenu
    *
    * @param id 需要删除的SysRoleMenu对象的id
    * @return 返回被删除的SysRoleMenu对象的id
    * @author lcc
    * @since 2022-06-24
    */
    int deleteSysRoleMenuById(int id);

    /**
    * 根据id更新SysRoleMenu
    *
    * @param sysRoleMenu 需要更新的SysRoleMenu对象
    * @return 返回被更新的SysRoleMenu对象的id
    * @author lcc
    * @since 2022-06-24
    */
    long updateSysRoleMenu(SysRoleMenu sysRoleMenu);

}
