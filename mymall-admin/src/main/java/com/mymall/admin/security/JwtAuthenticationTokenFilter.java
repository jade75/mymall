package com.mymall.admin.security;

import cn.hutool.core.util.StrUtil;
import com.mymall.framework.utils.JwtTokenUtils;
import com.mymall.test.sys.entity.SysMenu;
import com.mymall.test.sys.entity.SysUser;
import com.mymall.test.sys.service.SysUserService;
import com.mymall.test.sys.service.impl.SysMenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    SysMenuServiceImpl sysMenuService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String usernameFromToken = jwtTokenUtils.getUsernameFromToken(request);

        if (! StrUtil.isBlankOrUndefined(usernameFromToken)){
            SysUser sysUser = sysUserService.findByUsername(usernameFromToken);

            //多处使用
            List<SysMenu> menusByUserId = sysMenuService.getMenuByUserId(sysUser.getId());
            String perms= menusByUserId.stream().map(SysMenu::getPerms).collect(Collectors.joining(","));

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usernameFromToken, null, AuthorityUtils.commaSeparatedStringToAuthorityList(perms));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request,response);

    }
}
