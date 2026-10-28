package net.wanho.service.impl;

import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import net.wanho.po.Student;
import net.wanho.service.StudentServiceI;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentServiceImplTest {

    @Resource
    private StudentServiceI studentService;

    @Test
    void findByCondition() {
        PageInfo<Student> pageInfo = studentService.findByCondition(1, 5, new Student());
        System.out.println(pageInfo);
    }
}
