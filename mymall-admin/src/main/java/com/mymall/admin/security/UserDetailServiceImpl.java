package com.mymall.admin.security;

import com.mymall.test.sys.entity.SysMenu;
import com.mymall.test.sys.entity.SysUser;
import com.mymall.test.sys.service.impl.SysMenuServiceImpl;
import com.mymall.test.sys.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    SysUserServiceImpl SysUserService;


    @Autowired
    SysMenuServiceImpl sysMenuService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUser = SysUserService.findByUsername(username);
        if(sysUser==null){
            throw new UsernameNotFoundException("用户名或密码不正确");
        }
        return new AccountUser(sysUser.getId(), sysUser.getUsername(), sysUser.getPassword(), getUserAuthority(sysUser.getId()));
    }


    public List<GrantedAuthority> getUserAuthority(Long userId){

        List<SysMenu> menusByUserId = sysMenuService.getMenuByUserId(userId);
        String perms= menusByUserId.stream().map(SysMenu::getPerms).collect(Collectors.joining(","));
        return AuthorityUtils.commaSeparatedStringToAuthorityList(perms);

        // 角色(ROLE_admin)、菜单操作权限 sys:user:list
//        String authority = sysUserService.getUserAuthorityInfo(userId);  // ROLE_admin,ROLE_normal,sys:user:list,....
//        String authority = "a:b:c,t,h,m";
//        sys:manage,sys:user:list,sys:role:list,sys:menu:list,sys:tools,sys:dict:list,sys:role:save,sys:user:save,sys:user:update,sys:user:delete,sys:user:role,sys:user:repass,sys:role:update,sys:role:delete,sys:role:perm,sys:menu:save,sys:menu:update,sys:menu:delete
    }
}
