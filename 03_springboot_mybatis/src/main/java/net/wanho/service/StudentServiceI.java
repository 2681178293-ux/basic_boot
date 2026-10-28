package net.wanho.service;

import com.github.pagehelper.PageInfo;
import net.wanho.po.Student;

public interface StudentServiceI {

    PageInfo<Student> findByCondition(int pageNum,int pageSize,Student student);

    Student getById(Long id);
    void add(Student student);
    void update(Student student);
    void delById(Long id);
}
