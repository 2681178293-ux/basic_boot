package net.wanho.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import net.wanho.po.Student;
import net.wanho.service.StudentServiceI;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentServiceImplTest {

    @Resource
    private StudentServiceI studentService;

    @Test
    void getStudentById() {
        Student student = studentService.getById(2);
        System.out.println(student);
    }

    @Test
    void getStudentByName() {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.like("name", "张");
        wrapper.eq("age", 18);
        List<Student> students = studentService.list(wrapper);
        System.out.println(students);
    }

    @Test
    void getStudentsByPage() {
        IPage<Student> page = new Page<>(1, 5);
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Student::getName, "张");
        wrapper.eq(Student::getAge, 18);
        page = studentService.page(page,wrapper);
        System.out.println(page);
    }

    @Test
    void test1(){
        Student stu = new Student();
        stu.setId(2L);
        studentService.findStudentAndScores(stu);
    }
}
