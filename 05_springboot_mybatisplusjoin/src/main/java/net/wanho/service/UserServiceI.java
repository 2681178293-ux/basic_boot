package net.wanho.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseService;
import net.wanho.po.User;

public interface UserServiceI extends MPJBaseService<User> {

    User getUserAndAddressById(Long userId);
    IPage<User> getUsers(int pageNum,int pageSize,User user);
}
