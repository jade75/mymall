package com.mymall.test.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mymall.framework.exception.customizedException;
import com.mymall.test.sys.entity.SysMenu;
import com.mymall.test.sys.mapper.SysMenuMapper;
import com.mymall.test.sys.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lcc
 * @since 2022-06-24
 */
@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {




    @Override
    public List<SysMenu> getMenuByUserId(Long userId) {

//        baseMapper.selectList(new QueryWrapper<>())

        List<Long> menuListByUserIdSql = baseMapper.getMenuListByUserIdSql(userId);
        if (menuListByUserIdSql.size() > 0) {

//            return baseMapper.selectBatchIds(menuListByUserIdSql);
            return baseMapper.selectList(new QueryWrapper<SysMenu>().in("id", menuListByUserIdSql));
        }else{
            return null;
        }
    }

    @Override
    public Page<SysMenu> listSysMenusByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询sysMenu: page = {} pageSize = {} factor = {}", page, pageSize, factor);
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<SysMenu>().like("", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<SysMenu> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询sysMenu完毕: 结果数 = {} ", result.getRecords().size());
        return result;
    }

    @Override
    public SysMenu getSysMenuById(int id) {
        log.info("正在查询sysMenu中id为{}的数据", id);
        SysMenu sysMenu = super.getById(id);
        log.info("查询id为{}的sysMenu{}", id, (null == sysMenu ? "无结果" : "成功"));
        return sysMenu;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public long insertSysMenu(SysMenu sysMenu) {
        log.info("正在插入sysMenu");
        if (super.save(sysMenu)) {
            log.info("插入sysMenu成功,id为{}", sysMenu.getId());
            return sysMenu.getId();
        } else {
            log.error("插入sysMenu失败");
            throw new customizedException("添加失败");
        }
    }

    @Override
    public int deleteSysMenuById(int id) {
        log.info("正在删除id为{}的sysMenu", id);
        if (super.removeById(id)) {
            log.info("删除id为{}的sysMenu成功", id);
            return id;
        } else {
            log.error("删除id为{}的sysMenu失败", id);
            throw new customizedException("删除失败[id=" + id + "]");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public long updateSysMenu(SysMenu sysMenu) {
        log.info("正在更新id为{}的sysMenu", sysMenu.getId());
        if (super.updateById(sysMenu)) {
            log.info("更新d为{}的sysMenu成功", sysMenu.getId());
            return sysMenu.getId();
        } else {
            log.error("更新id为{}的sysMenu失败", sysMenu.getId());
            throw new customizedException("更新失败[id=" + sysMenu.getId() + "]");
        }
    }

}
