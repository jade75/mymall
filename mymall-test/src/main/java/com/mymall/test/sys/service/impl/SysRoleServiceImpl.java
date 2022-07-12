package com.mymall.test.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mymall.framework.exception.customizedException;
import com.mymall.test.sys.entity.SysRole;
import com.mymall.test.sys.mapper.SysRoleMapper;
import com.mymall.test.sys.service.SysRoleService;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public Page<SysRole> listSysRolesByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询sysRole: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        QueryWrapper<SysRole> queryWrapper =  new QueryWrapper<SysRole>().like("", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<SysRole> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询sysRole完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public SysRole getSysRoleById(int id) {
        log.info("正在查询sysRole中id为{}的数据",id);
        SysRole sysRole = super.getById(id);
        log.info("查询id为{}的sysRole{}",id,(null == sysRole?"无结果":"成功"));
        return sysRole;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public long insertSysRole(SysRole sysRole) {
        log.info("正在插入sysRole");
        if (super.save(sysRole)) {
            log.info("插入sysRole成功,id为{}",sysRole.getId());
            return sysRole.getId();
        } else {
            log.error("插入sysRole失败");
            throw new customizedException("添加失败");
        }
    }

    @Override
    public int deleteSysRoleById(int id) {
        log.info("正在删除id为{}的sysRole",id);
        if (super.removeById(id)) {
            log.info("删除id为{}的sysRole成功",id);
            return id;
        } else {
            log.error("删除id为{}的sysRole失败",id);
            throw new customizedException("删除失败[id=" + id + "]");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public long updateSysRole(SysRole sysRole) {
        log.info("正在更新id为{}的sysRole",sysRole.getId());
        if (super.updateById(sysRole)) {
            log.info("更新d为{}的sysRole成功",sysRole.getId());
            return sysRole.getId();
        } else {
            log.error("更新id为{}的sysRole失败",sysRole.getId());
            throw new customizedException("更新失败[id=" + sysRole.getId() + "]");
        }
    }

}
