package com.kinrin500.security;

import com.kinrin500.entity.User;
import com.kinrin500.service.UserService;
import com.kinrin500.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AuthToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("[doGetAuthorizationInfo]:执行授权逻辑");
        String username=TokenUtil.getAccount(principalCollection.toString());
        SimpleAuthorizationInfo info= new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        userService.getById(user.getId());
        info.addStringPermission(user.getPerms());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("身份认证");
        String token= (String) authenticationToken.getCredentials();
        String username= TokenUtil.getAccount(token);
        log.info(username);
        //这里要去数据库查找是否存在该用户，这里直接放行
        if (username==null){
            throw new AuthenticationException("认证失败！");
        }
        return new SimpleAuthenticationInfo(token,token,"UserRealm");
    }
}
