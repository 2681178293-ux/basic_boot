package net.wanho.controller;

import lombok.RequiredArgsConstructor;
import net.wanho.po.LoginUser;
import net.wanho.service.AuthService;
import net.wanho.vo.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录测试
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 测试登录
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginUser loginUser) {
        String token = authService.login(loginUser);
        return AjaxResult.success().put("token", token);
    }


    // 测试注销
    @RequestMapping("/logout")
    public AjaxResult logout() {
        authService.logout();
        return AjaxResult.success();
    }

}
