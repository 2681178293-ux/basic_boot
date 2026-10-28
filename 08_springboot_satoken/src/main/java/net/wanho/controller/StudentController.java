package net.wanho.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stu")
public class StudentController {

    @SaCheckPermission("sys:stu:list")
    @GetMapping("/list")
    public String list(){
        return "list " ;
    }

    @SaCheckPermission("sys:stu:add")
    @GetMapping("/add")
    public String add(){
        return "add " ;
    }

    @SaCheckPermission("sys:stu:delete")
    @GetMapping("/delete")
    public String delete(){
        return "delete " ;
    }

    @SaCheckPermission("sys:stu:update")
    @GetMapping("/update")
    public String update(){
        return "update " ;
    }
}
