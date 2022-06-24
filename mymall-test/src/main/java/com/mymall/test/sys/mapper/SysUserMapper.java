package com.mymall.test.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mymall.test.sys.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
* <p>
*  Mapper 接口
* </p>
*
* @author lcc
* @since 2022-06-23
*/
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    IPage<String> getUserListSql(IPage<SysUser> page, String username, String city);

}
