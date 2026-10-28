package net.wanho.service;

import cn.hutool.core.util.ObjectUtil;
import net.wanho.exception.CommonCode;
import net.wanho.exception.CustomerException;
import net.wanho.po.LoginUser;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public String login(LoginUser loginUser) {

        String username = loginUser.getUsername();

        //  LoginUser dbUser = findUserByUserName(username);
        LoginUser dbUser = loginUser;
        if(ObjectUtil.isNull(dbUser)){
            throw new CustomerException(CommonCode.USER_NOT_FOUND);
        }
        if(!dbUser.getPassword().equals(loginUser.getPassword())){
            throw new CustomerException(CommonCode.USER_PASSWORD_ERROR);
        }
        if(!dbUser.getStatus().equals("0")){
            throw new CustomerException(CommonCode.USER_LOCKED);
        }
        if(!dbUser.getDelFlag().equals("0")){
            throw new CustomerException(CommonCode.USER_DELETED);
        }

        return "^&*()_}PK:OLLIOPPK:LML:KUJOIUJMLK";

    }
}
