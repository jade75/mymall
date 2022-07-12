package com.mymall.test.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mymall.test.sys.entity.SysMenu;

import java.util.List;

/**
* <p>
*  服务类
* </p>
*
* @author lcc
* @since 2022-06-24
*/
public interface SysMenuService {


    List<SysMenu> getMenuByUserId(Long userId);

    /**
    * 分页查询SysMenu
    *
    * @param page     当前页数
    * @param pageSize 页的大小
    * @param factor  搜索关键词
    * @return 返回mybatis-plus的Page对象,其中records字段为符合条件的查询结果
    * @author lcc
    * @since 2022-06-24
    */
    Page<SysMenu> listSysMenusByPage(int page, int pageSize, String factor);

    /**
    * 根据id查询SysMenu
    *
    * @param id 需要查询的SysMenu的id
    * @return 返回对应id的SysMenu对象
    * @author lcc
    * @since 2022-06-24
    */
    SysMenu getSysMenuById(int id);

    /**
    * 插入SysMenu
    *
    * @param sysMenu 需要插入的SysMenu对象
    * @return 返回插入成功之后SysMenu对象的id
    * @author lcc
    * @since 2022-06-24
    */
    long insertSysMenu(SysMenu sysMenu);

    /**
    * 根据id删除SysMenu
    *
    * @param id 需要删除的SysMenu对象的id
    * @return 返回被删除的SysMenu对象的id
    * @author lcc
    * @since 2022-06-24
    */
    int deleteSysMenuById(int id);

    /**
    * 根据id更新SysMenu
    *
    * @param sysMenu 需要更新的SysMenu对象
    * @return 返回被更新的SysMenu对象的id
    * @author lcc
    * @since 2022-06-24
    */
    long updateSysMenu(SysMenu sysMenu);

}
