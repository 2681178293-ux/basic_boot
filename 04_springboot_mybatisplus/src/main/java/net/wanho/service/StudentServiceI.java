package net.wanho.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.spring.service.IService;
import net.wanho.po.Student;

public interface StudentServiceI extends IService<Student> {

    IPage<Student> findByPage(int pageNum,int pageSize,Student student);

    Student findStudentAndScores(Student student);
}
