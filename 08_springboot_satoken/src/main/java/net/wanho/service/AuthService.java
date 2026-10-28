package net.wanho.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.BCrypt;
import net.wanho.po.LoginUser;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public void logout() {
        StpUtil.logout();
    }

    public String login(LoginUser loginUser) {

        String username = loginUser.getUsername();
        // LoginUser dbUser = findUserByUsername(username);
        LoginUser dbUser = new LoginUser(username,"$2a$10$ZLW5s40xHxsn.JcrQoknv.Js/mdG49TCxqbxa09aL2.mYx.FW94Ai",loginUser.getStatus(),loginUser.getDelFlag());

        if(ObjectUtil.isNull(dbUser)){
            throw new RuntimeException("用户不存在！");
        }
        if(!BCrypt.checkpw(loginUser.getPassword(),dbUser.getPassword())){
            throw new RuntimeException("用户名或密码不对！");
        }
        if(!dbUser.getStatus().equals("0")){
            throw new RuntimeException("用户状态已禁用!");
        }
        if(!dbUser.getDelFlag().equals("0")){
            throw new RuntimeException("用户已经被删除！");
        }

        StpUtil.login(username,"PC");
        StpUtil.getSession().set("status",dbUser.getStatus());
        StpUtil.getSession().set("delFlag",dbUser.getDelFlag());
        return StpUtil.getTokenValue();
    }
}
