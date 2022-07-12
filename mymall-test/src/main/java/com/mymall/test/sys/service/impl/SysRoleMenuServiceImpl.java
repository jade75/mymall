package com.mymall.test.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mymall.framework.exception.customizedException;
import com.mymall.test.sys.entity.SysRoleMenu;
import com.mymall.test.sys.mapper.SysRoleMenuMapper;
import com.mymall.test.sys.service.SysRoleMenuService;
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
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Override
    public Page<SysRoleMenu> listSysRoleMenusByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询sysRoleMenu: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        QueryWrapper<SysRoleMenu> queryWrapper =  new QueryWrapper<SysRoleMenu>().like("", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<SysRoleMenu> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询sysRoleMenu完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public SysRoleMenu getSysRoleMenuById(int id) {
        log.info("正在查询sysRoleMenu中id为{}的数据",id);
        SysRoleMenu sysRoleMenu = super.getById(id);
        log.info("查询id为{}的sysRoleMenu{}",id,(null == sysRoleMenu?"无结果":"成功"));
        return sysRoleMenu;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public long insertSysRoleMenu(SysRoleMenu sysRoleMenu) {
        log.info("正在插入sysRoleMenu");
        if (super.save(sysRoleMenu)) {
            log.info("插入sysRoleMenu成功,id为{}",sysRoleMenu.getId());
            return sysRoleMenu.getId();
        } else {
            log.error("插入sysRoleMenu失败");
            throw new customizedException("添加失败");
        }
    }

    @Override
    public int deleteSysRoleMenuById(int id) {
        log.info("正在删除id为{}的sysRoleMenu",id);
        if (super.removeById(id)) {
            log.info("删除id为{}的sysRoleMenu成功",id);
            return id;
        } else {
            log.error("删除id为{}的sysRoleMenu失败",id);
            throw new customizedException("删除失败[id=" + id + "]");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public long updateSysRoleMenu(SysRoleMenu sysRoleMenu) {
        log.info("正在更新id为{}的sysRoleMenu",sysRoleMenu.getId());
        if (super.updateById(sysRoleMenu)) {
            log.info("更新d为{}的sysRoleMenu成功",sysRoleMenu.getId());
            return sysRoleMenu.getId();
        } else {
            log.error("更新id为{}的sysRoleMenu失败",sysRoleMenu.getId());
            throw new customizedException("更新失败[id=" + sysRoleMenu.getId() + "]");
        }
    }

}
