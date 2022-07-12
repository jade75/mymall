package com.mymall.test.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mymall.framework.exception.customizedException;
import com.mymall.test.sys.entity.SysUserRole;
import com.mymall.test.sys.mapper.SysUserRoleMapper;
import com.mymall.test.sys.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* <p>
*  服务实现类
* </p>
*
* @author lcc
* @since 2022-06-24
*/
@Slf4j
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public Page<SysUserRole> listSysUserRolesByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询sysUserRole: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        QueryWrapper<SysUserRole> queryWrapper =  new QueryWrapper<SysUserRole>().like("", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<SysUserRole> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询sysUserRole完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public SysUserRole getSysUserRoleById(int id) {
        log.info("正在查询sysUserRole中id为{}的数据",id);
        SysUserRole sysUserRole = super.getById(id);
        log.info("查询id为{}的sysUserRole{}",id,(null == sysUserRole?"无结果":"成功"));
        return sysUserRole;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public long insertSysUserRole(SysUserRole sysUserRole) {
        log.info("正在插入sysUserRole");
        if (super.save(sysUserRole)) {
            log.info("插入sysUserRole成功,id为{}",sysUserRole.getId());
            return sysUserRole.getId();
        } else {
            log.error("插入sysUserRole失败");
            throw new customizedException("添加失败");
        }
    }

    @Override
    public int deleteSysUserRoleById(int id) {
        log.info("正在删除id为{}的sysUserRole",id);
        if (super.removeById(id)) {
            log.info("删除id为{}的sysUserRole成功",id);
            return id;
        } else {
            log.error("删除id为{}的sysUserRole失败",id);
            throw new customizedException("删除失败[id=" + id + "]");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public long updateSysUserRole(SysUserRole sysUserRole) {
        log.info("正在更新id为{}的sysUserRole",sysUserRole.getId());
        if (super.updateById(sysUserRole)) {
            log.info("更新d为{}的sysUserRole成功",sysUserRole.getId());
            return sysUserRole.getId();
        } else {
            log.error("更新id为{}的sysUserRole失败",sysUserRole.getId());
            throw new customizedException("更新失败[id=" + sysUserRole.getId() + "]");
        }
    }

}
