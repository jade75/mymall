package com.mymall.test.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mymall.framework.utils.PageUtils;
import com.mymall.test.sys.entity.SysUser;

import java.util.Map;

/**
* <p>
*  服务类
* </p>
*
* @author lcc
* @since 2022-06-23
*/
public interface SysUserService {

    SysUser findByUsername(String username);


    PageUtils queryPageSql(String username, String city);

    PageUtils queryPage(Map<String, Object> params);

    /**
    * 分页查询SysUser
    *
    * @param page     当前页数
    * @param pageSize 页的大小
    * @param factor  搜索关键词
    * @return 返回mybatis-plus的Page对象,其中records字段为符合条件的查询结果
    * @author lcc
    * @since 2022-06-23
    */
    Page<SysUser> listSysUsersByPage(int page, int pageSize, String factor);

    /**
    * 根据id查询SysUser
    *
    * @param id 需要查询的SysUser的id
    * @return 返回对应id的SysUser对象
    * @author lcc
    * @since 2022-06-23
    */
    SysUser getSysUserById(int id);

    /**
    * 插入SysUser
    *
    * @param sysUser 需要插入的SysUser对象
    * @return 返回插入成功之后SysUser对象的id
    * @author lcc
    * @since 2022-06-23
    */
    long insertSysUser(SysUser sysUser);

    /**
    * 根据id删除SysUser
    *
    * @param id 需要删除的SysUser对象的id
    * @return 返回被删除的SysUser对象的id
    * @author lcc
    * @since 2022-06-23
    */
    int deleteSysUserById(int id);

    /**
    * 根据id更新SysUser
    *
    * @param sysUser 需要更新的SysUser对象
    * @return 返回被更新的SysUser对象的id
    * @author lcc
    * @since 2022-06-23
    */
    long updateSysUser(SysUser sysUser);

}
