package com.mymall.test.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mymall.framework.exception.customizedException;
import com.mymall.framework.utils.PageUtils;
import com.mymall.framework.utils.Query;
import com.mymall.test.sys.entity.SysUser;
import com.mymall.test.sys.mapper.SysUserMapper;
import com.mymall.test.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lcc
 * @since 2022-06-23
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {


    @Override
    public PageUtils queryPageSql(String username, String city) {

        Page<SysUser> page = new Page<>(1,10);
        IPage<String> result = baseMapper.getUserListSql(page, username, city);
        return new PageUtils(result);
    }



    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String username = (String) params.get("username");
        String email = (String) params.get("email");
        String city = (String) params.get("city");

//        IPage<SysUser> pageQuery = page(
//                new Page<>(page, pageSize),
//                new QueryWrapper<SysUser>()
//                .like(StringUtils.isNotBlank(username),"username", username)
//        );

        IPage<SysUser> page = this.page(
                new Query<SysUser>().getPage(params,"id",false),
                new QueryWrapper<SysUser>()
                        .like(StringUtils.isNotBlank(username), "username", username)

        );

        return new PageUtils(page);
    }

    @Override
    public Page<SysUser> listSysUsersByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询sysUser: page = {} pageSize = {} factor = {}", page, pageSize, factor);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>().like("username", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<SysUser> result = super.page(new Page<>(page, pageSize), queryWrapper);
        log.info("分页查询sysUser完毕: 结果数 = {} ", result.getRecords().size());
        return result;
    }

    @Override
    public SysUser getSysUserById(int id) {
        log.info("正在查询sysUser中id为{}的数据", id);
        SysUser sysUser = super.getById(id);
        log.info("查询id为{}的sysUser{}", id, (null == sysUser ? "无结果" : "成功"));
        return sysUser;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public long insertSysUser(SysUser sysUser) {
        log.info("正在插入sysUser");
        if (super.save(sysUser)) {
            log.info("插入sysUser成功,id为{}", sysUser.getId());
            return sysUser.getId();
        } else {
            log.error("插入sysUser失败");
            throw new customizedException("添加失败");
        }
    }

    @Override
    public int deleteSysUserById(int id) {
        log.info("正在删除id为{}的sysUser", id);
        if (super.removeById(id)) {
            log.info("删除id为{}的sysUser成功", id);
            return id;
        } else {
            log.error("删除id为{}的sysUser失败", id);
            throw new customizedException("删除失败[id=" + id + "]");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public long updateSysUser(SysUser sysUser) {
        log.info("正在更新id为{}的sysUser", sysUser.getId());
        if (super.updateById(sysUser)) {
            log.info("更新d为{}的sysUser成功", sysUser.getId());
            return sysUser.getId();
        } else {
            log.error("更新id为{}的sysUser失败", sysUser.getId());
            throw new customizedException("更新失败[id=" + sysUser.getId() + "]");
        }
    }

}
