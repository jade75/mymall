package com.mymall.test.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mymall.test.sys.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
* <p>
*  Mapper 接口
* </p>
*
* @author lcc
* @since 2022-06-24
*/
@Mapper
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}
