package net.wanho.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import net.wanho.po.User;
import net.wanho.service.UserServiceI;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

    @Resource
    private UserServiceI userService;

    @Test
    void getUserAndAddressById() {
        User user = userService.getUserAndAddressById(1L);
        System.out.println(user);

    }

    @Test
    void getUsers() {
        User user = new User();
        user.setName("J");
        IPage<User> page = userService.getUsers(1, 5, user);
        System.out.println(page.getRecords());
    }
}
